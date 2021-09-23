import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int customerAmount = Integer.parseInt(args[0]);
        List<Customer> customers = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(customerAmount);
        for (int i = 0; i < customerAmount; i++) {
            Customer customer = new Customer();
            customer.setCyclicBarrier(cyclicBarrier);
            customers.add(customer);
            customer.start();
            try {
                customer.join(10);
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
        }
        int boughtItems = 0;
        int purchaseAmount = 0;
        for (Customer customer : customers) {
            boughtItems += customer.getItemsAmount();
            purchaseAmount += customer.getPurchaseAmount();
            System.out.println(customer.getName() + " купил " + customer.getItemsAmount() + " предметов за " + customer.getPurchaseAmount() + " покупок");
        }
        System.out.println("Всего предметов куплено: " + boughtItems);
        System.out.println("Всего покупок совершено: " + purchaseAmount);
        System.out.println("Остаток: " + Storage.getItems());
    }
}
