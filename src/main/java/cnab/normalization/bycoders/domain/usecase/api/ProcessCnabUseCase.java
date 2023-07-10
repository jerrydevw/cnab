package cnab.normalization.bycoders.domain.usecase.api;

import org.springframework.web.multipart.MultipartFile;

public interface ProcessCnabUseCase {
    void execute(MultipartFile file);
}
