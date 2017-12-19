package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.exceptions.ConvertMetaDataException;
import com.pillo.mysearchengine.exceptions.LoadResourceFileException;
import com.pillo.mysearchengine.models.MetaData;
import com.pillo.mysearchengine.models.MovieData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
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
    public void load() {
        final Resource[] resources = loadResources();

        for (final Resource resource : resources) {
            try {
                final File file = resource.getFile();
                final MetaData metaData = convertToMetaData(file);
                indexDocument.index(metaData);
            } catch (final IOException e) {
                throw new ConvertMetaDataException();
            }
        }

    }

    private Resource[] loadResources() {
        try {
            return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(String.format(FILE_PATTERN, filePath));
        } catch (final IOException e) {
            throw new LoadResourceFileException();
        }
    }

    private MetaData convertToMetaData(final File file) throws IOException {
        final String fileName = file.getName();
        final StringBuffer stringBuffer = new StringBuffer();
        final Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            stringBuffer.append(scanner.nextLine());
        }

        return new MovieData(fileName, stringBuffer.toString());
    }

}
