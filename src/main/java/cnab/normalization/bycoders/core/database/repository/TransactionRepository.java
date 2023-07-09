package cnab.normalization.bycoders.core.database.repository;

import cnab.normalization.bycoders.core.database.entity.TransactionBalanceResult;
import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import cnab.normalization.bycoders.core.database.entity.TransactionsBalanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Page<TransactionEntity> findAllByNameStore(String nameStore, Pageable pageable);

//    @Query(value = "SELECT SUM(CASE WHEN tt.signal = '+' THEN t.value ELSE 0 END) AS total_entradas, " +
//            "SUM(CASE WHEN tt.signal = '-' THEN t.value ELSE 0 END) AS total_saidas, " +
//            "SUM(CASE WHEN tt.signal = '+' THEN t.value ELSE -t.value END) AS valor_final " +
//            "FROM transaction t " +
//            "JOIN transaction_type tt ON t.code = tt.code " +
//            "WHERE t.name_store = :nameStore", nativeQuery = true)
@Query(name = "transactions_balance_by_store", nativeQuery = true)
TransactionsBalanceEntity calculateTotals(String nameStore);

}
