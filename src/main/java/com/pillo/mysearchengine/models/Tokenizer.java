package com.pillo.mysearchengine.models;

import java.util.Set;

public interface Tokenizer {

    Set<Token> tokenize(Document document);

}
