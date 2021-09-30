public class Storage {
    private static volatile Storage instance;

    private int items;

    public boolean isEmpty() {
        return items <= 0;
    }

    private Storage() {
        items = 1000;
    }

    public static Storage getInstance() {
        if (instance == null) {
            synchronized (Storage.class) {
                if (instance == null) {
                    instance = new Storage();
                }
            }
        }
        return instance;
    }

    public synchronized int buyItems (int itemsAmount) {
        if (items < itemsAmount) {
            itemsAmount = items;
            items = 0;
        } else {
            items -= itemsAmount;
        }
        return itemsAmount;
    }
}
