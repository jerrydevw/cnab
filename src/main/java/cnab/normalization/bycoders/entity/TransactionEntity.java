package cnab.normalization.bycoders.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id")
    private TransactionTypeEntity tipo;

    @Column
    private String data;

    @Column
    private double valor;

    @Column
    private String cpf;

    @Column
    private String cartao;

    @Column
    private String hora;

    @Column
    private String donoLoja;

    @Column
    private String nomeLoja;
}
