package cnab.normalization.bycoders.api.controller;

public record TransactionsBalanceResponse(Double totalEntrance,
                                          Double totalExit,
                                          Double finalValue) {}