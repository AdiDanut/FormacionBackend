package com.example.block11uploaddownloadfiles.Controller;


import com.example.block11uploaddownloadfiles.Entity.FicheroEntity;
import com.example.block11uploaddownloadfiles.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<FicheroEntity> uploadFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("categoria") String categoria) throws IOException {
        FicheroEntity fichero = fileStorageService.storeFile(file, categoria);
        return ResponseEntity.ok(fichero);
    }

    @GetMapping("/setpath")
    public ResponseEntity<String> setFilePath(@RequestParam("path") String path) {
        fileStorageService.setUploadPath(path);
        return ResponseEntity.ok("Directorio de guardado actualizado: " + path);
    }

    @GetMapping("/downloadById")
    public ResponseEntity<Resource> downloadFileById(@RequestParam("id") Long id) throws ChangeSetPersister.NotFoundException {
        Resource resource = fileStorageService.downloadFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/downloadByName")
    public ResponseEntity<Resource> downloadFileByName(@RequestParam("nombre") String nombre) throws ChangeSetPersister.NotFoundException {
        Resource resource = fileStorageService.downloadFileByName(nombre);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
