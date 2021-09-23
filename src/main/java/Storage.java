public class Storage {
    private static volatile int items = 1000;

    public static int getItems() {
        return items;
    }

    public static synchronized int buyItems (int itemsAmount) {
        if (items < itemsAmount) {
            itemsAmount = items;
            items = 0;
        } else {
            items -= itemsAmount;
        }
        return itemsAmount;
    }
}
