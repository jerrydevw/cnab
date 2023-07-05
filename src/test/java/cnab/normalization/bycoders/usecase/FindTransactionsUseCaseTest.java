package cnab.normalization.bycoders.usecase;

import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.core.database.entity.TransactionTypeEntity;
import cnab.normalization.bycoders.core.database.repository.TransactionRepository;
import cnab.normalization.bycoders.domain.mapper.TransactionMapper;
import cnab.normalization.bycoders.domain.model.TransactionDomain;
import cnab.normalization.bycoders.domain.model.TransactionTypeDomain;
import cnab.normalization.bycoders.domain.usecase.FindTransactionsUseCaseImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class FindTransactionsUseCaseTest {

    @InjectMocks
    private FindTransactionsUseCaseImpl useCase;


    @Spy
    private TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);

    @Mock
    private TransactionRepository repository;

    @Before("")
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAndConvertTransactions() {
        TransactionTypeEntity type = new TransactionTypeEntity(1L, "1", "Débito", "Entrada", "+", null);
        TransactionEntity transactionEntity = new TransactionEntity(
                1L,
                type,
                "date",
                50.0,
                "44588745625",
                "4753****3153",
                "153453",
                "JOÃO MACEDO  ",
                "BAR DO JOÃO    "
        );
        PageImpl<TransactionEntity> page = new PageImpl<>(List.of(transactionEntity));
        when(repository.findAllByNameStore(anyString(), any(Pageable.class))).thenReturn(page);

        var transactionsResult = useCase.execute("storeName", 0, 10);

        assertThat(transactionsResult).isNotNull();
        assertThat(transactionsResult.getContent()).isNotEmpty();
    }
}
