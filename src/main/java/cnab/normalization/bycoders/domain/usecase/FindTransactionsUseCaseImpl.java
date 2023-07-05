package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.core.database.repository.TransactionRepository;
import cnab.normalization.bycoders.domain.mapper.TransactionMapper;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.FindTransactionsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTransactionsUseCaseImpl implements FindTransactionsUseCase {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;

    @Override
    public Page<TransactionDomain> execute(String storeName, Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return repository.findAllByNameStore(storeName, pageable).map(mapper::toDomain);
    }
}
