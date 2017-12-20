package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.SearchOperator;
import com.pillo.mysearchengine.models.SearchRequest;
import com.pillo.mysearchengine.models.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueryLoad {

    private final SearchDocument searchDocument;

    private final ApplicationArguments args;

    @Autowired
    public QueryLoad(final SearchDocument searchDocument, final ApplicationArguments args) {
        this.searchDocument = searchDocument;
        this.args = args;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() throws Exception {
        final String q = args.getNonOptionArgs().get(0);
        final SearchRequest searchRequest = new SearchRequest(q, SearchOperator.AND);
        final SearchResponse searchResponse = searchDocument.search(searchRequest);

        System.out.println(searchResponse.toString());
    }

}
