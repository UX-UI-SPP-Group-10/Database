package appBackend.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "buyer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyId;
    private String buyName;

    /*@OneToMany(mappedBy = "buyer")
    @JsonManagedReference
    private List<Receipt> receipts; */
}

