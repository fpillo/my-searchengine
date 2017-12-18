package com.pillo.mysearchengine.models;

import lombok.Getter;

import java.util.Set;

@Getter
public class SearchAction {

    private final Set<Token> tokens;

    private final SearchOperator operator;

    public SearchAction(final Set<Token> tokens, final SearchOperator operator) {
        this.tokens = tokens;
        this.operator = operator;
    }
}
