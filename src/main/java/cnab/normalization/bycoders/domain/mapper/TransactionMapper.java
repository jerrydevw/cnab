package cnab.normalization.bycoders.domain.mapper;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.domain.usecase.ProcessCnabUseCaseImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring", uses = ProcessCnabUseCaseImpl.class)
public interface TransactionMapper {

    TransactionDomain toDomain(TransactionEntity entity);

    TransactionEntity toEntity(TransactionDomain domain);
}
