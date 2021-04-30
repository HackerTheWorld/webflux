package com.web.flux.entity;

public class ClientAuthoritiesEntity {
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