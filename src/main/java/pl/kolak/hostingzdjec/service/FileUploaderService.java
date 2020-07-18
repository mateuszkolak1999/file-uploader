package pl.kolak.hostingzdjec.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kolak.hostingzdjec.model.File;
import pl.kolak.hostingzdjec.repository.FileRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileUploaderService {

    private Cloudinary cloudinary;
    private FileRepository fileRepository;

    @Autowired
    public FileUploaderService(FileRepository imageRepository) {
        this.fileRepository = imageRepository;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "cloud_name",
                "api_key", "api_key",
                "api_secret", "api_secret"));
    }

    public String uploadFile(String path){
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(path, ObjectUtils.emptyMap());
            fileRepository.save(new File(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult.get("url").toString();
    }


    public List<File> getListPath(){
        return fileRepository.findAll();
    }
}
