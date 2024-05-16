package tfg.apitfg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import tfg.apitfg.service.FundService;
import tfg.apitfg.service.WalletService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
class ApiTfgApplicationTests {

    @Autowired
    private WalletService walletService;

    @Autowired
    private FundService fundService;

    @Test
    void testWallet() {
        var wallet = walletService.findWallet("alejandromartorellhernandez@gmail.com");
        assertEquals(wallet.get("IE00B03HCZ61"), 500);
    }

    @Test
    void testFunds() throws Exception {
        var funds = fundService.findFunds();
        assert (!funds.isEmpty());
    }

    @Test
    public void testCreateTransactions() {

        walletService.tradeFund("robe@mail.com", "LU0836512615", true, 300);

        var buyTransactions = walletService.findTransactions(
                "robe@mail.com", "LU0836512615", LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
        assertFalse(buyTransactions.isEmpty());
        var buyTransaction = buyTransactions.get(0);
        assertEquals(300, buyTransaction.getQuantity());
        assertTrue(buyTransaction.isBuySell());
    }
}
