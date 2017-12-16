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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchDocumentTest {

    private SearchDocument searchDocument;

    private Map<Token, Set<Document>> invertedMap;

    @Before
    public void setUp() {
        final Analyzer analyzer = new StandardAnalyzer(new StandardTokenizer(), Arrays.asList(new LowerCaseTokenFilter()));
        invertedMap = new HashMap<>();
        final Index index = new Index(invertedMap);

        searchDocument = new SearchDocument(analyzer, index);
    }

    @Test
    public void test_search_one_token_should_return_one_document() throws Exception {
        final Document document1 = new Document("alienator", "alienator jan-michael");
        final Document document2 = new Document("alien-apocalypse", "alien apocalypse");

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final Set<Document> result = searchDocument.search("alien");
        assertEquals(1, result.size());
        assertTrue(result.contains(document2));
        assertFalse(result.contains(document1));
    }

    @Test
    public void test_search_two_token_should_return_two_documents() throws Exception {
        final Document document1 = new Document("alienator", "alienator jan-michael");
        final Document document2 = new Document("alien-apocalypse", "alien apocalypse");

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final Set<Document> result = searchDocument.search(" ALIEN Jan-Michael ");
        assertEquals(2, result.size());
        assertTrue(result.contains(document2));
        assertTrue(result.contains(document1));
    }

    @Test
    public void test_search_tree_token_should_return_one_document() throws Exception {
        final Document document1 = new Document("alienator", "alienator jan-michael");
        final Document document2 = new Document("alien-apocalypse", "alien apocalypse");

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final Set<Document> result = searchDocument.search("    aPoCALYpse test @token");
        assertEquals(1, result.size());
        assertTrue(result.contains(document2));
        assertFalse(result.contains(document1));
    }

    @Test
    public void test_search_tree_token_should_return_zero_document() throws Exception {
        final Document document1 = new Document("alienator", "alienator jan-michael");
        final Document document2 = new Document("alien-apocalypse", "alien apocalypse");

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final Set<Document> result = searchDocument.search("aliens apocalypS @token");
        assertEquals(0, result.size());
        assertFalse(result.contains(document2));
        assertFalse(result.contains(document1));
    }

    @Test(expected = BusinessException.class)
    public void test_search_empty_token_should_throws_business_exception() throws Exception {
        searchDocument.search(" ");
    }

    @Test(expected = BusinessException.class)
    public void test_search_null_token_should_throws_business_exception() throws Exception {
        searchDocument.search(null);
    }

}
