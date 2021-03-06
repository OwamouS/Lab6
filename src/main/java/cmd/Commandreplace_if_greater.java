package cmd;
import Control.TableController;
import productdata.Product;
import productdata.ReaderProductBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * replace new product with old one if price of new product is higher
 *
 *
 */

public class Commandreplace_if_greater implements Command{

    @Override
    public void execute(String[] args) {
        for (Map.Entry<String, Product> map : TableController.getCurrentTable().getSet()) {
            if (map.getKey().compareTo(args[0]) == 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Product product = ReaderProductBuilder.buildProduct(reader);
                if (product != null && product.getPrice() > map.getValue().getPrice()) {
                    TableController.getCurrentTable().replace(map.getKey(), product);
                }
            }
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "replace_if_greater";
    }
}
