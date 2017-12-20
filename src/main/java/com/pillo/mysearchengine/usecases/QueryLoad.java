package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.SearchOperator;
import com.pillo.mysearchengine.models.SearchRequest;
import com.pillo.mysearchengine.models.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QueryLoad implements CommandLineRunner {

    private final SearchDocument searchDocument;

    @Autowired
    public QueryLoad(final SearchDocument searchDocument) {
        this.searchDocument = searchDocument;
    }

    @Override
    public void run(final String ... strings) throws Exception {
        final SearchRequest searchRequest = new SearchRequest(strings[0], SearchOperator.AND);
        final SearchResponse searchResponse = searchDocument.search(searchRequest);
    }
}
