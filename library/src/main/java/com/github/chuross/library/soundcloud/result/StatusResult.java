package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.github.chuross.library.soundcloud.element.Status;
import com.google.common.collect.ListMultimap;

public class StatusResult extends AbstractSoundCloudResult<Status> {

    public StatusResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final Status content) {
        super(status, responseHeaders, content);
    }
}
