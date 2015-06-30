package com.github.chuross.library.soundcloud;

import com.chuross.common.library.http.DefaultHttpClient;
import com.chuross.common.library.http.HeaderElement;
import com.chuross.common.library.http.HttpClient;
import com.chuross.common.library.http.Response;
import com.chuross.common.library.rest.Method;
import com.chuross.common.library.rest.RestClient;
import com.chuross.common.library.rest.RestRequestBuilder;
import com.chuross.common.library.rest.Result;
import com.chuross.common.library.util.JsonUtils;
import com.github.chuross.library.soundcloud.element.*;
import com.github.chuross.library.soundcloud.parameter.TrackSearchFilter;
import com.github.chuross.library.soundcloud.result.*;
import com.google.common.base.Charsets;
import com.google.common.collect.ListMultimap;
import net.arnx.jsonic.TypeReference;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

public class SoundCloudApiClient extends RestClient {

    private RequestContext context;

    public SoundCloudApiClient(final RequestContext context) {
        this(context, new DefaultHttpClient());
    }

    public SoundCloudApiClient(final RequestContext context, final HttpClient<?> client) {
        super(client);
        this.context = context;
    }

    public RequestContext getContext() {
        return context;
    }

    public Observable<TokenResult> getAccessToken(final String grantType, final String code) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("oauth2/token.json"));
        builder.addParameter("client_secret", context.getClientSecret());
        builder.addParameter("grant_type", grantType);
        builder.addParameter("redirect_uri", context.getRedirectUri());
        builder.addParameter("code", code);
        return execute(Method.POST, builder, TokenResult.class, AccessToken.class, new TypeReference<AccessToken>() {
        });
    }

    public Observable<UserResult> getUser(final long userId) {
        return execute(Method.GET, new RestRequestBuilder(context.getUrl("users/%d.json", userId)), UserResult.class, User.class);
    }

    public Observable<TrackResult> getTrack(final long trackId) {
        return execute(Method.GET, new RestRequestBuilder(context.getUrl("tracks/%d.json", trackId)), TrackResult.class, Track.class);
    }

    public Observable<TracksResult> getTracks(final TrackSearchFilter filter, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("tracks.json"));
        TrackSearchFilter.setParameters(builder, filter);
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, TracksResult.class, List.class, new TypeReference<List<Track>>() {
        });
    }

    public Observable<TracksResult> getUserTracks(final long userId, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("users/%d/tracks.json", userId));
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, TracksResult.class, List.class, new TypeReference<List<Track>>() {
        });
    }

    public Observable<TracksResult> getUserFavoriteTracks(final String accessToken, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("me/favorites.json"));
        setAccessToken(builder, accessToken);
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, TracksResult.class, List.class, new TypeReference<List<Track>>() {
        });
    }

    public Observable<TracksResult> getUserFavoriteTracks(final long userId, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("users/%d/favorites.json", userId));
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, TracksResult.class, List.class, new TypeReference<List<Track>>() {
        });
    }

    public Observable<StatusResult> putUserFavoriteTrack(final String accessToken, final long trackId) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("me/favorites/%d.json", trackId));
        setAccessToken(builder, accessToken);
        return execute(Method.PUT, builder, StatusResult.class, Status.class);
    }

    public Observable<StatusResult> deleteUserFavoriteTrack(final String accessToken, final long trackId) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("me/favorites/%d.json", trackId));
        setAccessToken(builder, accessToken);
        return execute(Method.DELETE, builder, StatusResult.class, Status.class);
    }

    public Observable<PlaylistResult> getPlaylist(final long playlistId) {
        return execute(Method.GET, new RestRequestBuilder(context.getUrl("playlists/%d.json", playlistId)), PlaylistResult.class, Playlist.class);
    }

    public Observable<PlaylistsResult> getPlaylists(final String query, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("playlists.json"));
        builder.addParameterIfNotNull("q", query);
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, PlaylistsResult.class, List.class, new TypeReference<List<Playlist>>() {
        });
    }

    public Observable<PlaylistsResult> getUserPlaylists(final String accessToken, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("me/playlists.json"));
        setAccessToken(builder, accessToken);
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, PlaylistsResult.class, List.class, new TypeReference<List<Playlist>>() {
        });
    }

    public Observable<PlaylistsResult> getUserPlaylists(final long userId, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("users/%d/playlists.json", userId));
        setPagingParameters(builder, limit, offset);
        return execute(Method.GET, builder, PlaylistsResult.class, List.class, new TypeReference<List<Playlist>>() {
        });
    }

    public Observable<PlaylistResult> addUserPlaylist(final String accessToken, final String title, final String sharing) {
        return addUserPlaylist(accessToken, title, sharing, null);
    }

    public Observable<PlaylistResult> addUserPlaylist(final String accessToken, final String title, final String sharing, final List<Long> ids) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("playlists.json"));
        setAccessToken(builder, accessToken);
        builder.addParameter("playlist[title]", title);
        builder.addParameterIfNotNull("playlist[sharing]", sharing);
        if(ids != null && !ids.isEmpty()) {
            for(final Long id : ids) {
                builder.addParameterIfNotNull("playlist[tracks][][id]", id);
            }
        }
        return execute(Method.POST, builder, PlaylistResult.class, Playlist.class);
    }

    private static void setPagingParameters(final RestRequestBuilder builder, final Long limit, final Long offset) {
        builder.addParameterIfNotNull("limit", limit);
        builder.addParameterIfNotNull("offset", offset);
    }

    private static void setAccessToken(final RestRequestBuilder builder, final String accessToken) {
        builder.addRequestHeader("Authorization", HeaderElement.of("OAuth " + accessToken));
    }

    private <RESULT extends Result<?>> Observable<RESULT> execute(final Method method, final RestRequestBuilder builder, final Class<RESULT> resultClass, final Class<?> rootElementClass) {
        return execute(method, builder, resultClass, rootElementClass, null);
    }

    private <RESULT extends Result<?>, ELEMENT> Observable<RESULT> execute(final Method method, final RestRequestBuilder builder, final Class<RESULT> resultClass, final Class<?> rootElementClass, final TypeReference<ELEMENT> typeReference) {
        builder.addParameter("client_id", context.getClientId());
        return execute(method, builder.build(), getDecodeFunction(resultClass, rootElementClass, typeReference));
    }

    private static <RESULT extends Result<?>, ELEMENT> Func1<Response, RESULT> getDecodeFunction(final Class<RESULT> resultClass, final Class<?> rootElementClass, final TypeReference<ELEMENT> typeReference) {
        return new Func1<Response, RESULT>() {
            @Override
            public RESULT call(final Response response) {
                try {
                    return getResult(resultClass, rootElementClass, typeReference, response);
                } catch(final Exception e) {
                    throw new IllegalStateException("instance create failed.", e);
                }
            }
        };
    }

    private static <RESULT extends Result<?>, ELEMENT> RESULT getResult(final Class<RESULT> resultClass, final Class<?> rootElementClass, final TypeReference<ELEMENT> typeReference, final Response response) throws Exception {
        final ELEMENT element = response.getData() != null ? getElement(rootElementClass, response, typeReference) : null;
        return resultClass.getConstructor(int.class, ListMultimap.class, rootElementClass).newInstance(response.getStatus(), response.getHeaders(), element);
    }

    @SuppressWarnings("unchecked")
    private static <ELEMENT> ELEMENT getElement(final Class<?> rootElementClass, final Response response, final TypeReference<ELEMENT> typeReference) {
        if(typeReference != null) {
            return JsonUtils.decode(StringUtils.toEncodedString(response.getData(), Charsets.UTF_8), typeReference);
        } else {
            return JsonUtils.decode(StringUtils.toEncodedString(response.getData(), Charsets.UTF_8), (Class<ELEMENT>) rootElementClass);
        }
    }
}
