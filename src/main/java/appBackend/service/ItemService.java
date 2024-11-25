package appBackend.service;

import appBackend.model.Company;
import appBackend.model.Item;
import appBackend.repository.ItemRepository;
import appBackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        System.out.println("Fectching item with id: " + id);
        return itemRepository.findById(id).orElse(null);
    }

    public void createItem(Item item) {
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item addItemToCompany(Long compId, Item item) {
        Company company = companyRepository.findById(compId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        item.setCompany(company);
        return itemRepository.save(item);
    }

    public List<Item> getItemsByCompanyId(Long compId) {
        return itemRepository.findByCompany_CompanyId(compId);
    }

    public Item updateItem(Item updatedItem) {
        // Fetch the existing item from the database
        Item existingItem = itemRepository.findById(updatedItem.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Update only mutable fields
        existingItem.setItemName(updatedItem.getItemName());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setStock(updatedItem.getStock());

        // Retain the existing company association
        if (existingItem.getCompany() == null) {
            throw new RuntimeException("Company must be associated with the item");
        }
        updatedItem.setCompany(existingItem.getCompany());

        // Save and return the updated item
        return itemRepository.save(existingItem);
    }



    public Item patchItem(Long id, Map<String, Object> updates) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            return null;
        }
        Item item = optionalItem.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "itemName":
                    if (value instanceof String) {
                        item.setItemName((String) value);
                    } else {
                        throw new IllegalArgumentException("Invalid value for itemName");
                    }
                    break;
                case "price":
                    if (value instanceof Number) { // Handle numbers safely
                        item.setPrice(((Number) value).intValue());
                    } else {
                        throw new IllegalArgumentException("Invalid value for price");
                    }
                    break;
                case "description":
                    if (value instanceof String) {
                        item.setDescription((String) value);
                    } else {
                        throw new IllegalArgumentException("Invalid value for description");
                    }
                    break;
                case "stock":
                    if (value instanceof Number) { // Handle numbers safely
                        item.setStock(((Number) value).intValue());
                    } else {
                        throw new IllegalArgumentException("Invalid value for stock");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return itemRepository.save(item);
    }

}