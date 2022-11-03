namespace LinkedList;

internal static class Program
{
    public static void Main(string[] args)
    {
        var list = new LinkedList();
        list = list.CreateWithLoop();
        Console.WriteLine(list.HasLoop());
    }
}