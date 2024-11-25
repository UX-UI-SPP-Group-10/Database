package appBackend.repository;

import appBackend.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByBuyer_BuyerId(Long buyerId);

    // Find receipts by item ID
    List<Receipt> findByItem_ItemId(Long itemId);

    // Find receipts by company ID through the item's company relationship
    List<Receipt> findByItem_Company_CompanyId(Long companyId);
}