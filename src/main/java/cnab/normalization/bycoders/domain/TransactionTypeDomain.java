package cnab.normalization.bycoders.domain;

import lombok.*;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class TransactionTypeDomain {
    private String code;
    private String description;
    private String nature;
    private String signal;

    public static HashMap<String, TransactionTypeDomain> allTypes() {
        HashMap<String, TransactionTypeDomain> types = new HashMap<>();

        types.put("1", new TransactionTypeDomain("1", "Débito", "Entrada", "+"));
        types.put("2",new TransactionTypeDomain("2", "Boleto", "Saída", "-"));
        types.put("3", new TransactionTypeDomain("3", "Financiamento", "Saída", "-"));
        types.put("4", new TransactionTypeDomain("4", "Crédito", "Entrada", "+"));
        types.put("5", new TransactionTypeDomain("5", "Recebimento Empréstimo", "Entrada", "+"));
        types.put("6", new TransactionTypeDomain("6", "Vendas", "Entrada", "+"));
        types.put("7", new TransactionTypeDomain("7", "Recebimento TED", "Entrada", "+"));
        types.put("8", new TransactionTypeDomain("8", "Recebimento DOC", "Entrada", "+"));
        types.put("9", new TransactionTypeDomain("9", "Aluguel", "Saída", "-"));

        return types;
    }
}
