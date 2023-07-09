package cnab.normalization.bycoders.api.controller;

import cnab.normalization.bycoders.api.mapper.TransactionMapperApi;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.FindTransactionsUseCase;
import cnab.normalization.bycoders.domain.usecase.api.ProcessCnabUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cnab")
@RequiredArgsConstructor
public class FileProcessCNABController {
    private final ProcessCnabUseCase processCnabUseCase;
    private final FindTransactionsUseCase findTransactionsUseCase;
    private final TransactionMapperApi mapper;
    @PostMapping
    public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) {
        processCnabUseCase.execute(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> findTransactions(@RequestParam(value = "storeName", required = false) String storeName, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<TransactionResponse> response = findTransactionsUseCase.execute(storeName, page, size).map(mapper::toResponse);
        return ResponseEntity.ok(response);
    }

}

