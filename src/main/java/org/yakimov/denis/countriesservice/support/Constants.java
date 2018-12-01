package org.yakimov.denis.countriesservice.support;

public class Constants {
    public static final String ZIPPREFIX = "upload-";
    public static final String ZIPSUFFIX = ".zip";
    public static final String REQUESTING = "Requesting: %s";
    public static final String PROCESSING = "Processing request for file: %s";
    static final String CREATED_ZIP = "Created ZipArchive model: %s";
    static final String CREATED_FILE = "Created FileData model: %s";
    static final String CREATED_COUNTRIES_LIST = "Created CountriesContentlist: %s";
    static final String RESTRESPONSE = "RestResponse";
    static final String MESSAGES = "messages";
    static final String RESULT = "result";
    static final String NAME = "name";
    static final String ALPHACODE = "alpha2_code";
    static final String LINESREGEX = "[\\r\\n]+";
    public static final int CONNECT_TIMEOUT = 120000;
    public static final String UTF_8 = "UTF-8";
    public static final String ERROR = "Error: %s";
    public static final String UNKNOWN = "Unknown error!";
    public static final String EMPTY_FILE = "Zip file was empty, can't process that file";
    public static final long SIZE = 10;
    public static final String FILE_PARAM= "file";
    public static final String SESSION_MARKER = "session";
    public static final String URL = "/countries/{"+SESSION_MARKER+"}";
    public static final String SESSION_URL = "/session";
}
