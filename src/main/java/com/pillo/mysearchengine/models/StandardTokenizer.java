package com.pillo.mysearchengine.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StandardTokenizer implements Tokenizer {

    @Override
    public List<Token> tokenize(final String text) {
        final List<Token> tokens = new LinkedList<>();
        final String normalized = text.trim();

        if (normalized.isEmpty()) {
            return Collections.emptyList();
        }

        final String [] tokenStrings = normalized.split("\\s+");
        for (final String tokenString : tokenStrings) {
            tokens.add(new Token(tokenString));
        }

        return tokens;
    }

}
