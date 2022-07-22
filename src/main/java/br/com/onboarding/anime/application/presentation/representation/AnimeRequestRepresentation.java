package br.com.onboarding.anime.application.presentation.representation;

import br.com.onboarding.anime.application.presentation.representation.enums.TipoGeneroRepresentation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimeRequestRepresentation {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("nomeAutor")
    private String nomeAutor;

    @JsonProperty("anoPublicacao")
    private int anoPublicacao;

    @JsonProperty("numeroEpisodios")
    private int numeroEpisodios;

    @JsonProperty("sinopse")
    private String sinopse;

    @JsonProperty("tipoGenero")
    private TipoGeneroRepresentation tipoGenero;

}
