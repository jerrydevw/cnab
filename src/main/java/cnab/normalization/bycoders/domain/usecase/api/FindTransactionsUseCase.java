package cnab.normalization.bycoders.domain.usecase.api;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import org.springframework.data.domain.Page;

public interface FindTransactionsUseCase {
    Page<TransactionDomain> execute(String storeName, Integer page, Integer size);
}
