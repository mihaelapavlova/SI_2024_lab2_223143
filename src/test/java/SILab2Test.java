
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    private List<Item> items(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @Test
    void testCheckCart_EveryBranch() {
        RuntimeException exception;

        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertTrue(exception.getMessage().contains("allItems list can't be null!")); //1

        assertTrue(SILab2.checkCart(new ArrayList<>(), 0));  //2

        assertFalse(SILab2.checkCart(new ArrayList<>(), -100)); //3

        assertTrue(SILab2.checkCart(items(new Item("", "0123", 500, 0.2f)), 400)); //4

        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(new Item("", null, 500, 0.2f)), 100));
        assertTrue(exception.getMessage().contains("No barcode!"));  //5
        
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(new Item("", "01#23", 500, 0.2f)), 100));
        assertTrue(exception.getMessage().contains("Invalid character in item barcode!"));  //6

        assertFalse(SILab2.checkCart(items(new Item("", "0123", 500, 0)), 400));  //7


    }


    @Test
    public void testCheckCart_MultipleCondition() {

        assertTrue(SILab2.checkCart(items(new Item("item","0123",500,0.2f)),400));

        assertFalse(SILab2.checkCart(items(new Item("item","4123",500,0.2f)),0));

        assertFalse(SILab2.checkCart(items(new Item("item","0123",500,0)),100));

        assertFalse(SILab2.checkCart(items(new Item("item","4123",500,0)),0));

        assertFalse(SILab2.checkCart(items(new Item("item","0123",100,0.2f)),0));

        assertFalse(SILab2.checkCart(items(new Item("item","4123",100,0.2f)),0));

        assertFalse(SILab2.checkCart(items(new Item("item","0123",100,0)),0));

        assertFalse(SILab2.checkCart(items(new Item("item","4123",100,0)),0));

    }
}

