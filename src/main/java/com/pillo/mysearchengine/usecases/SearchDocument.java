package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.exceptions.BusinessException;
import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.SearchAction;
import com.pillo.mysearchengine.models.Token;

import java.util.List;
import java.util.Set;

public class SearchDocument {

    private final Analyzer analyzer;

    private final Index index;

    public SearchDocument(final Analyzer analyzer, final Index index) {
        this.analyzer = analyzer;
        this.index = index;
    }

    public Set<Document> search(final String q) {
        validate(q);
        final List<Token> tokens = analyzer.analyze(q);
        final SearchAction searchAction = new SearchAction(tokens);

        return index.searchDocument(searchAction);
    }

    private void validate(final String q) {
        if (q == null || q.trim().isEmpty()) {
            throw new BusinessException();
        }
    }

}
