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

    public  void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        Storage storage = Storage.getInstance();
        while (!storage.isEmpty()) {
            try {
                cyclicBarrier.await();
                int boughtItems = storage.buyItems(1 + (int) (Math.random() * 10));
                if (boughtItems != 0) {
                    itemsAmount += boughtItems;
                    purchaseAmount++;
                }
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Поток с id " + this.getId() + " остановлен или барьер достиг предела");
            }
        }
    }
}
