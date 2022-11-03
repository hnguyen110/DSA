public class Main {
    public static void main(String[] args) {
        var table = new HashTable(5);
        table.put(1, "Hello");
        table.put(2, "World");
        table.put(11, "World");
        System.out.println(table.get(1));
        System.out.println(table.get(2));
        System.out.println(table.get(11));
        System.out.println(table.get(12));
        table.remove(1);
        table.remove(2);
        table.remove(11);
        table.remove(11);
        System.out.println(table.get(1));
        System.out.println(table.get(2));
        System.out.println(table.get(11));
    }
}
