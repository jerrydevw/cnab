package cnab.normalization.bycoders.domain.usecase.api;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ParseCnabFileUseCase {
    List<TransactionDomain> execute(MultipartFile file);
}
