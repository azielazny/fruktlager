package com.fruktlager.application;

//@Singleton
public class AppConfig {
    private final String REPOSITORY_DIRECTORY_PATH = "D:/smieci/kobietydokodu.pl/fruktlager/src/main/resources/";
    private final String API_SECRET_KEY = "secretKey";

    public AppConfig() {
    }

    public String getRepositoryDirectoryPath() {
        return REPOSITORY_DIRECTORY_PATH;
    }

    public String getApiSecretKey() {
        return API_SECRET_KEY;
    }
}
