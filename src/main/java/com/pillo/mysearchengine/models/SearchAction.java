package com.pillo.mysearchengine.models;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchAction {

    private final List<Token> tokens;

    private final SearchOperator operator;

    public SearchAction(final List<Token> tokens, final SearchOperator operator) {
        this.tokens = tokens;
        this.operator = operator;
    }
}
