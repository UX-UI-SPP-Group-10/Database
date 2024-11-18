package appBackend.controller;

import appBackend.model.Receipt;
import appBackend.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {
    
    @Autowired
    private ReceiptService receiptService;

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Long id) {
        Receipt receipt = receiptService.getReceiptById(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        try {
            // Log incoming data
            System.out.println("Received receipt: " + receipt);

            Receipt createdReceipt = receiptService.createReceipt(receipt);
            return ResponseEntity.ok(createdReceipt);
        } catch (Exception e) {
            System.err.println("Error creating receipt: " + e.getMessage());
            return ResponseEntity.status(500).build();  // Return 500 on error
        }
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
