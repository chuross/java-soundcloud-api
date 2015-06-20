package com.github.chuross.library.soundcloud;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RequestContextTest {

    @Test
    public void 認可用のURLを取得できる() throws Exception {
        final RequestContext context = new RequestContext("test");
        context.setDisplay("display");
        context.setResponseType("code");
        context.setScope("*");

        assertThat(context.getAuthenticationUrl("http://localhost/authorization", "authorization"), is("https://soundcloud.com/connect?display=display&redirect_uri=http:%2F%2Flocalhost%2Fauthorization&state=authorization&client_id=test&response_type=code&scope=*"));
    }
}
