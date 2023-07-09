package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.core.database.repository.TransactionRepository;
import cnab.normalization.bycoders.domain.exception.SaveTransactionException;
import cnab.normalization.bycoders.domain.mapper.TransactionMapperDomain;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.StoresCnabUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveCnabTransactionsUseCaseImpl implements StoresCnabUseCase {

    private final TransactionMapperDomain mapper;
    private final TransactionRepository repository;
    Logger logger = LoggerFactory.getLogger(SaveCnabTransactionsUseCaseImpl.class);

    @Override
    public void execute(List<TransactionDomain> transacoes) {
        try {
            List<TransactionEntity> transactions = transacoes.stream().map(mapper::toEntity).toList();

            repository.saveAll(transactions);
        } catch (Exception e) {
            logger.error("ERRO AO ARMAZENAR O ARQUIVO", e);
            throw new SaveTransactionException("Erro ao armazenar o arquivo", e);
        }

    }
}
