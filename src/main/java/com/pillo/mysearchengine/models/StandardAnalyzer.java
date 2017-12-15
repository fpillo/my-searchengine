package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StandardAnalyzer implements Analyzer {

    private final Tokenizer tokenizer;

    private final List<TokenFilter> tokenFilters;

    @Override
    public List<Token> analyze(final String text) {
        List<Token> tokens = tokenize(text);
        tokens = filter(tokens);

        return tokens;
    }

    private List<Token> tokenize(final String text) {
        return tokenizer.tokenize(text);
    }

    private List<Token> filter(final List<Token> originalTokens) {
        List<Token> filteredTokens = new ArrayList<>(originalTokens);

        for (final TokenFilter tokenFilter : tokenFilters) {
            filteredTokens = tokenFilter.filter(filteredTokens);
        }

        return filteredTokens;
    }

}
