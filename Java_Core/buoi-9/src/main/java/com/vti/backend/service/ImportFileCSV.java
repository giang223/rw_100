package com.vti.backend.service;

import com.vti.dto.ImportError;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface ImportFileCSV<T, K, E> {
    void validation(String line, T context, List<K> entities, List<ImportError<E>> importErrors);
    void saveAll(List<K> entities);
    void exportFileError(List<ImportError<E>> importErrors, String pathError);

    default String importFileCSV(String pathName, T context, String pathError)
    {
        File file = new File(pathName);
        if(!file.exists())
        {
            return "File không tồn tại!";
        }
        if(!pathName.endsWith(".csv"))
        {
            return "File không đúng định dạng";
        }

        List<K> entities = new ArrayList<>();
        List<ImportError<E>> importErrors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathName)))
        {
            String line = "";
            br.readLine();
            while((line = br.readLine()) != null)
            {
                this.validation(line, context, entities, importErrors);
            }
            this.saveAll(entities);
            this.exportFileError(importErrors, pathError);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String message = "";

        if(importErrors.isEmpty())
        {
            message = "Import thành công";
        }
        if(entities.isEmpty())
        {
            message = "Import không thành công, đã xuất file lỗi " + pathError;
        }
        if(!importErrors.isEmpty() && !entities.isEmpty())
        {
            message = "Import thành công " + entities.size() + ", đã xuất file lỗi " + importErrors.size();
        }

        return message;
    }
}
