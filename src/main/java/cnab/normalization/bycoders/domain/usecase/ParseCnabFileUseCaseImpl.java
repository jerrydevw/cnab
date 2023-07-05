package cnab.normalization.bycoders.domain.usecase;

import cnab.normalization.bycoders.domain.exception.ParseFileException;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.model.TransactionTypeDomain;
import cnab.normalization.bycoders.domain.usecase.api.ParseCnabFileUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParseCnabFileUseCaseImpl implements ParseCnabFileUseCase {
    Logger logger = LoggerFactory.getLogger(ParseCnabFileUseCaseImpl.class);

    @Override
    public List<TransactionDomain> execute(MultipartFile multipartFile) {
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
                            line.substring(48, 62).trim(),
                            line.substring(62).trim(),
                            getTransactionType(line.substring(0, 1), types)
                    );
                    transacoes.add(transactionDomain);
                }
            }
        } catch (IOException e) {
            logger.error("ERRO AO PROCESSAR O ARQUIVO", e);
            throw new ParseFileException("Erro ao processar o arquivo", e);
        }

        return transacoes;

    }

    private TransactionTypeDomain getTransactionType(String codigo, HashMap<String, TransactionTypeDomain> types) {
        return types.get(codigo);
    }
}
