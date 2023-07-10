package cnab.normalization.bycoders.core.database.repository;

import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.core.database.entity.TransactionsBalanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Page<TransactionEntity> findAllByNameStore(String nameStore, Pageable pageable);

    @Query(name = "transactions_balance_by_store", nativeQuery = true)
    TransactionsBalanceEntity calculateTotals(String nameStore);

}
