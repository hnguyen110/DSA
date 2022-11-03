namespace Array;

public class Array
{
    protected int _counter;
    protected int[] _items;

    public Array(int length)
    {
        _counter = 0;
        _items = new int[length];
    }

    public void Insert(int item)
    {
        if (_items.Length == _counter)
        {
            var resizedItems = new int[_counter * 2];
            for (var index = 0; index < _counter; index++)
            {
                resizedItems[index] = _items[index];
            }

            _items = resizedItems;
        }

        _items[_counter++] = item;
    }

    public void RemoveAt(int position)
    {
        if (position < 0 || position >= _counter)
        {
            throw new Exception("The index is out of range");
        }

        for (var index = position; index < _counter - 1; ++index)
        {
            _items[index] = _items[index + 1];
        }

        _counter--;
    }

    public int IndexOf(int item)
    {
        for (var index = 0; index < _counter; index++)
        {
            if (item == _items[index])
            {
                return index;
            }
        }

        return -1;
    }

    public void Print()
    {
        foreach (var item in _items)
        {
            Console.WriteLine(item);
        }
    }
}