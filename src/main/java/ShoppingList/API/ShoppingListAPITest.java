package ShoppingList.API;

import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListAPITest {

    @org.junit.jupiter.api.Test
    void pushProductToCategoryTEST1() throws Exception {
        ShoppingListAPI shoplist = new ShoppingListAPI();

        String product = "Opona";
        String category = "Motoryzacja";

        shoplist.loadEmptyShoppingList();
        shoplist.pushProductToCategory(product, category);
        shoplist.printFullList();

    }

    @org.junit.jupiter.api.Test
    void pushProductToCategoryTEST2() throws Exception {
        ShoppingListAPI shoplist = new ShoppingListAPI();
        String product = "Opona";
        String category = "Motoryzacja";

        shoplist.loadEmptyShoppingList();
        shoplist.pushProductToCategory(product, category);
        shoplist.printFullList();

        shoplist.popProductFromCategory(product, category);
        shoplist.printFullList();
    }

    @org.junit.jupiter.api.Test
    void pushProductToCategoryTEST3() throws Exception {
        ShoppingListAPI shoplist = new ShoppingListAPI();
        shoplist.loadEmptyShoppingList();

        shoplist.pushProductToCategory("product1", "category");
        shoplist.pushProductToCategory("PRODUCT", "category");
        shoplist.pushProductToCategory("product", "cat2");
        shoplist.pushProductToCategory("test", "cat2");
        shoplist.printFullList();

        shoplist.saveListToFile();
    }
}