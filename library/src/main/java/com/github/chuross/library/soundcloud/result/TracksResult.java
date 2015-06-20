package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.github.chuross.library.soundcloud.element.Track;
import com.google.common.collect.ListMultimap;

import java.util.List;

public class TracksResult extends AbstractSoundCloudResult<List<Track>> {

    public TracksResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final List<Track> content) {
        super(status, responseHeaders, content);
    }
}
