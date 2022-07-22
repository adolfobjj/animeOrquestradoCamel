package br.com.onboarding.anime.domain.service;

import br.com.onboarding.anime.domain.domain.Anime;
import br.com.onboarding.anime.domain.port.AnimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.asm.tree.TryCatchBlockNode;

import java.util.List;

@Slf4j
public class AnimeService {
    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAnime() {
        try {
            return this.animeRepository.getAnime();
        } catch (Exception e) {
            log.error("Error when trying to GET an anime.", e);
            throw e;
        }

    }

    public Anime getAnimeById(Long id){
        try {
           return this.animeRepository.getAnimeById(id);
        } catch (Exception e) {
            log.error("Error when trying to GetById an anime.", e);
            throw e;
        }

    }

    public Anime addAnime (Anime anime){
        try {
            return this.animeRepository.addAnime(anime);
        } catch (Exception e) {
            log.error("Error when trying to save an anime.", anime, e);
            throw e;
        }

    }

    public Anime updateAnime(Long id, Anime anime) {
        try {
            return this.animeRepository.updateAnime(id, anime);
        } catch (Exception e) {
            log.error("The anime doesn't exist", e);
            throw e;
        }
    }

    public void deleteAnime(Long id) {
        try {
            this.animeRepository.deleteAnime(id);
        } catch (Exception e) {
            log.error("The anime doesn't exist", e);
            throw e;
        }
    }


}

