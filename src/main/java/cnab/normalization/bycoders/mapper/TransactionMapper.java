package cnab.normalization.bycoders.mapper;

import cnab.normalization.bycoders.domain.TransactionDomain;
import cnab.normalization.bycoders.entity.TransactionEntity;
import cnab.normalization.bycoders.service.StorageServiceImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring", uses = StorageServiceImpl.class)
public interface TransactionMapper {

    TransactionDomain toDomain(TransactionEntity entity);

    TransactionEntity toEntity(TransactionDomain domain);
}
