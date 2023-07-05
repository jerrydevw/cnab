package cnab.normalization.bycoders.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionTypeDomain {
    private String code;
    private String description;
    private String nature;
    private String signal;
}
