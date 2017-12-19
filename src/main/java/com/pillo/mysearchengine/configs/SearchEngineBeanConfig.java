package com.pillo.mysearchengine.configs;

import com.pillo.mysearchengine.models.Analyzer;
import com.pillo.mysearchengine.models.Index;
import com.pillo.mysearchengine.models.LowerCaseTokenFilter;
import com.pillo.mysearchengine.models.StandardAnalyzer;
import com.pillo.mysearchengine.models.StandardTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class SearchEngineBeanConfig {

    @Bean
    public Index index() {
        return new Index(new HashMap<>());
    }

    @Bean
    public Analyzer analyzer() {
        return new StandardAnalyzer(new StandardTokenizer(), Arrays.asList(new LowerCaseTokenFilter()));
    }

}
