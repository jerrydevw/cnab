package cnab.normalization.bycoders.domain.usecase.api;

import cnab.normalization.bycoders.domain.model.TransactionsBalanceDomain;

public interface CalculateTransactionsBalanceUseCase {
    TransactionsBalanceDomain execute(String storeName);
}
