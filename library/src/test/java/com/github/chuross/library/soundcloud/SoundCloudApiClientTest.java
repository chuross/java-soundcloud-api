package com.github.chuross.library.soundcloud;

import com.github.chuross.library.soundcloud.element.Playlist;
import com.github.chuross.library.soundcloud.element.Track;
import com.github.chuross.library.soundcloud.element.User;
import com.github.chuross.library.soundcloud.parameter.TrackSearchFilterBuilder;
import com.github.chuross.library.soundcloud.result.*;
import com.google.common.collect.Lists;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SoundCloudApiClientTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
    private static final String HOST = "localhost";
    private static final int PORT = 3000;
    private MockWebServer server;
    private SoundCloudApiClient apiClient;

    @Before
    public void before() throws Exception {
        server = new MockWebServer();
        server.start(InetAddress.getByName(HOST), 3000);
        final RequestContext context = new MockRequestContext(String.format("http://%s:%d", HOST, PORT));
        context.setRedirectUri("http://hoge/fuga");
        apiClient = new SoundCloudApiClient(context);
    }

    @After
    public void after() throws Exception {
        server.shutdown();
    }

    @Test
    public void アクセストークンが取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/oauth2/success.json")));

        final TokenResult result = apiClient.getAccessToken("authorization_code", "hogeHogeCode").toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("POST"));
        assertThat(request.getPath(), is("/oauth2/token.json"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        assertThat(result.getContent().getAccessToken(), is("04u7h-4cc355-70k3n"));
        assertThat(result.getContent().getScope(), is("non-expiring"));
    }

    @Test
    public void ユーザー情報が取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/user/success.json")));

        final UserResult result = apiClient.getUser(12345L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/users/12345.json?client_id=test"));

        final User user = result.getContent();
        assertThat(user.getId(), is(3207L));
        assertThat(user.getUsername(), is("Johannes Wagener"));
        assertThat(user.getFullName(), is("Johannes Wagener"));
        assertThat(user.getFirstName(), is("Johannes"));
        assertThat(user.getLastName(), is("Wagener"));
        assertThat(user.getDiscogsName(), is("discogsName"));
        assertThat(user.getCountry(), is("Germany"));
        assertThat(user.getCity(), is("Berlin"));
        assertThat(user.getPlan(), is("Free"));
        assertThat(user.getDescription(), is("description"));
        assertThat(user.getMyspaceName(), is("myspaceName"));
        assertThat(user.getPermalink(), is("jwagener"));
        assertThat(user.getPermalinkUrl(), is("http://soundcloud.com/jwagener"));
        assertThat(user.getUri(), is("https://api.soundcloud.com/users/3207"));
        assertThat(user.getWebsite(), is("http://johannes.wagener.cc"));
        assertThat(user.getWebsiteTitle(), is("johannes.wagener.cc"));
        assertThat(user.isOnline(), is(true));
        assertThat(user.getTrackCount(), is(55));
        assertThat(user.getPlaylistCount(), is(2));
        assertThat(user.getFollowersCount(), is(2346));
        assertThat(user.getFollowingsCount(), is(307));
        assertThat(user.getPublicFavoritesCount(), is(110));
        assertThat(user.getLastModified(), is(DATE_FORMAT.parse("2014/09/09 09:00:00")));
    }

    @Test
    public void トラック情報が取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/track/success.json")));

        final TrackResult result = apiClient.getTrack(12345L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/tracks/12345.json?client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        final Track track = result.getContent();
        assertThat(track.getId(), is(13158665L));
        assertThat(track.getCreatedAt(), is(DATE_FORMAT.parse("2011/04/06 09:00:00")));
        assertThat(track.getLastModified(), is(DATE_FORMAT.parse("2013/02/18 09:00:00")));
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

    @Test
    public void トラック情報の一覧を取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/common/success_list.json")));

        final TrackSearchFilterBuilder builder = new TrackSearchFilterBuilder();
        builder.setQuery("query");
        builder.setTags(Lists.newArrayList("tagA", "tagB"));
        builder.setFilter("filter");
        builder.setLicense("license");
        builder.setBpmFrom(10);
        builder.setBpmTo(20);
        builder.setDurationFrom(30);
        builder.setDurationTo(40);
        builder.setCreatedAtFrom(new Date(1000));
        builder.setCreatedAtTo(new Date(2000));
        builder.setIds(Lists.newArrayList(1L, 2L, 3L));
        builder.setGenres(Lists.newArrayList("g1", "g2"));
        builder.setTypes(Lists.newArrayList("type1", "type2"));

        final TracksResult result = apiClient.getTracks(builder.build(), 1L, 2L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/tracks.json?q=query&tags=tagA,tagB&filter=filter&license=license&bpm[from]=10&bpm[to]=20&duration[from]=30&duration[to]=40&created_at[from]=1970-01-01%2000:00:01&created_at[to]=1970-01-01%2000:00:02&ids=1,2,3&genres=g1,g2&types=type1,type2&limit=1&offset=2&client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        assertThat(result.getContent().size(), is(3));
        assertThat(result.getContent().get(0).getId(), is(57047389L));
        assertThat(result.getContent().get(1).getId(), is(46742486L));
        assertThat(result.getContent().get(2).getId(), is(46699421L));
    }

    @Test
    public void ユーザーの投稿したトラックを取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/common/success_list.json")));

        final TracksResult result = apiClient.getUserTracks(12345L, 1L, 2L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/users/12345/tracks.json?limit=1&offset=2&client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        assertThat(result.getContent().size(), is(3));
        assertThat(result.getContent().get(0).getId(), is(57047389L));
        assertThat(result.getContent().get(1).getId(), is(46742486L));
        assertThat(result.getContent().get(2).getId(), is(46699421L));
    }

    @Test
    public void ログインユーザーのお気に入り一覧を取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/common/success_list.json")));

        final TracksResult result = apiClient.getFavoriteTracks("access-token", 1L, 2L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/me/favorites.json?limit=1&offset=2&client_id=test"));
        assertThat(request.getHeader("Authorization"), is("OAuth access-token"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        assertThat(result.getContent().size(), is(3));
        assertThat(result.getContent().get(0).getId(), is(57047389L));
        assertThat(result.getContent().get(1).getId(), is(46742486L));
        assertThat(result.getContent().get(2).getId(), is(46699421L));
    }

    @Test
    public void ユーザーのお気に入り一覧を取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/common/success_list.json")));

        final TracksResult result = apiClient.getFavoriteTracks(12345L, 1L, 2L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/users/12345/favorites.json?limit=1&offset=2&client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        assertThat(result.getContent().size(), is(3));
        assertThat(result.getContent().get(0).getId(), is(57047389L));
        assertThat(result.getContent().get(1).getId(), is(46742486L));
        assertThat(result.getContent().get(2).getId(), is(46699421L));
    }

    @Test
    public void ログインユーザーのお気に入りを追加できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(201).setBody(readBody("/status/success_created.json")));

        final StatusResult result = apiClient.putFavoriteTrack("access-token", 12345L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("PUT"));
        assertThat(request.getPath(), is("/me/favorites/12345.json"));
        assertThat(request.getHeader("Authorization"), is("OAuth access-token"));

        assertThat(result.getStatus(), is(201));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getContent().getStatus(), is("201 - Created"));
    }

    @Test
    public void ログインユーザーのお気に入りを削除できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/status/success_ok.json")));

        final StatusResult result = apiClient.deleteFavoriteTrack("access-token", 12345L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("DELETE"));
        assertThat(request.getPath(), is("/me/favorites/12345.json?client_id=test"));
        assertThat(request.getHeader("Authorization"), is("OAuth access-token"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getContent().getStatus(), is("200 - OK"));
    }

    @Test
    public void プレイリストを取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/playlist/success.json")));

        final PlaylistResult result = apiClient.getPlaylist(12345L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/playlists/12345.json?client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        final Playlist playlist = result.getContent();
        assertThat(playlist.getId(), is(405726L));
        assertThat(playlist.getCreatedAt(), is(DATE_FORMAT.parse("2010/11/02 09:00:00")));
        assertThat(playlist.getLastModified(), is(DATE_FORMAT.parse("2012/06/28 09:00:00")));
        assertThat(playlist.getUserId(), is(3207L));
        assertThat(playlist.getUser().getId(), is(3207L));
        assertThat(playlist.getUser().getUsername(), is("Johannes Wagener"));
        assertThat(playlist.getUser().getPermalink(), is("jwagener"));
        assertThat(playlist.getUser().getPermalinkUrl(), is("http://soundcloud.com/jwagener"));
        assertThat(playlist.getUser().getUri(), is("https://api.soundcloud.com/users/3207"));
        assertThat(playlist.getTitle(), is("Field Recordings"));
        assertThat(playlist.getPermalink(), is("field-recordings"));
        assertThat(playlist.getPermalinkUrl(), is("http://soundcloud.com/jwagener/sets/field-recordings"));
        assertThat(playlist.getUri(), is("https://api.soundcloud.com/playlists/405726"));
        assertThat(playlist.getSharing(), is("public"));
        assertThat(playlist.getEmbeddableBy(), is("me"));
        assertThat(playlist.getPurchaseTitle(), is("purchaseTitle"));
        assertThat(playlist.getPurchaseUrl(), is("http://purchase"));
        assertThat(playlist.getArtworkUrl(), is("https://i1.sndcdn.com/artworks-000025801802-1msl1i-large.jpg"));
        assertThat(playlist.getDescription(), is("a couple of field recordings to test http://soundiverse.com."));
        assertThat(playlist.getLabelId(), is(12345L));
        assertThat(playlist.getLabelName(), is("hoge"));
        assertThat(playlist.getDuration(), is(154516L));
        assertThat(playlist.getGenre(), is("genre"));
        assertThat(playlist.getLicense(), is("all-rights-reserved"));
        assertThat(playlist.getTagList(), is("tagA \"tag B\" tagC"));
        assertThat(playlist.getTrackCount(), is(5));
        assertThat(playlist.getRelease(), is("12345"));
        assertThat(playlist.getReleaseDay(), is(15));
        assertThat(playlist.getReleaseMonth(), is(1));
        assertThat(playlist.getReleaseYear(), is(2004));
        assertThat(playlist.getSharing(), is("public"));
        assertThat(playlist.isStreamable(), is(true));
        assertThat(playlist.isDownloadable(), is(true));
        assertThat(playlist.getEan(), is("ean"));
        assertThat(playlist.getPlaylistType(), is("other"));
        assertThat(playlist.getTracks().size(), is(5));
        assertThat(playlist.getTracks().get(0).getId(), is(6621631L));
        assertThat(playlist.getTracks().get(1).getId(), is(6621549L));
        assertThat(playlist.getTracks().get(2).getId(), is(6668072L));
        assertThat(playlist.getTracks().get(3).getId(), is(6698933L));
        assertThat(playlist.getTracks().get(4).getId(), is(6770077L));
    }

    @Test
    public void プレイリストの一覧を取得できる() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(readBody("/common/success_list.json")));

        final PlaylistsResult result = apiClient.getPlaylists("hoge", 1L, 2L).toBlocking().single();

        final RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/playlists.json?q=hoge&limit=1&offset=2&client_id=test"));

        assertThat(result.getStatus(), is(200));
        assertThat(result.isSuccess(), is(true));

        assertThat(result.getContent().size(), is(3));
        assertThat(result.getContent().get(0).getId(), is(57047389L));
        assertThat(result.getContent().get(1).getId(), is(46742486L));
        assertThat(result.getContent().get(2).getId(), is(46699421L));
    }

    private String readBody(final String filePath) throws Exception {
        return IOUtils.toString(getClass().getResourceAsStream(filePath));
    }
}
