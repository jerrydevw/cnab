package cnab.normalization.bycoders.domain.usecase.api;

import cnab.normalization.bycoders.domain.model.TransactionDomain;

import java.util.List;

public interface StoresCnabUseCase {
    void execute(List<TransactionDomain> transacoes);
}
