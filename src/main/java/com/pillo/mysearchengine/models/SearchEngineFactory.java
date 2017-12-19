package com.pillo.mysearchengine.models;

import java.util.Map;
import java.util.Set;

public class SearchEngineFactory {

    public static SearchEngine getEngine(final SearchOperator operator, final Map<Token, Set<Document>> invertedMap) {
        switch (operator) {
            case OR: {
                return new UnionEngine(invertedMap);
            }
            case AND: {
                return new IntersectionEngine(invertedMap);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

}
