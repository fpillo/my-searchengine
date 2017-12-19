package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.models.MetaData;
import com.pillo.mysearchengine.models.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Scanner;

@Service
public class LoadData {

    private static final String FILE_PATTERN = "file:%s*.txt";

    private final String filePath;

    private final ResourceLoader resourceLoader;

    private final IndexDocument indexDocument;

    @Autowired
    public LoadData(@Value("${filePath}") final String filePath, final ResourceLoader resourceLoader, final IndexDocument indexDocument) {
        this.filePath = filePath;
        this.resourceLoader = resourceLoader;
        this.indexDocument = indexDocument;
    }

    @PostConstruct
    public void load() throws Exception {
        final Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(String.format(FILE_PATTERN, filePath));

        for (final Resource resource : resources) {
            final File file = resource.getFile();
            final MetaData metaData = convertToMetaData(file);
            indexDocument.index(metaData);
        }

    }

    private MetaData convertToMetaData(final File file) throws Exception {
        final String fileName = file.getName();
        final StringBuffer stringBuffer = new StringBuffer();
        final Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            stringBuffer.append(scanner.nextLine());
        }

        return new MovieData(fileName, stringBuffer.toString());
    }

}
