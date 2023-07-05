package cnab.normalization.bycoders.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String codigo;

    @Column
    private String descricao;

    @Column
    private String natureza;

    @Column
    private String sinal;

    @OneToOne(mappedBy = "tipo", cascade = CascadeType.ALL)
    private TransactionEntity transaction;
}
