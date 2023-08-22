package com.example.block11uploaddownloadfiles.Service;

import com.example.block11uploaddownloadfiles.Entity.FicheroEntity;
import com.example.block11uploaddownloadfiles.Repository.FicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;

@Service
public class FileStorageService {

    private String uploadPath = "";

    @Autowired
    private FicheroRepository ficheroRepository;

    public FicheroEntity storeFile(MultipartFile file, String categoria) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.substring(fileName.indexOf(".") + 1).equals(categoria)) {
            Path filePath = Paths.get(uploadPath).resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            FicheroEntity fichero = new FicheroEntity();
            fichero.setName(fileName);
            fichero.setFechaSubida(new Date());
            fichero.setCategoria(categoria);
            return ficheroRepository.save(fichero);
        }
        else {
            throw new IOException();
        }
    }

    public void setUploadPath(String newUploadPath) {
        uploadPath = newUploadPath;
    }

    @Transactional(readOnly = true)
    public Resource downloadFileById(Long id) throws ChangeSetPersister.NotFoundException {
        FicheroEntity fichero = ficheroRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Path filePath = Paths.get(uploadPath).resolve(fichero.getName());
        return new FileSystemResource(filePath.toFile());
    }

    @Transactional(readOnly = true)
    public Resource downloadFileByName(String nombre) throws ChangeSetPersister.NotFoundException {
        FicheroEntity fichero = ficheroRepository.findByName(nombre)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Path filePath = Paths.get(uploadPath).resolve(fichero.getName());
        return new FileSystemResource(filePath.toFile());
    }
}
