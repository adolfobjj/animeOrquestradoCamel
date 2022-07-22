package br.com.onboarding.anime.application.config;

import br.com.onboarding.anime.domain.port.AnimeRepository;
import br.com.onboarding.anime.domain.service.AnimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimeConfig {

    @Bean
    public AnimeService animeService(AnimeRepository animeRepository) {
        return new AnimeService(animeRepository);
    }

}
