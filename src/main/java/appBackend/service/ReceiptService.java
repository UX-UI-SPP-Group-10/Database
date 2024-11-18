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
        try {
            System.out.println("Received receipt: " + receipt);
    
            Buyer buyer = buyerRepository.findById(receipt.getBuyer().getBuyId())
                    .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + receipt.getBuyer().getBuyId()));
            System.out.println("Fetched buyer: " + buyer);
    
            Item item = itemRepository.findById(receipt.getItem().getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with ID: " + receipt.getItem().getItemId()));
            System.out.println("Fetched item: " + item);
    
            receipt.setBuyer(buyer);
            receipt.setItem(item);
    
            Receipt savedReceipt = receiptRepository.save(receipt);
            System.out.println("Saved receipt: " + savedReceipt);
            return savedReceipt;
        } catch (Exception e) {
            System.out.println("Error creating receipt: " + e.getMessage());
            throw e;  // or return an appropriate response
        }
    }
    

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }
}
