package com.pillo.mysearchengine.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnionEngine implements SearchEngine {

    private final Map<Token, Set<Document>> invertedMap;

    public UnionEngine(final Map<Token, Set<Document>> invertedMap) {
        this.invertedMap = invertedMap;
    }

    @Override
    public Set<Document> searchByTokens(final Set<Token> tokens) {
        final Set<Document> result = new HashSet<>();
        for (final Token token : tokens) {
            final Set<Document> documents = invertedMap.getOrDefault(token, new HashSet<>());
            if (!documents.isEmpty()) {
                result.addAll(documents);
            }

        }

        return result;
    }

}
