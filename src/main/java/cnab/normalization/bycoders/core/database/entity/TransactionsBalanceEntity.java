package cnab.normalization.bycoders.core.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NamedNativeQuery(
        name = "transactions_balance_by_store",
        query =
                "SELECT SUM(CASE WHEN tt.signal = '+' THEN t.value ELSE 0 END) AS totalEntrance, " +
                        "SUM(CASE WHEN tt.signal = '-' THEN t.value ELSE 0 END) AS totalExit, " +
                        "SUM(CASE WHEN tt.signal = '+' THEN t.value ELSE -t.value END) AS finalValue " +
                        "FROM transaction t " +
                        "JOIN transaction_type tt ON t.code = tt.code " +
                        "WHERE t.name_store = 'MERCADO DA AVENIDA'",
        resultSetMapping = "balance_by_store"
)
@SqlResultSetMapping(
        name = "balance_by_store",
        classes = @ConstructorResult(
                targetClass = TransactionsBalanceEntity.class,
                columns = {
                        @ColumnResult(name = "totalEntrance", type = Double.class),
                        @ColumnResult(name = "totalExit", type = Double.class),
                        @ColumnResult(name = "finalValue", type = Double.class)
                }
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionsBalanceEntity {
    Double totalEntrance;

    Double totalExit;

    Double finalValue;
    @Id
    private Long id;
}
