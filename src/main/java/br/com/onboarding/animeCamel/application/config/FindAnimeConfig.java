package br.com.onboarding.animeCamel.application.config;

import br.com.onboarding.animeCamel.domain.camel.CamelContextWrapper;
import br.com.onboarding.animeCamel.domain.camel.route.AnimeRouter;
import br.com.onboarding.animeCamel.domain.port.AnimeRepository;
import br.com.onboarding.animeCamel.domain.service.AnimeService;
import org.apache.camel.Configuration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.bean.Bean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FindAnimeConfig {

    //bean pra acessar o endpoint do atomico
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //camel vai fazer a rota
    @Bean
    public CamelContextWrapper camelContextWrapper(RouteBuilder... routes) throws Exception {
        return new CamelContextWrapper(routes);
    }

    //router chama a implementação
    @Bean
    public AnimeRouter animeRouter(AnimeRepository animeRepository) {
        return new AnimeRouter(animeRepository);
    }

    //service
    @Bean
    public AnimeService animeService(CamelContextWrapper wrapper) {
        return new AnimeService(wrapper);
    }

}