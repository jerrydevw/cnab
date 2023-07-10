package cnab.normalization.bycoders.api.mapper;

import cnab.normalization.bycoders.api.controller.FileProcessCNABController;
import cnab.normalization.bycoders.api.controller.TransactionsBalanceResponse;
import cnab.normalization.bycoders.domain.model.TransactionsBalanceDomain;
import org.mapstruct.Mapper;

@Mapper(uses = FileProcessCNABController.class)
public interface TransactionsBalanceMapperApi {
    TransactionsBalanceResponse toResponse(TransactionsBalanceDomain domain);

}
