package br.com.onboarding.animeCamel.application.presentation;

import br.com.onboarding.animeCamel.application.mapper.AnimeMapper;
import br.com.onboarding.animeCamel.application.presentation.representation.AnimeResponseRepresentation;
import br.com.onboarding.animeCamel.domain.service.AnimeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping(path = "/")
    public ResponseEntity<List<AnimeResponseRepresentation>> searchAnime() {
        var animeList = animeService.findAnime();
        var representationList = AnimeMapper.toAnimeResponseRepresentationList(animeList);
        return ResponseEntity.ok(representationList);
    }
}
