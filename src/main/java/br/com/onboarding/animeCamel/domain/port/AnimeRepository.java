package br.com.onboarding.animeCamel.domain.port;

import br.com.onboarding.animeCamel.domain.domain.Anime;

import java.util.List;

public interface AnimeRepository {

    //Anime salvar(Anime anime);


    List<Anime> getAnime();

    Anime getAnimeById(Long id);
    Anime addAnime(Anime anime);
    Anime updateAnime(Long id, Anime anime);

    void deleteAnime(Long id);


}
