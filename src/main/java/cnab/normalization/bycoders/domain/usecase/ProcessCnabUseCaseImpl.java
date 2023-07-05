package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.ParseCnabFileUseCase;
import cnab.normalization.bycoders.domain.usecase.api.ProcessCnabUseCase;
import cnab.normalization.bycoders.domain.usecase.api.StoresCnabUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ProcessCnabUseCaseImpl implements ProcessCnabUseCase {

    private final ParseCnabFileUseCase parseCnabFileUseCase;
    private final StoresCnabUseCase storesCnabUseCase;

    @Override
    public void execute(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            System.out.println("File is empty");
            return;
        }

        Runnable runnable = () -> {
                    List<TransactionDomain> transactions = parseCnabFileUseCase.execute(multipartFile);
                    storesCnabUseCase.execute(transactions);
                };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
