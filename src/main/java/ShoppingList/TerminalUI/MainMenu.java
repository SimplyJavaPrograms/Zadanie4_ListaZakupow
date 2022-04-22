package ShoppingList.TerminalUI;

public class MainMenu implements IMenu{
    private String[] mainMenuContent = {"\n\n\n\nWybierz operacje jaka chcesz wykonac",
            "    [1] Dodaj produkt",
            "    [2] Usun produkt",
            "    [3] Wyczysc cala liste",
            "    [4] Zapisz zmiany na dysku",
            "    [5] Wyswietl liste",
            "    [6] Wyjdz z programu",
    };

    @Override
    public void printMenu() {
        for (String line : mainMenuContent){
            System.out.println(line);
        }
    }

    @Override
    public void validateInput(int userChoice) throws Exception {
        if(userChoice < 1 || userChoice > mainMenuContent.length-1)
            throw new Exception("Wybierz jedna z opcji z zakresu 1 - " + (mainMenuContent.length - 1) );
    }

    @Override
    public void performUserAction(ShoppingListAPITerminalController controller, int choice) throws Exception {
        validateInput(choice);
        switch (choice) {
            case 1:
                controller.getProductWithCategoryFromUserAndPush();
                break;
            case 2:
                controller.getProductWIthCategoryFromUserAndPop();
                break;
            case 3:
                controller.clearWholeList();
                break;
            case 4:
                controller.saveListToFile();
                break;
            case 5:
                controller.printFullList();
                break;
            case 6:
                System.exit(1);
            default:
                break;
        }
    }

    @Override
    public int menuType() {
        return 1;
    }
}
