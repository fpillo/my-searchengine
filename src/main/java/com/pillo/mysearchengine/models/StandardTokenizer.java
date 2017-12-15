package com.pillo.mysearchengine.models;

import java.util.LinkedHashSet;
import java.util.Set;

public class StandardTokenizer implements Tokenizer {

    @Override
    public Set<Token> tokenize(final Document document) {
        final Set<Token> tokens = new LinkedHashSet<>();
        final String [] tokenStrings = document.getText().split(" ");

        return null;
    }


}
