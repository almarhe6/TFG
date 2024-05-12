package tfg.apitfg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import tfg.apitfg.repository.FundRepository;
import tfg.apitfg.service.IWalletService;

@DataJpaTest
@SpringBootTest
public class RepositoryTests {

    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private IWalletService walletService;

    @Test
    public void testGetAllFunds() {
        var funds = fundRepository.findAll();
        assertNotNull(funds);
    }

    @Test
    public void testCreateTransactions() {
        // Realiza una compra de 300 unidades del fondo
        walletService.tradeFund("robe@mail.com", "LU0836512615", true, 300);

        // Verifica que se haya creado una transacción de compra
        var buyTransactions = walletService.findTransactions(
                "robe@mail.com", "LU0836512615", LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
        assertFalse(buyTransactions.isEmpty());
        var buyTransaction = buyTransactions.get(0);
        assertEquals(300, buyTransaction.getQuantity());
        assertTrue(buyTransaction.isBuySell());

        // Realiza una venta de 300 unidades del fondo
        walletService.tradeFund("robe@mail.com", "LU0836512615", false, 300);

        // Verifica que se haya creado una transacción de venta
        var sellTransactions = walletService.findTransactions(
                "robe@mail.com", "LU0836512615", LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
        assertFalse(sellTransactions.isEmpty());
        var sellTransaction = sellTransactions.get(0);
        assertEquals(300, sellTransaction.getQuantity());
        assertFalse(sellTransaction.isBuySell());
    }
}
