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
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id")
    private TransactionTypeEntity type;

    @Column
    private String date;

    @Column
    private Double value;

    @Column
    private String cpf;

    @Column
    private String cardNumber;

    @Column
    private String hour;

    @Column
    private String onwnerStore;

    @Column
    private String nameStore;
}
