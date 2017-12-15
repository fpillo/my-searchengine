package com.pillo.mysearchengine.models;

import java.util.Set;

public interface Analyzer {

    Set<Token> analyze(Document document);

}
