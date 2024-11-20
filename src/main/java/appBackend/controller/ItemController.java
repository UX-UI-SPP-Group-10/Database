package appBackend.controller;

import appBackend.model.Company;
import appBackend.model.Item;
import appBackend.service.CompanyService;
import appBackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/company/{compId}")
    public ResponseEntity<Item> createItem(@PathVariable Long compId, @RequestBody Item item) {
        Item createdItem = itemService.addItemToCompany(compId, item);
        return ResponseEntity.ok(createdItem);
    }


    @GetMapping
    public List<Item> getAllItems(){
        System.out.println("Received request to get all items");
        return itemService.getAllItems();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/company/{compId}")
    public List<Item> getItemsByCompanyId(@PathVariable Long compId) {
        return itemService.getItemsByCompanyId(compId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        Item existingItem = itemService.getItemById(id);
        if (existingItem == null) {
            return ResponseEntity.notFound().build();
        }
        updatedItem.setItemId(id);
        Item savedItem = itemService.updateItem(updatedItem);
        return ResponseEntity.ok(savedItem);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> patchItem(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Item updatedItem = itemService.patchItem(id, updates);
        if (updatedItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedItem);
    }

}
