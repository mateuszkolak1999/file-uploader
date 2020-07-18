package pl.kolak.hostingzdjec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kolak.hostingzdjec.model.File;
import pl.kolak.hostingzdjec.service.FileUploaderService;

@Controller
public class FileController {

    private FileUploaderService fileUploaderService;

    @Autowired
    public FileController(FileUploaderService fileUploaderService) {
        this.fileUploaderService = fileUploaderService;
    }

    @GetMapping("/upload")
    public String form(Model model){
        model.addAttribute("image", new File());
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute(name = "image") File image, Model model){
        fileUploaderService.uploadFile(image.getPath());
        model.addAttribute("message","File added!");
        return "upload";
    }

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("filePathList", fileUploaderService.getListPath());
        return "gallery";
    }
}
