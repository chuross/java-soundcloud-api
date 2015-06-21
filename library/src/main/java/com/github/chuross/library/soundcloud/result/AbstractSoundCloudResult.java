package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.chuross.common.library.rest.AbstractResult;
import com.google.common.collect.ListMultimap;

import java.net.HttpURLConnection;

/**
 * error codes
 * https://developers.soundcloud.com/docs/api/guide#errors
 */
public abstract class AbstractSoundCloudResult<T> extends AbstractResult<T> {

    public AbstractSoundCloudResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final T content) {
        super(status, responseHeaders, content);
    }

    @Override
    public boolean isSuccess() {
        return !hasError() && getContent() != null;
    }

    public boolean hasError() {
        return isBadRequest() || isUnauthorized() || isForbidden() || isNotAccesible() || isUnprocessableEntity() || isTooManyRequests() || isInternalServerError() || isServiceUnavailable() || isGatewayTimeout();
    }

    public boolean isBadRequest() {
        return getStatus() == HttpURLConnection.HTTP_BAD_REQUEST;
    }

    public boolean isUnauthorized() {
        return getStatus() == HttpURLConnection.HTTP_UNAUTHORIZED;
    }

    public boolean isForbidden() {
        return getStatus() == HttpURLConnection.HTTP_FORBIDDEN;
    }

    public boolean isNotAccesible() {
        return getStatus() == HttpURLConnection.HTTP_NOT_ACCEPTABLE;
    }

    public boolean isUnprocessableEntity() {
        return getStatus() == 422;
    }

    public boolean isTooManyRequests() {
        return getStatus() == 429;
    }

    public boolean isInternalServerError() {
        return getStatus() == HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    public boolean isServiceUnavailable() {
        return getStatus() == HttpURLConnection.HTTP_UNAVAILABLE;
    }

    public boolean isGatewayTimeout() {
        return getStatus() == HttpURLConnection.HTTP_GATEWAY_TIMEOUT;
    }
}
