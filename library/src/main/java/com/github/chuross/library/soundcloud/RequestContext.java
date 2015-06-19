package com.github.chuross.library.soundcloud;

public class RequestContext {

    private static final String BASE_URL = "https://api.soundcloud.com";
    private String clientId;

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
}
