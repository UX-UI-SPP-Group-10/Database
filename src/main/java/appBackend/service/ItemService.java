package appBackend.service;

import appBackend.model.Company;
import appBackend.model.Item;
import appBackend.repository.ItemRepository;
import appBackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return itemRepository.findById(id).orElse(null);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
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
}