package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"uuid"})
@ToString
public class Document {

    private final UUID uuid;

    private MetaData metaData;

}
