package com.pillo.mysearchengine.models;

import java.util.List;

public interface Analyzer {

    List<Token> analyze(String text);

}
