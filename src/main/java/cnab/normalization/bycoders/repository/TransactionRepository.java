package cnab.normalization.bycoders.repository;

import cnab.normalization.bycoders.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
