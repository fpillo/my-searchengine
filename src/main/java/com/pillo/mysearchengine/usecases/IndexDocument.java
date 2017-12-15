package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.IndexAction;
import com.pillo.mysearchengine.models.Token;

import java.util.HashSet;
import java.util.List;

public class IndexDocument {

    private final Analyzer analyzer;

    private final Index index;

    private final ValidateModel validateModel;

    public IndexDocument(final Analyzer analyzer, final Index index, final ValidateModel validateModel) {
        this.analyzer = analyzer;
        this.index = index;
        this.validateModel = validateModel;
    }

    public void index(final Document document){
        validateModel.validate(document);
        final List<Token> tokens = analyzer.analyze(document.getText());
        index.indexDocument(new IndexAction(new HashSet<>(tokens), document));
    }

}
