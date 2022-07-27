package br.com.onboarding.animeCamel.domain.service;

import br.com.onboarding.animeCamel.domain.camel.CamelContextWrapper;
import br.com.onboarding.animeCamel.domain.camel.route.AnimeRouter;
import br.com.onboarding.animeCamel.domain.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;

import java.util.List;

@Slf4j
public class AnimeService {
    private final ProducerTemplate template;

    public AnimeService(CamelContextWrapper wrapper) {
        this.template = wrapper.createProducerTemplate();
    }
    public List<Anime> findAnime() {
        return template.requestBody(AnimeRouter.ROUTE_URI, null, List.class);
    }

    public Anime findAnimeById(Long id) {
        return template.requestBody(AnimeRouter.ROUTE_URI_BY_ID, id, Anime.class);
    }


}

