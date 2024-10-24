package appBackend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;


    @ManyToOne
    @JsonBackReference(value = "buyer-receipts")
    private Buyer buyer;

    @OneToOne
    @JsonBackReference(value = "item-receipt")
    private Item item;

}

