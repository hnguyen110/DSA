public class Main {
    public static void main(String[] args) {
        var radix = new Radix();
        radix.insert("cat");
        radix.insert("canada");
        radix.insert("done");
        radix.insert("hello");
        radix.insert("truck");
        radix.insert("tree");

        var list = radix.findWords(null);
        System.out.println(list);
    }
}