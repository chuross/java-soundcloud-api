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
    private String responseType;
    private String scope;
    private String display;

    public RequestContext(final String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
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

    public String getAuthenticationUrl(final String redirectUri, final String state) {
        return OAUTH_BASE_URL + "?" + getOAuthParameterString(redirectUri, state);
    }

    private String getOAuthParameterString(final String redirectUri, final String state) {
        final ListMultimap<String, Object> parameters = LinkedListMultimap.create();
        CollectionUtils.putIfNotNull(parameters, "display", display);
        parameters.put("redirect_uri", redirectUri);
        parameters.put("state", state);
        parameters.put("client_id", clientId);
        parameters.put("response_type", getResponseType());
        parameters.put("scope", getScope());
        return HttpClientUtils.toQueryString(parameters);
    }

    public String getResponseType() {
        return StringUtils.isBlank(responseType) ? "code" : responseType;
    }

    public void setResponseType(final String responseType) {
        this.responseType = responseType;
    }

    public String getScope() {
        return StringUtils.isBlank(scope) ? "non-expiring" : scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(final String display) {
        this.display = display;
    }
}
