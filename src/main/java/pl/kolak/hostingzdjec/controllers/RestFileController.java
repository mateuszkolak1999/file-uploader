package pl.kolak.hostingzdjec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.hostingzdjec.model.File;
import pl.kolak.hostingzdjec.service.FileUploaderService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestFileController {

    private FileUploaderService fileUploaderService;

    @Autowired
    public RestFileController(FileUploaderService fileUploaderService) {
        this.fileUploaderService = fileUploaderService;
    }

    @PostMapping("/upload")
    public ResponseEntity<File> upload(@RequestBody File file, BindingResult result){
        if(!result.hasErrors()){
            fileUploaderService.uploadFile(file.getPath());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(file.getId()).toUri();
            return ResponseEntity.created(location).body(file);
        }
        throw new IllegalArgumentException("File does not save!");
    }

    @GetMapping("/gallery")
    public List<File> gallery(){
        return fileUploaderService.getListPath();
    }
}
