package com.sfg.course.spring6reactivewebclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements WebClientCustomizer {

    private final String rootUrl;
    private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;

    @Autowired
    public WebClientConfig(@Value("${webclient.root-url}") String rootUrl,
            ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        this.rootUrl = rootUrl;
        this.authorizedClientManager = authorizedClientManager;
    }

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth
                = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth.setDefaultClientRegistrationId("springauth");

        webClientBuilder
                .filter(oauth)
                .baseUrl(rootUrl);
    }

}
