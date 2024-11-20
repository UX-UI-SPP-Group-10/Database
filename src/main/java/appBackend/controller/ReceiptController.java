package appBackend.controller;

import appBackend.model.Receipt;
import appBackend.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Receipt> createReceipt(
            @RequestParam(required = true) Long buyerId,
            @RequestParam(required = true) Long itemId) {
        try {
            // Log the incoming data
            System.out.println("Received receipt with buyerId: " + buyerId + " and itemId: " + itemId);

            Receipt createdReceipt = receiptService.createReceipt(buyerId, itemId);
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

    @PutMapping("/{id}")
    public ResponseEntity<Receipt> updateReceipt(@PathVariable Long id, @RequestBody Receipt updatedReceipt) {
        try {
            Receipt receipt = receiptService.updateReceipt(id, updatedReceipt);
            if (receipt != null) {
                return ResponseEntity.ok(receipt);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error updating receipt: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Receipt> patchReceipt(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            Receipt receipt = receiptService.patchReceipt(id, updates);
            if (receipt != null) {
                return ResponseEntity.ok(receipt);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error patching receipt: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

}
