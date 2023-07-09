package cnab.normalization.bycoders.api.controller;

import java.util.Date;

public record TransactionResponse(Long id,
                                String date,
                                Double value,
                                String cpf,
                                String cardNumber,
                                String hour,
                                String onwnerStore,
                                String nameStore,
                                TransactionTypeResponse type) {}