package com.pillo.mysearchengine.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntersectionEngine implements SearchEngine {

    private final Map<Token, Set<Document>> invertedMap;

    public IntersectionEngine(final Map<Token, Set<Document>> invertedMap) {
        this.invertedMap = invertedMap;
    }

    @Override
    public Set<Document> searchByTokens(final Set<Token> tokens) {
        Set<Document> result = new HashSet<>();
        boolean firstRun = true;

        for (final Token token : tokens) {
            final Set<Document> documents = invertedMap.getOrDefault(token, new HashSet<>());
            if (documents.isEmpty()) {
                return new HashSet<>();
            }

            if (firstRun) {
                result.addAll(documents);
                firstRun = false;
            } else {
                result.retainAll(documents);
                if (result.isEmpty()) {
                    return result;
                }
            }

        }

        return result;
    }
}
