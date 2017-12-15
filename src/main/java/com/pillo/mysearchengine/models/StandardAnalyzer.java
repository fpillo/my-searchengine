package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class StandardAnalyzer implements Analyzer {

    private final Tokenizer tokenizer;

    private final List<TokenFilter> tokenFilters;

    @Override
    public Set<Token> analyze(final Document document) {
        return null;
    }
}
