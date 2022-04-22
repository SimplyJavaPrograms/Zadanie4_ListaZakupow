package ShoppingList;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingList {

    String listFilename;
    ArrayList<Category> categories;

    public ShoppingList(String listFilename) throws Exception {
        this.listFilename = listFilename;
        this.categories = initCategoriesArray();
        this.loadShoppingList();
    }

    private ArrayList<Category> initCategoriesArray() {
        return new ArrayList<Category>();
    }

    private void loadShoppingList() throws Exception {
        String fileRecord;
        LineNumberReader fReader = new LineNumberReader(new FileReader(listFilename));

        while ((fileRecord = fReader.readLine()) != null)
            try {
                categories.add(new Category(fReader, fileRecord));
            } catch (IllegalArgumentException e) {
                productWithoutCategoryError(fileRecord, fReader.getLineNumber());
            }

        fReader.close();
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
}
