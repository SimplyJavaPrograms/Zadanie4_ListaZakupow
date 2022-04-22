package ShoppingList.TerminalUI;

public interface IMenu {
    void printMenu();
    void validateInput(int userChoice) throws Exception;
    void performUserAction(ShoppingListAPITerminalController controller, int choice) throws Exception;
    int menuType();
}
