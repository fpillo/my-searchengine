package com.pillo.mysearchengine.models;

import java.util.Set;

public interface SearchEngine {

    Set<Document> searchByTokens(Set<Token> tokens);

}
