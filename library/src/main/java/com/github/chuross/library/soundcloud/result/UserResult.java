package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.github.chuross.library.soundcloud.element.User;
import com.google.common.collect.ListMultimap;

public class UserResult extends AbstractSoundCloudResult<User> {

    public UserResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final User content) {
        super(status, responseHeaders, content);
    }
}
