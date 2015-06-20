package com.github.chuross.library.soundcloud;

import com.chuross.common.library.util.CollectionUtils;
import com.chuross.common.library.util.HttpClientUtils;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import org.apache.commons.lang3.StringUtils;

public class RequestContext {

    private static final String BASE_URL = "https://api.soundcloud.com";
    private static final String OAUTH_BASE_URL = "https://soundcloud.com/connect";
    private String clientId;
    private String clientSecret;
    private String redirectUri;

    public RequestContext(final String clientId, final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        if(redirectUri == null) {
            throw new IllegalStateException("redirect uri is null");
        }
        return redirectUri;
    }

    public void setRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getUrl(final String path, final Object... args) {
        return getUrl(String.format(path, args));
    }

    public String getUrl(final String path) {
        return String.format("%s/%s", getBaseUrl(), path.startsWith("/") ? path.substring(1) : path);
    }

    public String getAuthenticationUrl(final String responseType, final String scope, final String state, final String display) {
        return OAUTH_BASE_URL + "?" + getOAuthParameterString(responseType, scope, state, display);
    }

    private String getOAuthParameterString(final String responseType, final String scope, final String state, final String display) {

        final String responseTypeParameter = StringUtils.isBlank(responseType) ? "code" : responseType;
        final String scopeParameter = StringUtils.isBlank(scope) ? "non-expiring" : scope;

        final ListMultimap<String, Object> parameters = LinkedListMultimap.create();
        CollectionUtils.putIfNotNull(parameters, "display", display);
        parameters.put("redirect_uri", redirectUri);
        parameters.put("state", state);
        parameters.put("client_id", clientId);
        parameters.put("response_type", responseTypeParameter);
        parameters.put("scope", scopeParameter);
        return HttpClientUtils.toQueryString(parameters);
    }
}
