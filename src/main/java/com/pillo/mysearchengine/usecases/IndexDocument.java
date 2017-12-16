package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.IndexAction;
import com.pillo.mysearchengine.models.MetaData;
import com.pillo.mysearchengine.models.Token;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class IndexDocument {

    private final Analyzer analyzer;

    private final Index index;

    private final ValidateModel validateModel;

    public IndexDocument(final Analyzer analyzer, final Index index, final ValidateModel validateModel) {
        this.analyzer = analyzer;
        this.index = index;
        this.validateModel = validateModel;
    }

    public void index(final MetaData metaData){
        validateModel.validate(metaData);

        final List<Token> tokens = analyzer.analyze(metaData.getText());
        final Document document = createDocument(metaData);

        index.indexDocument(new IndexAction(new HashSet<>(tokens), document));
    }

    private Document createDocument(final MetaData metaData) {
        return new Document(UUID.randomUUID(), metaData);
    }

}
