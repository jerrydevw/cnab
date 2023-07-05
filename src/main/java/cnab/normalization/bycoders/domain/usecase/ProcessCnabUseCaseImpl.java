package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.ParseCnabFileUseCase;
import cnab.normalization.bycoders.domain.usecase.api.ProcessCnabUseCase;
import cnab.normalization.bycoders.domain.usecase.api.StoresCnabUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessCnabUseCaseImpl implements ProcessCnabUseCase {

    private static final int BATCH_SIZE = 100;
    private final ParseCnabFileUseCase parseCnabFileUseCase;
    private final StoresCnabUseCase storesCnabUseCase;

    Logger logger = LoggerFactory.getLogger(ProcessCnabUseCaseImpl.class);

    @Override
    public void execute(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("Arquivo vazio");
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
