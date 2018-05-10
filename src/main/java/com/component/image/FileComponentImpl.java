package com.component.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component("FileComponent")
public class FileComponentImpl implements FileComponent {

    private static final String RESOURCE = "/resources/statics/img/";

    private String webappRoot;
    private MultipartFile file;

    @Autowired
    public FileComponentImpl(ServletContext context) {
        webappRoot = context.getRealPath("/");
    }

    public boolean importImage(MultipartFile file, int dir) {
        try {
            if (file.isEmpty()) {
                return false;
            }

            this.file = file;

            saveFile(getDir(webappRoot + RESOURCE + dir));
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public String getFileName() {
        if (file.isEmpty()) {
            return "";
        }

        return file.getOriginalFilename();
    }

    private String getDir(String dirFile) {
        File dir = new File(dirFile);

        if (!dir.exists()) {
            dir.mkdir();
        }

        return dir.getAbsolutePath();
    }

    private void saveFile(String dirPath) throws IOException {
        File newFile = new File(dirPath + "/" + getFileName());

        BufferedOutputStream buffStream =
                new BufferedOutputStream(new FileOutputStream(newFile));

        byte[] bytes = file.getBytes();
        buffStream.write(bytes);

        buffStream.close();
    }
}
