package cnab.normalization.bycoders.service;

import cnab.normalization.bycoders.domain.TransactionDomain;
import cnab.normalization.bycoders.domain.TransactionTypeDomain;
import cnab.normalization.bycoders.mapper.TransactionMapper;
import cnab.normalization.bycoders.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    @Override
    public void store(MultipartFile multipartFile) {
        List<TransactionDomain> transacoes = new ArrayList<>();

        HashMap<String, TransactionTypeDomain> types = TransactionTypeDomain.allTypes();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 80) {
                    TransactionDomain TransactionDomain = new TransactionDomain();
                    TransactionDomain.setType(getTransactionType(line.substring(0, 1), types));
                    TransactionDomain.setDate(line.substring(1, 9));
                    TransactionDomain.setValue(Double.parseDouble(line.substring(9, 19)) / 100.00);
                    TransactionDomain.setCpf(line.substring(19, 30));
                    TransactionDomain.setCardNumber(line.substring(30, 42));
                    TransactionDomain.setHour(line.substring(42, 48));
                    TransactionDomain.setOnwnerStore(line.substring(48, 62));
                    TransactionDomain.setNameStore(line.substring(62));

                    transacoes.add(TransactionDomain);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        var ok = transacoes.stream().map(mapper::toEntity).toList();

        repository.saveAll(ok);

    
    }


    private TransactionTypeDomain getTransactionType(String codigo, HashMap<String, TransactionTypeDomain> types) {
        return types.get(codigo);
    }
}
