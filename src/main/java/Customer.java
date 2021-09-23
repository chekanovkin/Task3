import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer extends Thread {

    private int itemsAmount;

    private int purchaseAmount;

    private CyclicBarrier cyclicBarrier;

    public int getItemsAmount() {
        return itemsAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void addItems(int items) {
        itemsAmount += items;
    }

    public  void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        while (Storage.getItems() != 0) {
            try {
                cyclicBarrier.await(); // не понял, почему это нужно делать, нашел методом тыка
                int boughtItems = Storage.buyItems(1 + (int) (Math.random() * 10));
                if (boughtItems != 0) {
                    addItems(boughtItems);
                    purchaseAmount++;
                }
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Поток с id " + this.getId() + " остановлен или барьер достиг предела");
            }
        }
    }
}
