package org.yakimov.denis.countriesservice.zip;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.support.Constants;
import org.yakimov.denis.countriesservice.support.DataProcessor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@Component
public class ZipDataExtractor {

    public List<FileDto> getContent(MultipartFile multipartFile) throws IOException, EmptyFileException {
        if (multipartFile.isEmpty() || multipartFile.getSize()<1){
            throw new EmptyFileException();
        }

        List<FileDto> result = new ArrayList<>();

        String archiveName = multipartFile.getOriginalFilename();

        FileInputStream inputStream = null;
        File tempZipArchive = null;
        try {
            tempZipArchive = File.createTempFile(Constants.ZIPPREFIX, Constants.ZIPSUFFIX);
            multipartFile.transferTo(tempZipArchive);
            inputStream = new FileInputStream(tempZipArchive);
            ZipInputStream zis = new ZipInputStream(inputStream);

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    outputStream.write(c);
                }
                outputStream.flush();
                zis.closeEntry();
                outputStream.close();

                result.add(DataProcessor.generateFileData(archiveName, entry.getName(), outputStream));
            }

        } finally {
            inputStream.close();
            tempZipArchive.delete();
        }

        return result;
    }
}
