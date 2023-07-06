package ra.view;

import ra.controller.ProductController;
import ra.model.Product;

import java.util.List;

public class ProductManger {
    public static void displayListProduct(){
        ProductController productController = new ProductController();
        for (Product p: productController.findAll()) {
            if(p.isStatus()){
                System.out.println(p);
            }
        }
    }
}
