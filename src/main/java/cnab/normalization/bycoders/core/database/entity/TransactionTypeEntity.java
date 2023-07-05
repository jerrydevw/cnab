package cnab.normalization.bycoders.core.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction_type")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private String nature;

    @Column
    private String signal;

    @OneToOne(mappedBy = "type", cascade = CascadeType.ALL)
    private TransactionEntity transaction;
}
