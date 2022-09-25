package com.bookstore.authservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bookstore.authservice.common.util.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_client_details")
@Builder
public class OAuthClient extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "client_id", updatable = false, nullable = false)
    private String client_id;

    @Column(name = "client_secret", updatable = false, nullable = false)
    private String client_secret;
    @Column()
    private String authorized_grant_types;
    @Column
    private String authorities;
    @Column
    private String scope;
    @Column
    private String resource_ids;

    @Column
    private  String web_server_redirect_uri;

    @Column
    private String access_token_validity;

    @Column
    private String refresh_token_validity;

    @Column
    private String additional_information;

    @Column
    private String autoapprove;


}