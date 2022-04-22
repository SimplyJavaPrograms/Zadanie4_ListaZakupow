package ShoppingList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product {
    private String name;
    private static String productRecordRegEx = "^[\\s].*";
    private static Pattern productPattern = Pattern.compile(productRecordRegEx);

    public Product(String productName) {
        setName(productName);
    }

    void setName(String productName) {
        this.name = getTrimmedProductName(productName);

    }

    String getName() {
        return this.name;
    }

    String getTrimmedProductName(String productName) {
        return productName.replaceFirst("^\\s{1,}", "");
    }

    public static boolean isProductRecord(String line) {
        try {
            Matcher m = productPattern.matcher(line);
            return m.find();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
