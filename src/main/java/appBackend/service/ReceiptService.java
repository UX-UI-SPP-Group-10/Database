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

    @Autowired  // Add @Autowired here
    private ReceiptRepository receiptRepository;

    @Autowired  // Add @Autowired here
    private ItemRepository itemRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }

    public Receipt createReceipt(Receipt receipt) {
        // Fetch buyer and item by ID
        Buyer buyer = buyerRepository.findById(receipt.getBuyer().getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + receipt.getBuyer().getBuyerId()));

        Item item = itemRepository.findById(receipt.getItem().getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + receipt.getItem().getItemId()));

        // Set buyer and item to the receipt
        receipt.setBuyer(buyer);
        receipt.setItem(item);

        // Save and return the receipt
        return receiptRepository.save(receipt);
    }



    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

    /*
    public List<Receipt> getReceiptsByBuyId(Long buyerId) {
        return receiptRepository.findByBuyer_BuyId(buyerId); // Updated method name
    }
*/
}
