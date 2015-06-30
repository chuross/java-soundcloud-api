package com.github.chuross.library.soundcloud.result;

import com.chuross.common.library.http.HeaderElement;
import com.github.chuross.library.soundcloud.element.Playlist;
import com.google.common.collect.ListMultimap;

import java.util.List;

public class PlaylistsResult extends AbstractSoundCloudResult<List<Playlist>> {

    public PlaylistsResult(final int status, final ListMultimap<String, HeaderElement> responseHeaders, final List<Playlist> content) {
        super(status, responseHeaders, content);
    }
}
