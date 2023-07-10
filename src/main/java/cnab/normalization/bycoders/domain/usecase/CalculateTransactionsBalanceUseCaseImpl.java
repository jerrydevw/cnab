package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.core.database.repository.TransactionRepository;
import cnab.normalization.bycoders.domain.mapper.TransactionsBalanceMapperDomain;
import cnab.normalization.bycoders.domain.model.TransactionsBalanceDomain;
import cnab.normalization.bycoders.domain.usecase.api.CalculateTransactionsBalanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateTransactionsBalanceUseCaseImpl implements CalculateTransactionsBalanceUseCase {

    private final TransactionRepository transactionRepository;
    private final TransactionsBalanceMapperDomain mapper;

    @Override
    public TransactionsBalanceDomain execute(String storeName) {
        return mapper.toDomain(transactionRepository.calculateTotals(storeName));
    }
}
