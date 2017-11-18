package com.component.image;

import org.springframework.web.multipart.MultipartFile;

public interface FileComponent {
    boolean importImage(MultipartFile file, int dir);

    String getFileName();
}
