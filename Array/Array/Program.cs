namespace Array;

internal static class Program
{
    public static void Main(string[] args)
    {
        var array = new AntonArray(5);
        array.Insert(1);
        array.Insert(2);
        array.Insert(3);
        array.Insert(4);
        array.Insert(5);
        array.InsertAt(999, 2);
        int[] intersectedArray = {1, 2, 3};
        var result = array.Intersect(intersectedArray);
        foreach (var item in result)
        {
            Console.WriteLine(item);
        }
    }
}

