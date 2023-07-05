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
                    TransactionDomain.setNameStore(line.substring(62, line.length()));

                    transacoes.add(TransactionDomain);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        var ok = transacoes.stream().map(mapper::toEntity).toList();

        repository.saveAll(ok);

        // Imprimir a lista de transações
        for (TransactionDomain TransactionDomain : transacoes) {
            System.out.println(TransactionDomain);
        }
    
    }


    private TransactionTypeDomain getTransactionType(String codigo, HashMap<String, TransactionTypeDomain> types) {
        return types.get(codigo);
    }
}
