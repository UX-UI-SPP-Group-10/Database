package appBackend.service;

import appBackend.model.Receipt;
import appBackend.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }

    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }   

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }
    /*
    public List<Receipt> getReceiptsByBuyerId(Long buyerId) {
        return receiptRepository.findByBuyerId(buyerId);
    }
    */
    
}
