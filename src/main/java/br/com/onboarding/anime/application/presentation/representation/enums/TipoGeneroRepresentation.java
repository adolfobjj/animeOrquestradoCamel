package br.com.onboarding.anime.application.presentation.representation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoGeneroRepresentation {

    ACAO("Acao"),
    AVENTURA("Aventura");

    private final String tipo;

}
