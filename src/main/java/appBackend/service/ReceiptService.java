package appBackend.service;

import appBackend.model.Buyer;
import appBackend.model.Receipt;
import appBackend.model.Item;
import appBackend.repository.ReceiptRepository;
import appBackend.repository.BuyerRepository;
import appBackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceiptService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }

    public Receipt createReceipt(Long buyerId, Long itemId) {
        // Fetch buyer and item by ID from the query parameters
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + buyerId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        Receipt newReceipt = new Receipt();
        // Set buyer and item to the receipt
        newReceipt.setBuyer(buyer);
        newReceipt.setItem(item);

        // Save and return the receipt
        return receiptRepository.save(newReceipt);
    }

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

    public Receipt updateReceipt(Long id, Receipt updatedReceipt) {
        return receiptRepository.findById(id)
                .map(existingReceipt -> {
                    existingReceipt.setBuyer(updatedReceipt.getBuyer());
                    existingReceipt.setItem(updatedReceipt.getItem());
                    return receiptRepository.save(existingReceipt);
                })
                .orElseThrow(() -> new RuntimeException("Receipt not found with ID: " + id));
    }

    public Receipt patchReceipt(Long id, Map<String, Object> updates) {
        return receiptRepository.findById(id)
                .map(existingReceipt -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "buyerId":
                                Long buyerId = Long.valueOf(value.toString());
                                Buyer buyer = buyerRepository.findById(buyerId)
                                        .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + buyerId));
                                existingReceipt.setBuyer(buyer);
                                break;
                            case "itemId":
                                Long itemId = Long.valueOf(value.toString());
                                Item item = itemRepository.findById(itemId)
                                        .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
                                existingReceipt.setItem(item);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid field: " + key);
                        }
                    });
                    return receiptRepository.save(existingReceipt);
                })
                .orElseThrow(() -> new RuntimeException("Receipt not found with ID: " + id));
    }


}
