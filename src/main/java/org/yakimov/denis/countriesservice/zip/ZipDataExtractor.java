package org.yakimov.denis.countriesservice.zip;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class ZipDataExtractor {
    public Map<String, List<String>> getContent(MultipartFile file) throws IOException {
        Map<String, List<String>> result = new HashMap<>();

        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        ZipEntry entry;
        String name;
        while((entry=zis.getNextEntry())!=null){
            name = entry.getName(); // получим название файла

//            List<String> currentList = new ArrayList<>();
//            result.put(name, currentList);
//
//            entry.
//            // распаковка
//            FileOutputStream fout = new FileOutputStream("C:\\somedir\\new" + name);
//            for (int c = zin.read(); c != -1; c = zin.read()) {
//                fout.write(c);
//            }
//            fout.flush();
//            zin.closeEntry();
//            fout.close();
        }

        return null;
    }
}
