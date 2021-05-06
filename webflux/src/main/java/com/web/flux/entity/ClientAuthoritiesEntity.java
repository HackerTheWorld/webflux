package com.web.flux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "client_authorities")
public class ClientAuthoritiesEntity {
    @Id
    private Long clientAuthoritiesId;

    private String authorities;

    private Long clientServerId;

    public Long getClientAuthoritiesId() {
        return clientAuthoritiesId;
    }

    public void setClientAuthoritiesId(Long clientAuthoritiesId) {
        this.clientAuthoritiesId = clientAuthoritiesId;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    public Long getClientServerId() {
        return clientServerId;
    }

    public void setClientServerId(Long clientServerId) {
        this.clientServerId = clientServerId;
    }
}