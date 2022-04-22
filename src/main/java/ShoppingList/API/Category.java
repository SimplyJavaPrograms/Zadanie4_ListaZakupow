package ShoppingList.API;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Category {


    private String name;
    private Vector<Product> products;
    private static final String categoryRecordRegEx = "^[^\\s].*";
    private static Pattern categoryPattern = Pattern.compile(categoryRecordRegEx);


    public Category(String name) {
        setName(name);
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


    private Vector<Product> initProductsArray() {
        return new Vector<Product>();
    }

    public void loadPruductsFromFile(LineNumberReader fReader) throws Exception {
        fReader.mark(1024);
        String fileRecord = fReader.readLine();

        while (Product.isProductRecord(fileRecord)) {
            try {
                pushProduct(fileRecord);
            } catch (IllegalArgumentException e) {
                break;
            }
            fReader.mark(1024);
            fileRecord = fReader.readLine();
        }
        fReader.reset();

    }
    public void pushProduct(String product){
        products.add(new Product(product));
    }
    public void popProduct(String productName){
        products.remove(new Product(productName));
    }

    private Product getProductByName(String categoryName){
        int index = products.indexOf(new Category(categoryName));

        if(index == -1)
            return null;

        Product product = products.get(index);
        return product;
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
        System.out.println(generateStringWithContent());
    }
    public String generateStringWithContent(){
        StringBuffer output = new StringBuffer();

        output.append(getName() + ":");
        products.forEach(product -> {
            output.append("\n\t- " + product.getName());
        });

        return new String(output);
    }
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Category other = (Category) obj;

        return this.getName().equals(other.getName());
    }
}
