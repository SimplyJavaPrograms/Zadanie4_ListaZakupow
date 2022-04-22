package ShoppingList;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Category {


    private String name;
    private ArrayList<Product> products;
    private static final String categoryRecordRegEx = "^[^\\s].*";
    private static Pattern categoryPattern = Pattern.compile(categoryRecordRegEx);


    public Category() {
        products = initProductsArray();
    }

    public Category(LineNumberReader fReader, String name) throws Exception {
        setName(name);
        products = initProductsArray();
        loadPruductsFromFile(fReader);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isCategoryRecord(name))
            this.name = name;
        else
            throw new IllegalArgumentException("It's not category record");
    }


    private ArrayList<Product> initProductsArray() {
        return new ArrayList<Product>();
    }

    public void loadPruductsFromFile(LineNumberReader fReader) throws Exception {
        fReader.mark(1024);
        String fileRecord = fReader.readLine();

        while (Product.isProductRecord(fileRecord)) {
            try {
                products.add(new Product(fileRecord));
            } catch (IllegalArgumentException e) {
                break;
            }
            fReader.mark(1024);
            fileRecord = fReader.readLine();
        }
        fReader.reset();

    }

    public static boolean isCategoryRecord(String line) {
        try {
            Matcher m = categoryPattern.matcher(line);
            return m.find();
        } catch (Exception e){
            return false;
        }
    }

    public void printCategoriesAndContent(){
        System.out.println(getName() + ":");
        products.forEach(product -> {
            System.out.println("\t- " + product.getName());
        });
    }
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
