import ShoppingList.TerminalUI.ShoppingListAPITerminalController;

class Main {
    public static void main(String[] args) throws Exception {
        ShoppingListAPITerminalController list = new ShoppingListAPITerminalController("lista.txt");
        list.runUI();
    }

}
