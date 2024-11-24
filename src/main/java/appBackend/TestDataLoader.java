package appBackend;

import appBackend.model.Buyer;
import appBackend.model.Company;
import appBackend.model.Item;
import appBackend.service.BuyerService;
import appBackend.service.CompanyService;
import appBackend.service.ItemService;
import appBackend.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ReceiptService receiptService;

    @Override
    public void run(String... args) throws Exception {
        // Create a test company
        Company company = new Company();
        company.setCompanyName("Test Company");
        company = companyService.createCompany(company);

        // Create test items
        Item item1 = new Item();
        item1.setItemName("Test Item 1");
        item1.setPrice(100);
        item1.setDescription("Description for Test Item 1");
        item1.setStock(10);
        item1.setCompany(company);
        itemService.createItem(item1);

        Item item2 = new Item();
        item2.setItemName("Test Item 2");
        item2.setPrice(200);
        item2.setDescription("Description for Test Item 2");
        item2.setStock(20);
        item2.setCompany(company);
        itemService.createItem(item2);

        Item item3 = new Item();
        item3.setItemName("Test Item 3 without receipt");
        item3.setPrice(200);
        item3.setDescription("Description for Test Item 2");
        item3.setStock(20);
        item3.setCompany(company);
        itemService.createItem(item3);

        // Create test buyers
        Buyer buyer1 = new Buyer();
        buyer1.setBuyerName("Test Buyer 1");
        buyer1 = buyerService.createBuyer(buyer1);

        Buyer buyer2 = new Buyer();
        buyer2.setBuyerName("Test Buyer 2");
        buyer2 = buyerService.createBuyer(buyer2);

        // Create test receipts using the new receipt setup
        receiptService.createReceipt(buyer1.getBuyerId(), item1.getItemId());
        receiptService.createReceipt(buyer2.getBuyerId(), item1.getItemId());

        System.out.println("Test data loaded.");
    }
}
