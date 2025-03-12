package com.example.beprojec2.Controller;

import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Service.FileService;
import com.example.beprojec2.Service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileServiceImp fileServiceImp;
    @PostMapping("")
    public ResponseEntity<?> createFood(@RequestParam MultipartFile file) {
        ResponData responData = new ResponData();
        boolean isSuccess=fileServiceImp.saveFile(file);
        if (isSuccess) {
            responData.setData("success");
        }
        return new ResponseEntity<>(responData, HttpStatus.OK);
    }
    @GetMapping("/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        Resource resource= fileServiceImp.loadFile(filename);
        ResponData responData = new ResponData();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"").body(resource);
    }
}
