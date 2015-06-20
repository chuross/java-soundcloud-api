package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.github.chuross.library.soundcloud.element.AccessToken;
import com.google.common.collect.ListMultimap;

public class TokenResult extends AbstractSoundCloudResult<AccessToken> {

    public TokenResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final AccessToken content) {
        super(status, responseHeaders, content);
    }
}
