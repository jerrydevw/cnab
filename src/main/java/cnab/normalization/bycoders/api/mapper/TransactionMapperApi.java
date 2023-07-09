package cnab.normalization.bycoders.api.mapper;

import cnab.normalization.bycoders.api.controller.FileProcessCNABController;
import cnab.normalization.bycoders.api.controller.TransactionResponse;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.ProcessCnabUseCaseImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(uses = FileProcessCNABController.class)
public interface TransactionMapperApi {
    @Mapping(source = "date", target = "date", qualifiedByName = "parseData")
    TransactionResponse toResponse(TransactionDomain domain);

    @Named("parseData")
    static String parseData(String dateString) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = inputFormat.parse(dateString);


        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        return outputFormat.format(date);
    }
}
