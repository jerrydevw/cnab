package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.core.database.repository.TransactionRepository;
import cnab.normalization.bycoders.domain.mapper.TransactionMapper;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.StoresCnabUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoresCnabUseCaseImpl implements StoresCnabUseCase {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    @Override
    public void execute(List<TransactionDomain> transacoes) {
        System.out.println("INICIANDO ARMAZENAMENTO DO ARQUIVO");
        List<TransactionEntity> transactions = transacoes.stream().map(mapper::toEntity).toList();

        repository.saveAll(transactions);
        System.out.println("FIM ARMAZENAMENTO DO ARQUIVO");
    }
}
