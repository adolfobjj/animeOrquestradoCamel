package br.com.onboarding.anime.application.repository;

import br.com.onboarding.anime.application.mapper.AnimeMapper;
import br.com.onboarding.anime.application.repository.jpa.AnimeJpa;
import br.com.onboarding.anime.application.repository.jpa.entity.AnimeEntity;
import br.com.onboarding.anime.domain.domain.Anime;
import br.com.onboarding.anime.domain.port.AnimeRepository;
import br.com.onboarding.anime.domain.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AnimeRepositoryImpl implements AnimeRepository {

    private final AnimeJpa animeJpa;

    @Override
    public List<Anime> getAnime() {
        try {
            var animeEntity = animeJpa.findAll();
            return AnimeMapper.toDomainList(animeEntity);
        } catch (Exception e) {
            log.error("Error when trying to get an anime.", e);
            throw e;
        }
    }

    @Override
    public Anime getAnimeById(Long id) {
        var animeEntity = animeJpa.findById(id);
        if(animeEntity.isPresent()){

            return AnimeMapper.entityParaDominio(animeEntity.get());
        }
        return null;

    }

    @Override
    public Anime addAnime(Anime anime) {
        try {
            var animeEntity = animeJpa.save(AnimeMapper.paraEntity(anime));
            return AnimeMapper.entityParaDominio(animeEntity);
        } catch (Exception e) {
            log.error("Error when trying to save an anime.", anime, e);
            throw e;
        }
    }
    @Override
    public Anime updateAnime(Long id, Anime anime) throws HttpStatusCodeException {
        var animeEntityById= animeJpa.findById(id);

        if (animeEntityById.isPresent()) {
            anime.setId(animeEntityById.get().getId());
            var animeEntity = animeJpa.save(AnimeMapper.paraEntity(anime));
            return AnimeMapper.entityParaDominio(animeEntity);
        }

        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Anime doesn't exist");
    }

    @Override
    public void deleteAnime(Long id) throws HttpStatusCodeException {
        var animeToDelete = getAnimeById(id);

        if (animeToDelete != null) {
            animeJpa.deleteById(id);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Anime doesn't exist");
        }
    }


}

