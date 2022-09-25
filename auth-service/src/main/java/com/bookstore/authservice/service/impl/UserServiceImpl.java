package com.bookstore.authservice.service.impl;

import com.bookstore.authservice.common.exception.ErrorResponse;
import com.bookstore.authservice.common.exception.Error;
import com.bookstore.authservice.common.exception.RunTimeExceptionPlaceHolder;
import com.bookstore.authservice.dao.RoleDAO;
import com.bookstore.authservice.dao.UserDAO;
import com.bookstore.authservice.exception.SuccessCodeWithErrorResponse;
import com.bookstore.authservice.repository.RoleRepository;
import com.bookstore.authservice.repository.UserRepository;
import com.bookstore.authservice.service.UserRoleService;
import com.bookstore.authservice.service.UserService;
import com.bookstore.authservice.utils.UserStatus;
import com.bookstore.authservice.vo.request.CreateUserRequest;
import com.bookstore.authservice.vo.request.MapUserToRolesRequest;
import com.bookstore.authservice.vo.request.UpdateUserRequest;
import com.bookstore.authservice.vo.request.UpdateUserRequestFromAdmin;
import com.bookstore.authservice.vo.response.GetUserInfoResponse;
import com.bookstore.authservice.vo.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(
            BCryptPasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleService userRoleService
            ){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleService = userRoleService;
    }


    @Override
    public String createUser(CreateUserRequest createUserRequest) {

        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        if (userRepository.existsByUserName(createUserRequest.getUserName())) {
            throw new RunTimeExceptionPlaceHolder("Username is already taken!!");
        }

        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new RunTimeExceptionPlaceHolder("Email address already in use!!");
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .uuid(UUID.randomUUID())
                .errors(new ArrayList<>())
                .build();

        List<RoleDAO> validRoles = new ArrayList<>();

        createUserRequest.getRoleNames().forEach(roleName -> {
            roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> validRoles.add(role))
                    .orElse(() -> {
                        Error error = Error.builder()
                                .code("400")
                                .message(roleName + " role doesn't exist")
                                .build();
                        errorResponse.getErrors().add(error);
                    })
                    .run();
        });

        UserDAO user = UserDAO.builder()
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .password(encodedPassword)
                .roles(new HashSet<>(validRoles))
                .status(UserStatus.ACTIVE)
                .build();

        UserDAO savedUser = userRepository.save(user);

        if (!errorResponse.getErrors().isEmpty()) {
            throw new SuccessCodeWithErrorResponse(savedUser.getUserId(), errorResponse);
        }

        return savedUser.getUserId();
    }

    @Override
    public GetUserResponse getUserByUserName(String userName) {

        Optional<UserDAO> userNameOrEmailOptional = userRepository
                .findByUserNameOrEmail(userName, userName);
        UserDAO userByUserName = userNameOrEmailOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
        );

        return GetUserResponse.builder()
                .userId(userByUserName.getUserId())
                .userName(userByUserName.getUserName())
                .firstName(userByUserName.getFirstName())
                .lastName(userByUserName.getLastName())
                .email(userByUserName.getEmail())
                .roles(userByUserName.getRoles())
                .build();
    }

    @Override
    public GetUserResponse getUserByUserId(String userId) {
        Optional<UserDAO> userIdOptional = userRepository.findByUserId(userId);
        UserDAO userById = userIdOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
        );

        return GetUserResponse.builder()
                .userId(userById.getUserId())
                .userName(userById.getUserName())
                .firstName(userById.getFirstName())
                .lastName(userById.getLastName())
                .email(userById.getEmail())
                .roles(userById.getRoles())
                .build();
    }

    @Override
    public GetUserInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        GetUserResponse userByUserName = getUserByUserName(userName);

        return GetUserInfoResponse.builder()
                .userId(userByUserName.getUserId())
                .userName(userByUserName.getUserName())
                .firstName(userByUserName.getFirstName())
                .lastName(userByUserName.getLastName())
                .email(userByUserName.getEmail())
                .build();

    }

    @Override
    public void updateUserInfo(UpdateUserRequest updateUserRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        Optional<UserDAO> userNameOrEmailOptional = userRepository.findByUserNameOrEmail(userName, userName);

        UserDAO userByUserName = userNameOrEmailOptional.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
        );

        if (updateUserRequest.getFirstName() != null) {
            userByUserName.setFirstName(updateUserRequest.getFirstName());
        }
        if (updateUserRequest.getLastName() != null) {
            userByUserName.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
            userByUserName.setPassword(encodedPassword);
        }
        if (updateUserRequest.getEmail() != null) {
            userByUserName.setEmail(updateUserRequest.getEmail());
        }

        userRepository.save(userByUserName);
    }

    @Override
    public void deleteUserById(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();
        GetUserResponse userByUserId = getUserByUserId(userId);

        if (userName.equals(userByUserId.getUserName())) {
            throw new RunTimeExceptionPlaceHolder("You cannot delete your own account!");
        }

        UserDAO userDAO = userRepository.findByUserId(userId).get();
        userDAO.setStatus(UserStatus.INACTIVE);
        userRepository.save(userDAO);
    }

    @Override
    public List<GetUserResponse> getAllUsers() {

        Iterable<UserDAO> all = userRepository.findAll();
        List<GetUserResponse> allUsers = new ArrayList<>();
        all.iterator().forEachRemaining(u -> {
            GetUserResponse userResponse = GetUserResponse.builder()
                    .userId(u.getUserId())
                    .userName(u.getUserName())
                    .firstName(u.getFirstName())
                    .lastName(u.getLastName())
                    .email(u.getEmail())
                    .roles(u.getRoles())
                    .build();
            allUsers.add(userResponse);
        });

        return allUsers;
    }

    @Override
    public void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

        Optional<UserDAO> existingUser = userRepository.findByUserId(userId);

        UserDAO user = existingUser.orElseThrow(() ->
                new RunTimeExceptionPlaceHolder("UserId doesn't exist!!")
        );

        if (updateUserRequestFromAdmin.getFirstName() != null) {
            user.setFirstName(updateUserRequestFromAdmin.getFirstName());
        }
        if (updateUserRequestFromAdmin.getLastName() != null) {
            user.setLastName(updateUserRequestFromAdmin.getLastName());
        }
        if (updateUserRequestFromAdmin.getEmail() != null) {
            user.setEmail(updateUserRequestFromAdmin.getEmail());
        }

        if (user.getRoles().size() > 0) {
            MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
            List<String> existingRoles = user.getRoles().stream().map(RoleDAO::getRoleName).collect(Collectors.toList());
            mapUserToRolesRequest.setRoleNames(existingRoles);
            userRoleService.removeRolesFromUser(user.getUserName(), mapUserToRolesRequest);
        }

        if (updateUserRequestFromAdmin.getRoles().size() > 0) {
            MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
            mapUserToRolesRequest.setRoleNames(updateUserRequestFromAdmin.getRoles());
            userRoleService.mapUserToRoles(user.getUserName(), mapUserToRolesRequest);
        }

        userRepository.save(user);
    }

}