package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.ParseCnabFileUseCase;
import cnab.normalization.bycoders.domain.usecase.api.ProcessCnabUseCase;
import cnab.normalization.bycoders.domain.usecase.api.StoresCnabUseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessCnabUseCaseImpl implements ProcessCnabUseCase {

    private static final int BATCH_SIZE = 100;
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

                    Collection<List<TransactionDomain>> transationsPartitioneds = new ArrayList<>(ListUtils
                        .partition(new ArrayList<>(transactions), BATCH_SIZE));

                    transationsPartitioneds.parallelStream().forEach(storesCnabUseCase::execute);
                };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
