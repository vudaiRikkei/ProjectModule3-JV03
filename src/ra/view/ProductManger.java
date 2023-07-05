package ra.view;

import ra.model.Product;

import java.util.List;

public class ProductManger {
    public static void displayListProduct(List<Product> list){
        for (Product p: list) {
            if(p.isStatus()){
                System.out.println(p);
            }
        }
    }
}
