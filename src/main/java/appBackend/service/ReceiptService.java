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
}
