package br.com.onboarding.animeCamel.application.presentation;


import br.com.onboarding.animeCamel.application.listener.constantes.RabbitmqConstantes;
import br.com.onboarding.animeCamel.application.mapper.AnimeMapper;
import br.com.onboarding.animeCamel.application.presentation.representation.AnimeRequestRepresentation;
import br.com.onboarding.animeCamel.application.presentation.representation.AnimeResponseRepresentation;
import br.com.onboarding.animeCamel.domain.service.AnimeService;
import br.com.onboarding.animeCamel.domain.service.RabbitmqService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/")  // aqui
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    private RabbitmqService rabbitmqService;

    @GetMapping(path = "/")
    public ResponseEntity<List<AnimeResponseRepresentation>> searchAnime() {
        var animeList = animeService.findAnime().stream().toList();
        var representationList = AnimeMapper.toAnimeResponseRepresentationList(animeList);
//        return ResponseEntity.ok(representationList);
        return ResponseEntity.status(HttpStatus.OK).body(representationList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimeResponseRepresentation> searchAnimeById(@PathVariable(value = "id") Long id) {
        var animeById = animeService.findAnimeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(AnimeMapper.toRepresentation(animeById));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<AnimeResponseRepresentation> save(@RequestBody AnimeRequestRepresentation body) {
        var anime = animeService.saveAnime(AnimeMapper.toDomain(body));

        if (nonNull(anime)) {
            this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_ANIME ,anime);
            //return new ResponseEntity(HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.CREATED).body(AnimeMapper.toRepresentation(anime));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AnimeResponseRepresentation> updateAnime(
            @PathVariable(value = "id") Long id,
            @RequestBody AnimeRequestRepresentation body) {

        var animeUpdated = animeService.updateAnime(id, AnimeMapper.toDomain(body));

        return ResponseEntity.status(HttpStatus.CREATED).body(AnimeMapper.toRepresentation(animeUpdated));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable(value = "id") Long id) {
        animeService.deleteAnime(id);
        return ResponseEntity.ok().build();
    }
}