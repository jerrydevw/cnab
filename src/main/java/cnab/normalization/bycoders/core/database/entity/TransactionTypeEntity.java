package cnab.normalization.bycoders.core.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TransactionEntity> transaction;
}
