package cnab.normalization.bycoders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDomain {
    private TransactionTypeDomain type;
    private String date;
    private Double value;
    private String cpf;
    private String cardNumber;
    private String hour;
    private String onwnerStore;
    private String nameStore;
}
