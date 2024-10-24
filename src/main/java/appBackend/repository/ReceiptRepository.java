package appBackend.repository;

import appBackend.model.Receipt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByBuyer_BuyId(Long buyerId);
}
