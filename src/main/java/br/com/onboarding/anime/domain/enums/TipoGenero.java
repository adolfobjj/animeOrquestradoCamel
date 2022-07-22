package br.com.onboarding.anime.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoGenero {

    ACAO("Acao"),
    AVENTURA("Aventura");

    private final String tipo;

}
