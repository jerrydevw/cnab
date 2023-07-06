package cnab.normalization.bycoders.core.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="code", nullable=false)
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
