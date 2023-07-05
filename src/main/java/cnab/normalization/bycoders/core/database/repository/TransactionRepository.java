package cnab.normalization.bycoders.core.database.repository;

import cnab.normalization.bycoders.core.database.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Page<TransactionEntity> findAllByNameStore(String nameStore, Pageable pageable);
}
