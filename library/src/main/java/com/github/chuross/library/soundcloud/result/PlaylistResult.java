package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.github.chuross.library.soundcloud.element.Playlist;
import com.google.common.collect.ListMultimap;

public class PlaylistResult extends AbstractSoundCloudResult<Playlist> {

    public PlaylistResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final Playlist content) {
        super(status, responseHeaders, content);
    }
}
