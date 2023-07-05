package cnab.normalization.bycoders.controller;

import cnab.normalization.bycoders.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/cnab")
@RequiredArgsConstructor
public class FileProcessCNAB {

    private final StorageService storageService;
    @PostMapping
    public void handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        // verify if file is null
        if (file.isEmpty()) {
            System.out.println("File is empty");
        } else {
            storageService.store(file);
        }
    }

}

