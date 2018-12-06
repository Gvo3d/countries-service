package org.yakimov.denis.countriesservice.support;

import org.apache.log4j.Logger;
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.models.CountryContent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataProcessor {
    private static final Logger LOGGER = Logger.getLogger(DataProcessor.class);

    public static FileDto generateFileData(String zipArchive, String fileName, ByteArrayOutputStream data){
        FileDto newFileData = new FileDto(fileName, data.toString(), zipArchive);
        LOGGER.info(String.format(Constants.CREATED_FILE, newFileData));
        return newFileData;
    }

    public static List<CountryContent> generateCountryContent(Map<RequestDto, FileDto> requestsMap){
        List<CountryContent> results = new ArrayList<>();
        for (Map.Entry<RequestDto, FileDto> entry: requestsMap.entrySet()){
            CountryContent content = new CountryContent();
            content.setArchiveName(entry.getValue().getArchiveName());
            content.setFileContent(entry.getValue().getFileContents());
            content.setFileName(entry.getValue().getFileName());
            content.setRequestDate(new Date());
            content.setCountryName(entry.getKey().getName());
            content.setCountryCode(entry.getKey().getAlpha2Code());
            content.setStatus(entry.getKey().getStatus());
            content.setMessage(entry.getKey().getMessage());
            results.add(content);
        }
        LOGGER.info(String.format(Constants.CREATED_COUNTRIES_LIST, results));
        return results;
    }

    public static String[] getIsoCodesFromFileData(FileDto fileData){
        return fileData.getFileContents().split(Constants.LINESREGEX);
    }
}
