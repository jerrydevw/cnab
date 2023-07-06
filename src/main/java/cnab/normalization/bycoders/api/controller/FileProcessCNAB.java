package cnab.normalization.bycoders.api.controller;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.api.FindTransactionsUseCase;
import cnab.normalization.bycoders.domain.usecase.api.ProcessCnabUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/cnab")
@RequiredArgsConstructor
public class FileProcessCNAB {
    private final ProcessCnabUseCase processCnabUseCase;
    private final FindTransactionsUseCase findTransactionsUseCase;
    @PostMapping
    public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) {
        processCnabUseCase.execute(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDomain>> findTransactions(@RequestParam(value = "storeName", required = false) String storeName, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(findTransactionsUseCase.execute(storeName, page, size));
    }

}

