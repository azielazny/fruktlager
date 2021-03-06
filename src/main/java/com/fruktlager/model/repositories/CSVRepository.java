package com.fruktlager.model.repositories;

import java.io.File;

public interface CSVRepository {
    default boolean fileExists(String pathToFile) {
        File file = new File(pathToFile);
        return file.exists() && file.canWrite();
    }
}
