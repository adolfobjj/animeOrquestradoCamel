package br.com.onboarding.animeCamel.domain.port;

import br.com.onboarding.animeCamel.domain.domain.Anime;

import java.util.List;

import java.util.List;

public interface AnimeRepository {

    List<Anime> findAnime();

    Anime findAnimeById(Long id);

    Anime saveAnime(Anime anime);

    Anime updateAnime(Anime anime);

    void deleteAnime(Long id);
}