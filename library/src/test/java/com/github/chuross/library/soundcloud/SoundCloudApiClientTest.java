package com.github.chuross.library.soundcloud;

import com.github.chuross.library.soundcloud.element.Track;
import com.github.chuross.library.soundcloud.result.TrackResult;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SoundCloudApiClientTest {

    private static final String HOST = "localhost";
    private static final int PORT = 3000;
    private MockWebServer server;
    private SoundCloudApiClient apiClient;

    @Before
    public void before() throws Exception {
        server = new MockWebServer();
        server.start(InetAddress.getByName(HOST), 3000);
        apiClient = new SoundCloudApiClient(new MockRequestContext(String.format("http://%s:%d", HOST, PORT)));
    }

    @After
    public void after() throws Exception {
        server.shutdown();
    }

    @Test
    public void トラック情報が取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/track/success.json")));

        final TrackResult result = apiClient.getTrack(12345L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/tracks/12345?client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        final Track track = result.getContent();
        assertThat(track.getId(), is(13158665L));
        assertThat(track.getCreatedAt(), is(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2011/04/06 09:00:00")));
        assertThat(track.getLastModified(), is(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2013/02/18 09:00:00")));
        assertThat(track.getUserId(), is(3699101L));
        assertThat(track.getUser().getId(), is(3699101L));
        assertThat(track.getUser().getUsername(), is("Alex Stevenson"));
        assertThat(track.getUser().getPermalink(), is("alex-stevenson"));
        assertThat(track.getUser().getPermalinkUrl(), is("http://soundcloud.com/alex-stevenson"));
        assertThat(track.getUser().getUri(), is("https://api.soundcloud.com/users/3699101"));
        assertThat(track.getUser().getAvatarUrl(), is("https://i1.sndcdn.com/avatars-000004193858-jnf2pd-large.jpg"));
        assertThat(track.getTitle(), is("Munching at Tiannas house"));
        assertThat(track.getPermalink(), is("munching-at-tiannas-house"));
        assertThat(track.getPermalinkUrl(), is("http://soundcloud.com/alex-stevenson/munching-at-tiannas-house"));
        assertThat(track.getUri(), is("https://api.soundcloud.com/tracks/13158665"));
        assertThat(track.getSharing(), is("public"));
        assertThat(track.getEmbeddableBy(), is("all"));
        assertThat(track.getArtworkUrl(), is("http://artwork"));
        assertThat(track.getPurchaseUrl(), is("http://purchase"));
        assertThat(track.getDescription(), is("description"));
        assertThat(track.getDuration(), is(18109L));
        assertThat(track.getGenre(), is("genre"));
        assertThat(track.getTagList(), is("soundcloud:source=iphone-record"));
        assertThat(track.getLabelId(), is(12345L));
        assertThat(track.getLabelName(), is("labelHoge"));
        assertThat(track.getRelease(), is(3234L));
        assertThat(track.getReleaseDay(), is(21));
        assertThat(track.getReleaseMonth(), is(5));
        assertThat(track.getReleaseYear(), is(2001));
        assertThat(track.isStreamable(), is(true));
        assertThat(track.isDownloadable(), is(true));
        assertThat(track.getState(), is("finished"));
        assertThat(track.getLicense(), is("all-rights-reserved"));
        assertThat(track.getTrackType(), is("recording"));
        assertThat(track.getWaveformUrl(), is("https://w1.sndcdn.com/fxguEjG4ax6B_m.png"));
        assertThat(track.getStreamUrl(), is("https://api.soundcloud.com/tracks/13158665/stream"));
        assertThat(track.getVideoUrl(), is("http://video"));
        assertThat(track.getBpm(), is(120));
        assertThat(track.isCommentable(), is(true));
        assertThat(track.getIsrc(), is("I123-545454"));
        assertThat(track.getKeySignature(), is("Cmaj"));
        assertThat(track.getCommentCount(), is(3));
        assertThat(track.getDownloadCount(), is(134));
        assertThat(track.getPlaybackCount(), is(5987));
        assertThat(track.getFavoritingsCount(), is(2));
        assertThat(track.getOriginalFormat(), is("m4a"));
        assertThat(track.getOriginalContentSize(), is(201483L));
    }

    private String readBody(final String filePath) throws Exception {
        return IOUtils.toString(getClass().getResourceAsStream(filePath));
    }
}
