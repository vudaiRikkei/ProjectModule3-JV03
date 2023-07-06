package ra.view;

import ra.config.InputMethods;
import ra.controller.ProductController;
import ra.controller.UserController;
import ra.model.RoleName;
import ra.model.User;
import ra.service.CartService;


import java.util.Arrays;
import java.util.List;


public class Navbar {
    private static UserController userController = new UserController();
    private static ProductController productController = new ProductController();

    public static User userLogin;
    // tat ca menu dao dien dieu huong

    public static void menuStore() {
        while (true) {
            System.out.println("-------------Menu-Store-------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. View product");
            System.out.println("4. Exit");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:

                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }

        }
    }

    public static void menuUser() {
        while (true) {
            System.out.println("-------------Menu-User-------------");
            System.out.println("1. Show list Product");
            System.out.println("2. Add to cart");
            System.out.println("3. View Cart");
            System.out.println("4. Change Profile");
            System.out.println("5. Change Password");
            System.out.println("6. Order history");
            System.out.println("0. Log Out");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // hiển thị danh sách sanr phẩm
                    ProductManger.displayListProduct();
                    break;
                case 2:
                    // mua hàng
                    CartManager.addToCart();
                    break;
                case 3:
                    // quan li gió hàng
                    new CartManager();
                    break;
                case 6:
                    // lịch sử mua hàng
                    new OrderManager();
                    break;
                case 0:
                    logOut();
                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void menuAdmin() {
        while (true){
        System.out.println("-------------Menu-Admin-------------");
        System.out.println("1. Account Manager");
        System.out.println("2. Catalog Manager");
        System.out.println("3. Product Manager");
        System.out.println("4. Order Manager");
        System.out.println("0. Logout");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                // Quản lí tài khoản người dùng
                new UserMananger(userController);
                break;
            case 2:

                break;
            case 3:

                break;
                case 4:

                break;
            case 0:
                ///
                logOut();
                break;
            default:
                System.err.println("please enter number from 1 to 4");
        }
        if (choice == 0) {
            break;
        }
    }
    }
    public  static void menuAccountManager(){
        System.out.println("-------------Menu-Account-Manager-------------");
        System.out.println("1. Show All Acount");
        System.out.println("2. Block/Unblock Acount");
        System.out.println("3. Back");
    }
    public  static void menuCart(){
        System.out.println("-------------Menu-Cart-------------");
        System.out.println("1. Show Cart");
        System.out.println("2. Change quantity");
        System.out.println("3. Delete item");
        System.out.println("4. Delete all");
        System.out.println("5. Check out");
        System.out.println("6. Back");
    }

    public static void login() {
        System.out.println("-------------Sign-In-------------");
        System.out.println("Enter username");
        String username = InputMethods.getString();
        System.out.println("Enter password");
        String password = InputMethods.getString();
        // kiem tra dang nhap
        User user = userController.login(username, password);
        if (user == null) {
            System.err.println("");
        } else {
            if (user.getRoles().contains(RoleName.ADMIN)) {
                userLogin = user;
                menuAdmin();

            } else {
                if (user.isStatus()) {
                    userLogin = user;
                    menuUser();
                } else {
                    System.err.println("Your account is blocked");
                    login();
                }
            }
        }

    }

    ;

    public static void register() {
        System.out.println("-------------Sign_Up-------------");
        User user = new User();
        user.setId(userController.getNewId());
        System.out.println("ID : " + user.getId());
        System.out.println("Enter Name");
        user.setName(InputMethods.getString());
        System.out.println("Enter Username");
        user.setUsername(InputMethods.getString());
        System.out.println("Enter Password");
        user.setPassword(InputMethods.getString());
        System.out.println("Enter Roles: (etc: user,admin,...)");
        String roles = InputMethods.getString();
        String[] stringRoles = roles.split(",");
        List<String> listRoles = Arrays.asList(stringRoles);
        for (String r : stringRoles) {
            // loi dụng co che break
            switch (r) {
                case "admin":
                    user.getRoles().add(RoleName.ADMIN);
                case "manager":
                    user.getRoles().add(RoleName.MANAGER);
                case "user":
                    user.getRoles().add(RoleName.USER);
                default:
                    user.getRoles().add(RoleName.USER);
            }
        }
//        user.getRoles() = listRoles.stream().map(
//                r->{
//                    //
//                }
//        ).collect(Collectors.toList());
        userController.save(user);
        System.out.println("Đăng kí thành công");
        System.out.println("Vui long đăng nhập");
    }

    public static void logOut() {
        userLogin = null;
        menuStore();
        // con tro ddung
    }

}
