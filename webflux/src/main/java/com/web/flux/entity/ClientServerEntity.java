package com.web.flux.entity;

import java.util.List;

public class ClientServerEntity {
    private Integer clientServerId;

    private String clientId;

    private String resourceIds;

    private Byte secretRequire;

    private Byte scopeRequire;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private List<ClientAuthoritiesEntity> authorities;

    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<ClientAuthoritiesEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<ClientAuthoritiesEntity> authorities) {
        this.authorities = authorities;
    }

    public Integer getClientServerId() {
        return clientServerId;
    }

    public void setClientServerId(Integer clientServerId) {
        this.clientServerId = clientServerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Byte getSecretRequire() {
        return secretRequire;
    }

    public void setSecretRequire(Byte secretRequire) {
        this.secretRequire = secretRequire;
    }

    public Byte getScopeRequire() {
        return scopeRequire;
    }

    public void setScopeRequire(Byte scopeRequire) {
        this.scopeRequire = scopeRequire;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri == null ? null : webServerRedirectUri.trim();
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
}