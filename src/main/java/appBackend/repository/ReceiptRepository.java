package appBackend.repository;

import appBackend.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByBuyer_BuyerId(Long buyerId);
}