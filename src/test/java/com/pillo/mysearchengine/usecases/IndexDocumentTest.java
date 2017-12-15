package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
public class IndexDocumentTest {

    private IndexDocument indexDocument;

    private Index index;

    private Map<Token, Set<Document>> invertedMap;

    @Before
    public void setUp() {
        invertedMap = new HashMap<>();
        index = new Index(invertedMap);
        indexDocument = new IndexDocument(index);
    }

    @Test
    public void test_index_one_document() throws Exception {
        final Document document = new Document("alienator", "alienator 1990 jan-michael vincent john phillip law teagan clive fred olen ray paul garson jeffrey c. hogue");

        indexDocument.index(document);

        Assert.assertEquals(1, invertedMap.get(new Token("alienator")).size());

    }

}
