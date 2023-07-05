package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.domain.mapper.TransactionMapper;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.model.TransactionTypeDomain;
import cnab.normalization.bycoders.domain.usecase.api.ParseCnabFileUseCase;
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
public class ParseCnabFileUseCaseImpl implements ParseCnabFileUseCase {

    private final TransactionMapper mapper;
    @Override
    public List<TransactionDomain> execute(MultipartFile multipartFile) {
        System.out.println("INICIANDO PROCESSAMENTO DO ARQUIVO");
        List<TransactionDomain> transacoes = new ArrayList<>();

        HashMap<String, TransactionTypeDomain> types = TransactionTypeDomain.allTypes();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 80) {
                    TransactionDomain transactionDomain = new TransactionDomain(
                            line.substring(1, 9),
                            Double.parseDouble(line.substring(9, 19)) / 100.00,
                            line.substring(19, 30),
                            line.substring(30, 42),
                            line.substring(42, 48),
                            line.substring(48, 62),
                            line.substring(62),
                            getTransactionType(line.substring(0, 1), types)
                    );
                    transacoes.add(transactionDomain);
                }
                System.out.println("FIM PROCESSAMENTO DO ARQUIVO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transacoes;

    }

    private TransactionTypeDomain getTransactionType(String codigo, HashMap<String, TransactionTypeDomain> types) {
        return types.get(codigo);
    }
}
