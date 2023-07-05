package cnab.normalization.bycoders.usecase;

import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.usecase.ParseCnabFileUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ParseCnabFileUseCaseTest {

    @InjectMocks
    private ParseCnabFileUseCaseImpl useCase;

    @Test
    void shouldParseFile() {

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                """
                    3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO      \s
                    5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ
                    3201903010000012200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA
                """.getBytes()
        );

        List<TransactionDomain> result = useCase.execute(file);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);

    }
}
