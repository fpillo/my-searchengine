package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.SearchAction;
import com.pillo.mysearchengine.models.SearchRequest;
import com.pillo.mysearchengine.models.SearchResponse;
import com.pillo.mysearchengine.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchDocument {

    private final Analyzer analyzer;

    private final Index index;

    private final ValidateModel validateModel;

    @Autowired
    public SearchDocument(final Analyzer analyzer, final Index index, final ValidateModel validateModel) {
        this.analyzer = analyzer;
        this.index = index;
        this.validateModel = validateModel;
    }

    public SearchResponse search(final SearchRequest searchRequest) {
        validateModel.validate(searchRequest);
        final List<Token> tokens = analyzer.analyze(searchRequest.getQ());

        final Instant start = Instant.now();
        final SearchAction searchAction = new SearchAction(new HashSet<>(tokens), searchRequest.getOperator());
        final Set<Document> documents = index.searchDocument(searchAction);

        final Duration totalTime = Duration.between(start, Instant.now());

        return createResponse(totalTime.toMillis(), documents);
    }

    private SearchResponse createResponse(final Long totalTime, final Set<Document> documents) {
        return new SearchResponse(totalTime, documents.stream().collect(Collectors.toList()));
    }

}
