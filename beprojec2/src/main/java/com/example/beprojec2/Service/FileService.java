package com.example.beprojec2.Service;

import com.example.beprojec2.Service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService implements FileServiceImp {
    @Value("${FileUpload.rootPath}")
    private String rootPath;
    private Path root;
    // khoi tao direc neu chua co
    private void init(){
        try {
            root= Paths.get(rootPath);
            if(Files.notExists(root)){
                Files.createDirectories(root);
            }
        }catch (Exception e){
            System.out.println("Error create file root: " + e.getMessage());
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        try{
            init();
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            return true;
        }catch (Exception e){
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }

    }

    @Override
    public Resource loadFile(String filename) {
        try {
            init();
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                return resource;
            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println("Error load file: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteFile(String filename) {
        try {
            init(); // Đảm bảo thư mục tồn tại
            Path file = root.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (Exception e) {
            System.out.println("Error deleting file: " + e.getMessage());
            return false;
        }
    }
}
