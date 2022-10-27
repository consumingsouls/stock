package stock;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;

/**
 * Unit test for simple App.
 */
class StockTest {
   @Test 
   void testGetaccount_Vaild() {
    Stock.InitializeList();
    Account account = Stock.getAccount(7);
    assertEquals("Moritz Warrington",account.GetFullName() );
    assertEquals(7, account.GetAccountNumber());
    assertEquals(24, account.GetTransactionsCount());
    assertEquals(1710809.9, account.GetBalance());
    assertEquals(46, account.GetNumberOfShares());
   } 
   @Test 
   void testGetaccount_Invalid(){
    Stock.InitializeList();
    Account account = Stock.getAccount(420);
    assertNull(account);
    
   }
   @Test
    void testHtmlFilesExist() {

        Stock.InitializeList();

        //Write ALL accounts to there own html files
        for (int i = 1; i <= Stock.GetJSONArraySize(); i++) {
            Account account = Stock.getAccount(i);
            Stock.WritetoHtml(account);

            assertTrue(new File("accountStatements/"+account.GetHtmlFileName()).exists());

        }

    }



    }

