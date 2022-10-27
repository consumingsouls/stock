package stock;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountTest {
    @Test 
    void testAccountConstructor() {
        Stock.InitializeList();
        Account account = Stock.getAccount(7);
        assertEquals("Moritz Warrington",account.GetFullName() );
        assertEquals(7, account.GetAccountNumber());
        assertEquals(24, account.GetTransactionsCount());
        assertEquals(1710809.9, account.GetBalance());
        assertEquals(46, account.GetNumberOfShares());  
    }

    @Test 
    void testAccountAddTransaction() {
        Account account = new Account(73, "Freddy", "Krueger", "xxx-xx-xxxx", "test@testing.com", "666-456-7890", 666.666);
        account.AddTransaction(new Transaction("Buy", "WXYZ", 3, 32.58));
        account.AddTransaction(new Transaction("Buy", "ABC", 6, 23.14));
        account.AddTransaction(new Transaction("Sell", "THC", 2, 362.12));

        assertEquals(1154.33, account.GetBalance());
        assertEquals(7, account.GetNumberOfShares());
    }
}
