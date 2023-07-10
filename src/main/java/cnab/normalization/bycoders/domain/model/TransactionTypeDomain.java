package cnab.normalization.bycoders.domain.model;

import java.util.HashMap;

public record TransactionTypeDomain(
        Long id,
        String code,
        String description,
        String nature,
        String signal
) {
    public static HashMap<String, TransactionTypeDomain> allTypes() {
        HashMap<String, TransactionTypeDomain> types = new HashMap<>();

        types.put("1", new TransactionTypeDomain(1L, "1", "Débito", "Entrada", "+"));
        types.put("2",new TransactionTypeDomain(2L, "2", "Boleto", "Saída", "-"));
        types.put("3", new TransactionTypeDomain(3L, "3", "Financiamento", "Saída", "-"));
        types.put("4", new TransactionTypeDomain(4L, "4", "Crédito", "Entrada", "+"));
        types.put("5", new TransactionTypeDomain(5L, "5", "Recebimento Empréstimo", "Entrada", "+"));
        types.put("6", new TransactionTypeDomain(6L, "6", "Vendas", "Entrada", "+"));
        types.put("7", new TransactionTypeDomain(7L, "7", "Recebimento TED", "Entrada", "+"));
        types.put("8", new TransactionTypeDomain(8L, "8", "Recebimento DOC", "Entrada", "+"));
        types.put("9", new TransactionTypeDomain(9L, "9", "Aluguel", "Saída", "-"));

        return types;
    }
}
