package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.exceptions.BusinessException;
import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.LowerCaseTokenFilter;
import com.pillo.mysearchengine.models.StandardAnalyzer;
import com.pillo.mysearchengine.models.StandardTokenizer;
import com.pillo.mysearchengine.models.Token;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class IndexDocumentTest {

    private IndexDocument indexDocument;

    private Map<Token, Set<Document>> invertedMap;

    @Before
    public void setUp() {
        final Analyzer analyzer = new StandardAnalyzer(new StandardTokenizer(), Arrays.asList(new LowerCaseTokenFilter()));
        invertedMap = new HashMap<>();
        final Index index = new Index(invertedMap);
        final ValidateModel validateModel = new ValidateModel(Validation.buildDefaultValidatorFactory().getValidator());
        indexDocument = new IndexDocument(analyzer, index, validateModel);
    }

    @Test
    public void test_index_one_document() throws Exception {
        final Document document = new Document("alienator", "alienator 1990 jan-michael vincent john phillip law teagan clive fred olen ray paul garson jeffrey c. hogue");

        indexDocument.index(document);

        assertEquals(1, invertedMap.get(new Token("alienator")).size());
    }

    @Test
    public void test_index_three_document() throws Exception {
        final Document document1 = new Document("alien-apocalypse", "alien apocalypse 2005 bruce campbell renee o'connor michael cory davis josh becker josh becker");
        final Document document2 = new Document("alien-origin", "alien origin 2012 trey mccurley chelsea vincent daniela flynn mark atkins o'connor");
        final Document document3 = new Document("alien-terminator", "alien terminator 1996 maria ford rodger halston lisa boyle dave payne mike elliott mike upton o'connor");

        indexDocument.index(document1);
        indexDocument.index(document2);
        indexDocument.index(document3);

        assertEquals(3, invertedMap.get(new Token("alien")).size());
    }

    @Test(expected = BusinessException.class)
    public void test_index_empty_name_document() throws Exception {
        final Document document = new Document("", "alienator 1990 jan-michael vincent john phillip law teagan clive fred olen ray paul garson jeffrey c. hogue");

        indexDocument.index(document);
    }

    @Test(expected = BusinessException.class)
    public void test_index_empty_text_document() throws Exception {
        final Document document = new Document("alienator", "");

        indexDocument.index(document);
    }

}
