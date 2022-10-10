package com.ruoyi.web.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUploadUtil {
    public boolean uploadBlobImgFile(MultipartFile file, String fileNamePath){
        try {
            OutputStream out = new FileOutputStream(fileNamePath);
            out.write(file.getBytes());
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
