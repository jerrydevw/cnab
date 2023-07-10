package cnab.normalization.bycoders.domain.mapper;

import cnab.normalization.bycoders.core.database.entity.TransactionsBalanceEntity;
import cnab.normalization.bycoders.domain.model.TransactionsBalanceDomain;
import cnab.normalization.bycoders.domain.usecase.CalculateTransactionsBalanceUseCaseImpl;
import org.mapstruct.Mapper;

@Mapper(uses = CalculateTransactionsBalanceUseCaseImpl.class)
public interface TransactionsBalanceMapperDomain {

    TransactionsBalanceDomain toDomain(TransactionsBalanceEntity entity);
}
