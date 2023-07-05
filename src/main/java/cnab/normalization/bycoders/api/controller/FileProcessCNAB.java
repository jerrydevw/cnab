package cnab.normalization.bycoders.api.controller;

import cnab.normalization.bycoders.domain.usecase.api.ProcessCnabUseCase;
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
    private final ProcessCnabUseCase processCnabUseCase;
    @PostMapping
    public void handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        processCnabUseCase.execute(file);
    }

}

