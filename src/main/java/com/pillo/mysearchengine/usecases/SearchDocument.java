package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.SearchAction;
import com.pillo.mysearchengine.models.SearchRequest;
import com.pillo.mysearchengine.models.Token;

import java.util.List;
import java.util.Set;

public class SearchDocument {

    private final Analyzer analyzer;

    private final Index index;

    private final ValidateModel validateModel;

    public SearchDocument(final Analyzer analyzer, final Index index, final ValidateModel validateModel) {
        this.analyzer = analyzer;
        this.index = index;
        this.validateModel = validateModel;
    }

    public Set<Document> search(final SearchRequest searchRequest) {
        validateModel.validate(searchRequest);
        final List<Token> tokens = analyzer.analyze(searchRequest.getQ());
        final SearchAction searchAction = new SearchAction(tokens);

        return index.searchDocument(searchAction);
    }

}
