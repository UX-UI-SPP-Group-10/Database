package appBackend;

import appBackend.model.Buyer;
import appBackend.model.Company;
import appBackend.model.Item;
import appBackend.model.Receipt;
import appBackend.service.BuyerService;
import appBackend.service.CompanyService;
import appBackend.service.ItemService;
import appBackend.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component    // Use this to load test data
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

        // Create test buyers
        Buyer buyer1 = new Buyer();
        buyer1.setBuyerName("Test Buyer 1");
        buyer1 = buyerService.createBuyer(buyer1);

        Buyer buyer2 = new Buyer();
        buyer2.setBuyerName("Test Buyer 2");
        buyer2 = buyerService.createBuyer(buyer2);

        // Create test receipts
        Receipt receipt1 = new Receipt();
        receipt1.setBuyer(buyer1);
        receipt1.setItem(item1);
        receiptService.createReceipt(receipt1);

        Receipt receipt2 = new Receipt();
        receipt2.setBuyer(buyer2);
        receipt2.setItem(item2);
        receiptService.createReceipt(receipt2);

        System.out.println("Test data loaded.");
    }
}