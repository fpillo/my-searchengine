package com.pillo.mysearchengine.models;

import java.util.LinkedList;
import java.util.List;

public class LowerCaseTokenFilter implements TokenFilter {

    @Override
    public List<Token> filter(final List<Token> tokens) {
        final List<Token> resultTokens = new LinkedList<>();
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
