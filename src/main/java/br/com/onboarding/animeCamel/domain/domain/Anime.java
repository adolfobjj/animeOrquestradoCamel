package br.com.onboarding.animeCamel.domain.domain;

import br.com.onboarding.animeCamel.domain.enums.TipoGenero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Anime {

    private Long id;
    private String nome;
    private String nomeAutor;
    private int anoPublicacao;
    private int numeroEpisodios;
    private String sinopse;
    private TipoGenero tipoGenero;

}
