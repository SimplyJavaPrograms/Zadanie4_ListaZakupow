package ShoppingList.API;

import org.junit.experimental.theories.DataPoint;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShoppingListAPI {

    private final String listFilename;
    private final Vector<Category> categories;
    private boolean isCreateted = false;

    public ShoppingListAPI() throws Exception {
        this("lista.txt");
    }

    public ShoppingListAPI(String listFilename) throws Exception {
        this.listFilename = listFilename;
        this.categories = initCategoriesArray();
    }

    private Vector<Category> initCategoriesArray() {
        return new Vector<Category>();
    }

    protected boolean isCreateted() {
        return isCreateted;
    }

    private void setCreateted() {
        isCreateted = true;
    }

    public void loadShoppingList() throws Exception {
        String fileRecord;
        LineNumberReader fReader = new LineNumberReader(new FileReader(listFilename));

        while ((fileRecord = fReader.readLine()) != null)
            try {
                categories.add(new Category(fReader, fileRecord));
            } catch (IllegalArgumentException e) {
                productWithoutCategoryError(fileRecord, fReader.getLineNumber());
            }
        setCreateted();
        fReader.close();
    }

    private void pushCategory(String categoryName) {
        categories.add(new Category(categoryName));
    }

    public void loadEmptyShoppingList() throws Exception {
        categories.clear();
        setCreateted();
    }

    private void productWithoutCategoryError(String line, int lineNumber) {
        Product productWithoutCategory = new Product(line);
        System.out.println("Blad skladni w linii: " + lineNumber + "." + "Powod:Produkt bez kategori \"" + productWithoutCategory + "\".");
    }

    public void printFullList() {
        categories.forEach(category -> category.printCategoriesAndContent());
    }

    public void printCategory() {
        categories.forEach(category -> System.out.println("Kategoria:\t" + category));
    }

    public void pushProductToCategory(String product, String categoryName) {
        //List<Category> cat = categories.stream().filter(p -> p.getName().equals((categoryName))).collect(Collectors.toList());
        Category cat2 = getCategoryByName(categoryName);
        if (cat2 == null) {
            pushCategory(categoryName);
            pushProductToCategory(product, categoryName);
        } else
            cat2.pushProduct(product);
    }
    private Category getCategoryByName(String categoryName){
        int index = categories.indexOf(new Category(categoryName));

        if(index == -1)
            return null;

        Category cat = categories.get(index);
        return cat;
    }

    public void popProductFromCategory(String productName, String categoryName) {
        Category cat = getCategoryByName(categoryName);

        if (cat != null) {
            cat.popProduct(productName);
        }
    }

    public void clearWholeList() {
        categories.clear();
    }

    public void saveListToFile() throws FileNotFoundException {
        PrintWriter outputFile = new PrintWriter(listFilename);
        for (Category category : categories){
            String text = category.generateStringWithContent();
            outputFile.println(text);
        }
        outputFile.close();
    }
}
