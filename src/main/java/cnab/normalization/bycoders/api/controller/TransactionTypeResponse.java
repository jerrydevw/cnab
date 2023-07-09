package cnab.normalization.bycoders.api.controller;

public record TransactionTypeResponse(
    Long id,
    String code,
    String description,
    String nature,
    String signal) {
}
