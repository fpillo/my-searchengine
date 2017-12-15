package com.pillo.mysearchengine.models;

import java.util.List;

public interface Tokenizer {

    List<Token> tokenize(String text);

}
