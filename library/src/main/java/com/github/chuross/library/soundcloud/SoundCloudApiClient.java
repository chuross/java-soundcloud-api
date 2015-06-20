package com.github.chuross.library.soundcloud;

import com.chuross.common.library.http.DefaultHttpClient;
import com.chuross.common.library.http.HttpClient;
import com.chuross.common.library.http.Response;
import com.chuross.common.library.rest.Method;
import com.chuross.common.library.rest.RestClient;
import com.chuross.common.library.rest.RestRequestBuilder;
import com.chuross.common.library.rest.Result;
import com.chuross.common.library.util.JsonUtils;
import com.github.chuross.library.soundcloud.element.Track;
import com.github.chuross.library.soundcloud.element.User;
import com.github.chuross.library.soundcloud.parameter.TrackSearchFilter;
import com.github.chuross.library.soundcloud.result.TrackResult;
import com.github.chuross.library.soundcloud.result.TracksResult;
import com.github.chuross.library.soundcloud.result.UserResult;
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

    public Observable<UserResult> getUser(final long userId) {
        return execute(Method.GET, new RestRequestBuilder(context.getUrl("users/%d", userId)), UserResult.class, User.class, new TypeReference<User>() {
        });
    }

    public Observable<TrackResult> getTrack(final long trackId) {
        return execute(Method.GET, new RestRequestBuilder(context.getUrl("tracks/%d", trackId)), TrackResult.class, Track.class, new TypeReference<Track>() {
        });
    }

    public Observable<TracksResult> getUserTracks(final long userId, final Long limit, final Long offset) {
        return getUserTracks(userId, null, limit, offset);
    }

    public Observable<TracksResult> getUserTracks(final long userId, final TrackSearchFilter filter, final Long limit, final Long offset) {
        final RestRequestBuilder builder = new RestRequestBuilder(context.getUrl("users/%d/tracks", userId));
        builder.addParameterIfNotNull("limit", limit);
        builder.addParameterIfNotNull("offset", offset);
        TrackSearchFilter.setFilterParameters(builder, filter);
        return execute(Method.GET, builder, TracksResult.class, List.class, new TypeReference<List<Track>>() {
        });
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
        if(response.getData() == null) {
            return resultClass.getConstructor(int.class, ListMultimap.class, rootElementClass).newInstance(response.getStatus(), response.getHeaders(), null);
        }
        final ELEMENT element = JsonUtils.decode(StringUtils.toEncodedString(response.getData(), Charsets.UTF_8), typeReference);
        return resultClass.getConstructor(int.class, ListMultimap.class, rootElementClass).newInstance(response.getStatus(), response.getHeaders(), element);
    }
}
