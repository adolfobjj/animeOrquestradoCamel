package br.com.onboarding.animeCamel.application.mapper;

import br.com.onboarding.animeCamel.application.presentation.representation.AnimeRequestRepresentation;
import br.com.onboarding.animeCamel.application.presentation.representation.AnimeResponseRepresentation;
import br.com.onboarding.animeCamel.domain.domain.Anime;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@UtilityClass
public class AnimeMapper {

    private Supplier<ModelMapper> modelMapperSupplier = ModelMapper::new;


    public Anime toDomain(AnimeRequestRepresentation representation) {
        return modelMapperSupplier.get().map(representation, Anime.class);
    }

    public AnimeResponseRepresentation toRepresentation(Anime anime){
        return modelMapperSupplier.get().map(anime, AnimeResponseRepresentation.class);
    }

    public List<AnimeResponseRepresentation> toAnimeResponseRepresentationList(List<Anime> animeList) {
        List<AnimeResponseRepresentation> animeRepresentationList = new ArrayList<>();
//        for (Anime anime : animeList) {
//            animeRepresentationList.add(toRepresentation(anime));
//        }
        animeList.forEach(anime -> {
            animeRepresentationList.add(toRepresentation(anime));
        });

        return animeRepresentationList;
    }

}
