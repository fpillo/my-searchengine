package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.exceptions.BusinessException;
import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Document;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.LowerCaseTokenFilter;
import com.pillo.mysearchengine.models.MovieData;
import com.pillo.mysearchengine.models.SearchOperator;
import com.pillo.mysearchengine.models.SearchRequest;
import com.pillo.mysearchengine.models.SearchResponse;
import com.pillo.mysearchengine.models.StandardAnalyzer;
import com.pillo.mysearchengine.models.StandardTokenizer;
import com.pillo.mysearchengine.models.Token;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
        final ValidateModel validateModel = new ValidateModel(Validation.buildDefaultValidatorFactory().getValidator());

        searchDocument = new SearchDocument(analyzer, index, validateModel);
    }

    @Test
    public void test_search_and_operator_one_token_should_return_zero_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("zero", SearchOperator.AND));
        final List<Document> result = response.getDocuments();

        assertEquals(0, result.size());
        assertFalse(result.contains(document1));
        assertFalse(result.contains(document2));
    }

    @Test
    public void test_search_and_operator_one_token_should_return_one_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("alien", SearchOperator.AND));
        final List<Document> result = response.getDocuments();

        assertEquals(1, result.size());
        assertFalse(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_and_operator_two_token_should_return_zero_documents() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest(" ALIEN Jan-Michael ", SearchOperator.AND));
        final List<Document> result = response.getDocuments();

        assertEquals(0, result.size());
        assertFalse(result.contains(document1));
        assertFalse(result.contains(document2));
    }

    @Test
    public void test_search_and_operator_two_token_should_return_one_documents() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest(" ALIEN APOCALypse", SearchOperator.AND));
        final List<Document> result = response.getDocuments();

        assertEquals(1, result.size());
        assertFalse(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_and_operator_two_token_should_return_two_documents() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael alieN");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse jan-MICHAEL");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document1, document2)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1, document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("JAN-michael aliEn", SearchOperator.AND));
        final List<Document> result = response.getDocuments();

        assertEquals(2, result.size());
        assertTrue(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_and_operator_three_token_should_return_zero_documents() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael alieN");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse jan-MICHAEL");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document1, document2)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1, document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("aliens alienators jan michael", SearchOperator.AND));
        final List<Document> result = response.getDocuments();

        assertEquals(0, result.size());
        assertFalse(result.contains(document1));
        assertFalse(result.contains(document2));
    }

    @Test
    public void test_search_or_operator_one_token_should_return_zero_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("token", SearchOperator.OR));
        final List<Document> result = response.getDocuments();

        assertEquals(0, result.size());
        assertFalse(result.contains(document1));
        assertFalse(result.contains(document2));
    }

    @Test
    public void test_search_or_operator_one_token_should_return_one_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("alien", SearchOperator.OR));
        final List<Document> result = response.getDocuments();

        assertEquals(1, result.size());
        assertFalse(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_or_operator_one_token_should_return_two_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael alien");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document1, document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("alien", SearchOperator.OR));
        final List<Document> result = response.getDocuments();

        assertEquals(2, result.size());
        assertTrue(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_or_operator_two_token_should_return_two_documents() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest(" ALIEN Jan-Michael ", SearchOperator.OR));
        final List<Document> result = response.getDocuments();

        assertEquals(2, result.size());
        assertTrue(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_or_operator_tree_token_should_return_one_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("    aPoCALYpse test @token", SearchOperator.OR));
        final List<Document> result = response.getDocuments();

        assertEquals(1, result.size());
        assertFalse(result.contains(document1));
        assertTrue(result.contains(document2));
    }

    @Test
    public void test_search_or_operator_tree_token_should_return_zero_document() throws Exception {
        final MovieData movieData1 = new MovieData("alienator", "alienator jan-michael");
        final Document document1 = new Document(UUID.fromString("eff791d1-0e75-4ed9-a18f-e68f06c4d889"), movieData1);

        final MovieData movieData2 = new MovieData("alien-apocalypse", "alien apocalypse");
        final Document document2 = new Document(UUID.fromString("5fe89ff8-2b59-44f3-b0d7-34ccb3c1bd59"), movieData2);

        invertedMap.put(new Token("alienator"), new HashSet<>(Arrays.asList(document1)));
        invertedMap.put(new Token("jan-michael"), new HashSet<>(Arrays.asList(document1)));

        invertedMap.put(new Token("alien"), new HashSet<>(Arrays.asList(document2)));
        invertedMap.put(new Token("apocalypse"), new HashSet<>(Arrays.asList(document2)));

        final SearchResponse response = searchDocument.search(new SearchRequest("aliens apocalypS @token", SearchOperator.OR));
        final List<Document> result = response.getDocuments();

        assertEquals(0, result.size());
        assertFalse(result.contains(document1));
        assertFalse(result.contains(document2));
    }

    @Test(expected = BusinessException.class)
    public void test_search_empty_token_should_throws_business_exception() throws Exception {
        searchDocument.search(new SearchRequest(" ", SearchOperator.OR));
    }

    @Test(expected = BusinessException.class)
    public void test_search_null_token_should_throws_business_exception() throws Exception {
        searchDocument.search(new SearchRequest(null, SearchOperator.OR));
    }

}
