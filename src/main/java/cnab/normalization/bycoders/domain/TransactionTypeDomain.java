package cnab.normalization.bycoders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeDomain {
    private String code;
    private String description;
    private String nature;
    private String signal;
}
