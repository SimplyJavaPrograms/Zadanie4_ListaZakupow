package ShoppingList.TerminalUI;

public class LoaderMenu implements IMenu{
    private String[] setupMenuContent = {"Jak chcesz rozpoczac prace z programem:",
            "    [1] Wczytaj poprzednia liste",
            "    [2] Stworz nowa liste",
            "    [3] Wyjdz z programu",
    };

    @Override
    public void printMenu() {
        for (String line : setupMenuContent){
            System.out.println(line);
        }
    }

    @Override
    public void validateInput(int userChoice) throws Exception {
        if(userChoice < 1 || userChoice > setupMenuContent.length+1)
            throw new Exception("Wybierz jedna z opcji z zakresu 1 - " + (setupMenuContent.length - 1) );
    }

    @Override
    public void performUserAction(ShoppingListAPITerminalController controller, int choice) throws Exception {
        validateInput(choice);
        switch (choice) {
            case 1:
                controller.loadShoppingList();
                break;
            case 2:
                controller.loadEmptyShoppingList();
                break;
            case 3:
                System.exit(1);
            default:
                break;
        }
    }

    @Override
    public int menuType() {
        return 0;
    }
}
