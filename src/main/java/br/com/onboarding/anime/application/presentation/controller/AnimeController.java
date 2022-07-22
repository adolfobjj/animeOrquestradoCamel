package br.com.onboarding.anime.application.presentation.controller;

import br.com.onboarding.anime.application.mapper.AnimeMapper;
import br.com.onboarding.anime.application.presentation.representation.AnimeRequestRepresentation;
import br.com.onboarding.anime.application.presentation.representation.AnimeResponseRepresentation;
import br.com.onboarding.anime.application.repository.jpa.entity.AnimeEntity;
import br.com.onboarding.anime.domain.domain.Anime;
import br.com.onboarding.anime.domain.service.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/v1/animes")
@RequiredArgsConstructor
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }


    @PostMapping(path = "/salvar")
    public ResponseEntity<AnimeResponseRepresentation> addAnime(@RequestBody AnimeRequestRepresentation body) {
        var anime = animeService.addAnime(AnimeMapper.paraDominio(body));
        if (nonNull(anime)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(AnimeMapper.paraRepresentacao(anime));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping(path = "/buscar")
    public ResponseEntity<List<AnimeResponseRepresentation>> getAnime(){
        var animeList = animeService.getAnime();
        var animeRepresentationList = AnimeMapper.toAnimeResponseRepresentationList(animeList);
        return  ResponseEntity.ok(animeRepresentationList);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<AnimeResponseRepresentation> getAnimeById(@PathVariable(value = "id") Long id){
        var anime = animeService.getAnimeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(AnimeMapper.paraRepresentacao(anime));
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<AnimeResponseRepresentation> updateAnime(
            @PathVariable(value = "id") Long id,
            @RequestBody AnimeRequestRepresentation body) {

        var animeUpdated = animeService.updateAnime(id, AnimeMapper.paraDominio(body));

        return ResponseEntity.status(HttpStatus.CREATED).body(AnimeMapper.paraRepresentacao(animeUpdated));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable(value = "id") Long id) {
        animeService.deleteAnime(id);
        return ResponseEntity.ok().build();
    }

}
