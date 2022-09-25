package com.bookstore.authservice.service.impl;

import com.bookstore.authservice.common.exception.RunTimeExceptionPlaceHolder;
import com.bookstore.authservice.dao.OAuthClient;
import com.bookstore.authservice.dao.RoleDAO;
import com.bookstore.authservice.dao.UserDAO;
import com.bookstore.authservice.repository.OAuthClientRepository;
import com.bookstore.authservice.repository.RoleRepository;
import com.bookstore.authservice.repository.UserRepository;
import com.bookstore.authservice.service.AuthService;
import com.bookstore.authservice.utils.JwtTokenUtil;
import com.bookstore.authservice.utils.UserStatus;
import com.bookstore.authservice.vo.request.CreateOAuthClientRequest;
import com.bookstore.authservice.vo.request.SignInRequest;
import com.bookstore.authservice.vo.request.SignUpRequest;
import com.bookstore.authservice.vo.response.CreateOAuthClientResponse;
import com.bookstore.authservice.vo.response.CreateUserResponse;
import com.bookstore.authservice.vo.response.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthServiceImpl implements AuthService {


  private BCryptPasswordEncoder passwordEncoder;

  private UserRepository userRepository;


  private RoleRepository roleRepository;


  private OAuthClientRepository oAuthClientRepository;

  private AuthenticationManager authenticationManager;

  @Autowired
  public AuthServiceImpl(
          BCryptPasswordEncoder passwordEncoder,
          UserRepository userRepository,
          RoleRepository roleRepository,
          OAuthClientRepository oAuthClientRepository,
          AuthenticationManager authenticationManager

  ){
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.authenticationManager = authenticationManager;
    this.oAuthClientRepository = oAuthClientRepository;
  }

  @Value("${security.jwt.key-store}")
  private Resource keyStore;

  @Value("${security.jwt.key-store-password}")
  private String keyStorePassword;

  @Value("${security.jwt.key-pair-alias}")
  private String keyPairAlias;

  @Value("${security.jwt.key-pair-password}")
  private String keyPairPassword;

  @Value("${security.jwt.public-key}")
  private Resource publicKey;

  @Override
  public CreateOAuthClientResponse createOAuthClient(
      CreateOAuthClientRequest createOAuthClientRequest) {

    //Generate client secret.
    String clientSecret = UUID.randomUUID().toString();
    String encode = passwordEncoder.encode(clientSecret);

    OAuthClient oAuthClient = OAuthClient.builder()
        .client_secret(encode)
        .authorities(String.join(",", createOAuthClientRequest.getAuthorities()))
        .authorized_grant_types(
            String.join(",", createOAuthClientRequest.getAuthorized_grant_types()))
        .scope(String.join(",", createOAuthClientRequest.getScope()))
        .resource_ids(String.join(",", createOAuthClientRequest.getResource_ids()))
        .build();

    OAuthClient saved = oAuthClientRepository.save(oAuthClient);

    return CreateOAuthClientResponse.builder()
        .client_id(saved.getClient_id())
        .client_secret(clientSecret)
        .build();

  }

  @Override
  public CreateUserResponse registerUser(SignUpRequest signUpRequest) {

    if (userRepository.existsByUserName(signUpRequest.getUserName())) {
      throw new RunTimeExceptionPlaceHolder("Username is already taken!!");
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new RunTimeExceptionPlaceHolder("Email address already in use!!");
    }

    // Creating user's account
    UserDAO user = UserDAO
            .builder()
            .userName(signUpRequest.getUserName())
            .password(signUpRequest.getPassword())
            .firstName(signUpRequest.getFirstName())
            .lastName(signUpRequest.getLastName())
            .email(signUpRequest.getEmail())
            .status(UserStatus.ACTIVE)
            .build();


    user.setPassword(passwordEncoder.encode(user.getPassword()));

    RoleDAO userRole = roleRepository.findByRoleName("STANDARD_USER")
        .orElseThrow(() -> new RuntimeException("User Role not set."));

    user.setRoles(Collections.singleton(userRole));

    UserDAO savedUser =
        userRepository.save(user);

    return CreateUserResponse.builder()
        .userId(savedUser.getUserId())
        .userName(savedUser.getUserName())
        .build();

  }

  @Override
  public JwtAuthenticationResponse loginUser(SignInRequest signInRequest) {
    if (userRepository.existsByUserName(signInRequest.getUsernameOrEmail())) {
      throw new RunTimeExceptionPlaceHolder("Username is already taken!!");
    }

    if (userRepository.existsByEmail(signInRequest.getUsernameOrEmail())) {
      throw new RunTimeExceptionPlaceHolder("Email address already in use!!");
    }

    Optional<UserDAO> userDAO = userRepository.findByUserNameOrEmail(signInRequest.getUsernameOrEmail(), signInRequest.getUsernameOrEmail());
    if(userDAO.isEmpty()){
      throw new RunTimeExceptionPlaceHolder("Account have been delete");
    }
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    return JwtAuthenticationResponse
            .builder()
            .access_token(jwtTokenUtil.generateToken(new UserDetails() {
              @Override
              public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
              }

              @Override
              public String getPassword() {
                return userDAO.get().getPassword();
              }

              @Override
              public String getUsername() {
                return userDAO.get().getUserName();
              }

              @Override
              public boolean isAccountNonExpired() {
                return false;
              }

              @Override
              public boolean isAccountNonLocked() {
                return false;
              }

              @Override
              public boolean isCredentialsNonExpired() {
                return false;
              }

              @Override
              public boolean isEnabled() {
                return true;
              }
            }))
            .expires_in(JwtTokenUtil.JWT_TOKEN_VALIDITY)
            .refresh_token("")
            .build();
  }
}