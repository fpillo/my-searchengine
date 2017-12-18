package com.pillo.mysearchengine.models;

import java.util.Set;

public interface DocumentGrouper {

    Set<Document> group(Set<Document> documents);

    Set<Document> getGrouped();

}
