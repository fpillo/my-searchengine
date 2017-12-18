package com.pillo.mysearchengine.models;

public class DocumentGrouperFactory {

    public static DocumentGrouper getGrouper(final SearchOperator operator) {
        switch (operator) {
            case OR: {
                return new UnionGrouper();
            }
            case AND: {
                return new IntersectionGrouper();
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

}
