package cnab.normalization.bycoders.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class TransactionTypeDomain {
    private String code;
    private String description;
    private String nature;
    private String signal;
}
