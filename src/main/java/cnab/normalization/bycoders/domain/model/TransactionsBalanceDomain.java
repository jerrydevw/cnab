package cnab.normalization.bycoders.domain.model;

public record TransactionsBalanceDomain(Double totalEntrance,
                                        Double totalExit,
                                        Double finalValue) {}
