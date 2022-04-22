package ShoppingList.TerminalUI;

import ShoppingList.API.ShoppingListAPI;

import java.util.Scanner;

public class ShoppingListAPITerminalController extends ShoppingListAPI {


    private Scanner scanner = new Scanner(System.in);

    public ShoppingListAPITerminalController(String listFilename) throws Exception {
        super(listFilename);
    }

    public void runUI() {
        IMenu [] menu = {new MainMenu() , new LoaderMenu() };
        while (true) {
            IMenu curMenu = isCreateted() ? menu[0]: menu[1];
            curMenu.printMenu();
            try {
                scanner.skip("\\R?");
                int userChoice = scanner.nextInt();
                curMenu.performUserAction(this, userChoice);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.next();
            }
        }
    }

    public void getProductWithCategoryFromUserAndPush() {
        String[] productAndCategory = getProductAndCategory();
        pushProductToCategory(productAndCategory[0], productAndCategory[1]);
    }

    public void getProductWIthCategoryFromUserAndPop() {
        String[] productAndCategory = getProductAndCategory();
        popProductFromCategory(productAndCategory[0], productAndCategory[1]);
    }

    private String[] getProductAndCategory() {
        System.out.println("Wprowadz produkt do usuniecia w formacie: \"Produkt Kategoria\"");
        scanner.skip("\\R?");
        String line = scanner.nextLine();
        return line.split("\\s");
    }



}
