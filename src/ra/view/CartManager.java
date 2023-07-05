package ra.view;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.controller.CartController;
import ra.controller.ProductController;
import ra.model.CartItem;
import ra.model.Product;
import ra.model.User;

public class CartManager {
    private static CartController cartController = new CartController(Navbar.userLogin);

    public CartManager() {
        while (true) {
            Navbar.menuCart();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // xem danh sách giỏ hàng
                    showCart();
                    break;
                case 2:
                    // chỉnh sửa số lượng
                    changeQuantity();
                    break;
                case 3:
                    // xóa 1 item
                    deleteItem();
                    break;
                case 4:
                    // xóa hêt
                    cartController.clearAll();
                    break;
                case 5:
                    Navbar.menuUser();
                    break;
                default:
                    System.err.println("please enter number from 1 to 5");
            }

        }
    }
    public void showCart(){
        User userLogin = Navbar.userLogin;
        if(userLogin.getCart().isEmpty()){
            System.err.println("Cart is Empty");
            return;
        }
        for (CartItem ci:userLogin.getCart()
             ) {
            System.out.println(ci);
        }
    }
    public void changeQuantity(){
        System.out.println("Enter cartitemId");
        int cartItemID = InputMethods.getInteger();
        CartItem cartItem = cartController.findById(cartItemID);
        if(cartItem ==null){
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        System.out.println("Enter Quantity");
        cartItem.setQuantity(InputMethods.getInteger());
        cartController.save(cartItem);

    }
    public void deleteItem(){
        System.out.println("Enter cartitemId");
        int cartItemID = InputMethods.getInteger();
        if(cartController.findById(cartItemID) ==null){
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        cartController.delete(cartItemID);
    }

    public static void addToCart() {
        ProductController productController = new ProductController();
        System.out.println("Enter Product id");
        int proId = InputMethods.getInteger();
        Product pro = productController.findById(proId);
        if (pro == null) {
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(cartController.getNewId());
        cartItem.setProduct(pro);
        System.out.println("Enter quantity");
        cartItem.setQuantity(InputMethods.getInteger());
        cartController.save(cartItem);
    }
}
