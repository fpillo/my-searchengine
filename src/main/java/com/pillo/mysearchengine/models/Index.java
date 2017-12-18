package com.pillo.mysearchengine.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Index {

    private final Map<Token, Set<Document>> invertedMap;

    public Index(final Map<Token, Set<Document>> invertedMap) {
        this.invertedMap = invertedMap;
    }

    public void indexDocument(final IndexAction indexAction) {
        indexAction.getTokens().forEach(token -> {
            index(token, indexAction.getDocument());
        });
    }

    public Set<Document> searchDocument(final SearchAction searchAction) {
        final SearchEngine searchEngine = SearchengineFactory.getEngine(searchAction.getOperator(), invertedMap);
        return searchEngine.searchByTokens(searchAction.getTokens());
    }

    private void index(final Token token, final Document document) {
        Set<Document> documents = invertedMap.get(token);
        if (documents == null) {
            documents = new HashSet<>();
        }

        documents.add(document);
        invertedMap.put(token, documents);
    }

}
