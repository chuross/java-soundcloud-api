package com.github.chuross.library.soundcloud;

public class MockRequestContext extends RequestContext {

    private String baseUrl;

    public MockRequestContext(final String baseUrl) {
        super("test");
        this.baseUrl = baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}
