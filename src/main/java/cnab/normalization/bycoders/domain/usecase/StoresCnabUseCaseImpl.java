package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.core.database.repository.TransactionRepository;
import cnab.normalization.bycoders.domain.mapper.TransactionMapper;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.StoresCnabUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoresCnabUseCaseImpl implements StoresCnabUseCase {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    Logger logger = LoggerFactory.getLogger(StoresCnabUseCaseImpl.class);

    @Override
    public void execute(List<TransactionDomain> transacoes) {
        logger.info("INICIANDO ARMAZENAMENTO DO ARQUIVO");
        List<TransactionEntity> transactions = transacoes.stream().map(mapper::toEntity).toList();

        repository.saveAll(transactions);
        logger.info("FIM ARMAZENAMENTO DO ARQUIVO");
    }
}
