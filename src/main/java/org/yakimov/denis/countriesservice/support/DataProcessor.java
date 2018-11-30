package org.yakimov.denis.countriesservice.support;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.yakimov.denis.countriesservice.controllers.CountryController;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.FileData;
import org.yakimov.denis.countriesservice.models.ZipArchive;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataProcessor {
    private static final Logger LOGGER = Logger.getLogger(DataProcessor.class);

    public static ZipArchive generateArchive(MultipartFile file) {
        ZipArchive archive = new ZipArchive();
        archive.setArchiveName(file.getOriginalFilename());
        LOGGER.info(String.format(Constants.CREATED_ZIP, archive));
        return archive;
    }

    public static FileData generateFileData(ZipArchive zipArchive, String fileName, ByteArrayOutputStream data){
        FileData newFileData = new FileData();
        newFileData.setArchive(zipArchive);
        newFileData.setFileName(fileName);
        newFileData.setFileContent(data.toString());
        LOGGER.info(String.format(Constants.CREATED_FILE, newFileData));
        return newFileData;
    }

    public static List<CountryContent> generateCountryContent(Map<RequestDto, FileData> requestsMap){
        List<CountryContent> results = new ArrayList<>();
        for (Map.Entry<RequestDto, FileData> entry: requestsMap.entrySet()){
            CountryContent content = new CountryContent();
            content.setFileData(entry.getValue());
            content.setRequestDate(new Date());
            content.setCountryName(entry.getKey().getName());
            content.setStatus(entry.getKey().getStatus());
            content.setMessage(entry.getKey().getMessage());
            results.add(content);
        }
        LOGGER.info(String.format(Constants.CREATED_COUNTRIES_LIST, results));
        return results;
    }

    public static String[] getIsoCodesFromFileData(FileData fileData){
        return fileData.getFileContent().split(Constants.LINESREGEX);
    }
}
