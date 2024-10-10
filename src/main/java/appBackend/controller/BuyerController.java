package appBackend.controller;

import appBackend.model.Buyer;
import appBackend.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping
    public List<Buyer> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAllBuyers();
        System.out.println("Fetched buyers: " + buyers);
        return buyers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        System.out.println("Fetching buyer with ID: " + id);
        Buyer buyer = buyerService.getBuyerById(id);
        if (buyer != null) {
            return ResponseEntity.ok(buyer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        Buyer createdBuyer = buyerService.createBuyer(buyer);
        System.out.println("Created buyer: " + createdBuyer);
        return ResponseEntity.ok(createdBuyer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        System.out.println("Deleting buyer with ID: " + id);
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
