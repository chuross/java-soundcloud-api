package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.chuross.common.library.rest.AbstractResult;
import com.github.chuross.library.soundcloud.element.Track;
import com.google.common.collect.ListMultimap;

public class TrackResult extends AbstractResult<Track> {

    public TrackResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final Track content) {
        super(status, responseHeaders, content);
    }
}
