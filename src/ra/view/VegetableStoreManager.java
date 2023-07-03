package ra.view;

import ra.config.InputMethods;
import ra.controller.UserController;
import ra.model.RoleName;
import ra.model.User;

public class VegetableStoreManager {
    private static UserController userController= new UserController();
    private static User userLogin;
    public static void main(String[] args) {
        while (true) {
            Navbar.menuStore();
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice){
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
    public static void login(){
        System.out.println("-------------Sign-In-------------");
        System.out.println("Enter username");
        String username = InputMethods.getString();
        System.out.println("Enter password");
        String password = InputMethods.getString();
        // kiem tra dang nhap
        User user =userController.login(username,password);
        if (user==null){
            System.err.println("");
        }else {
            if (user.getRoles().contains(RoleName.ADMIN)){
               userLogin=user;
            }else {
                if(user.isStatus()){
                    userLogin=user;
                }else {

                }
            }
        }

    };
    public static void register(){
        System.out.println("-------------Sign_Up-------------");
        System.out.println("Enter Id");

    }
    public static void logOut(){

    }


}
