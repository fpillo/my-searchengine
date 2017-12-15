package com.pillo.mysearchengine.models;

import java.util.Set;
import java.util.TreeSet;

public class LowerCaseTokenFilter implements TokenFilter {

    @Override
    public Set<Token> filter(final Set<Token> tokens) {
        final Set<Token> resultTokens = new TreeSet<>();
        tokens.forEach(token -> {
            resultTokens.add(applyLowerCase(token));
        });

        return resultTokens;
    }

    private Token applyLowerCase(final Token originalToken) {
        final Token resultToken = new Token(originalToken.getValue().toLowerCase());

        return resultToken;
    }


}
