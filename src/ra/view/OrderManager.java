package ra.view;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.controller.OrderController;
import ra.model.CartItem;
import ra.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager {
    private OrderController orderController;
    public OrderManager() {
        orderController= new OrderController();
        while (true){
            System.out.println("-------------Order Histry-------------");
            System.out.println("1. Show all Order");
            System.out.println("2. Show order waiting ...");
            System.out.println("3. Show order accepted");
            System.out.println("4. Show order canceled");
            System.out.println("5. Show order detail");
            System.out.println("0. Back");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // hiển thị tất cả
                    showAllOrder();
                    break;
                case 2:
                    // chờ xác nhận
                    showOrderByCode((byte) 0);
                    break;
                case 3:
                    showOrderByCode((byte) 1);
                    break;
                case 4:
                    showOrderByCode((byte) 2);
                    break;
                case 5:
                    // chi tiết hóa đơn
                    showOrderDetail();
                    break;
                    case 0:

                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }
            if (choice == 0) {
                break;
            }
        }

    }
    public void showAllOrder(){
        List<Order> list = orderController.findOrderByUserId();

        if (list.isEmpty()){
            System.err.println("History is empty");
            return;
        }
        for (Order o :list) {
            System.out.println(o);
        }
    }
    public void showOrderByCode(byte code){
        List<Order> orders = orderController.findOrderByUserId();
        List<Order> filter = new ArrayList<>();
        for (Order o :orders) {
           if(o.getStatus()==code){
               filter.add(o);
           }
        }
        if (filter.isEmpty()){
            System.err.println("order is empty");
            return;
        }
        for (Order o :filter) {
            System.out.println(o);
        }
    }
        public  void showOrderDetail(){
            System.out.println("Enter order ID");
            int orderId = InputMethods.getInteger();
            Order order = orderController.findById(orderId);
            if (order ==null){
                System.err.println(Constants.NOT_FOUND);
                return;
            }

            // in ra chi tiết hóa đơn
            System.out.printf("---------------------OrderDetail-----------------------\n");
            System.out.printf("                    Id:%5d                              \n",order.getId());
            System.out.println("--------------------Infomation--------------------------");
            System.out.print("Receiver: "+order.getReceiver() + "| Phone : "+order.getPhoneNumber()+"\n");
            System.out.println("Address : "+order.getAddress());
            System.out.println("--------------------Detail-------------------------------");
            for (CartItem ci: order.getOrderDetail()){
                System.out.println(ci);
            }
            System.out.println("Total : "+order.getTotal());
            System.out.println("------------------------End------------------------------");
            if(order.getStatus()==0){
                System.out.println("Do you want to cancel this order?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your choice");
                int choice = InputMethods.getInteger();
                if (choice==1){
                    // hủy
                    order.setStatus((byte) 2);
                    orderController.save(order);
                }
            }
        }
}
