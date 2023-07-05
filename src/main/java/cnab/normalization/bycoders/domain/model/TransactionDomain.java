package cnab.normalization.bycoders.domain.model;

public record TransactionDomain(String date,
                                Double value,
                                String cpf,
                                String cardNumber,
                                String hour,
                                String onwnerStore,
                                String nameStore,
                                TransactionTypeDomain type) {}
