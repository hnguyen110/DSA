namespace Array;

public class AntonArray : Array, IAnton
{
    public AntonArray(int length) : base(length)
    {
    }

    public int Max()
    {
        return _items.Max();
    }

    public int[] Intersect(int[] array)
    {
        return _items.Intersect(array).ToArray();
    }

    public void Reverse()
    {
        _items = _items.Reverse().ToArray();
    }

    public void InsertAt(int item, int position)
    {
        if (position < 0 || position >= _counter)
        {
            throw new Exception("The index is out of range");
        }

        if (_items.Length == _counter)
        {
            var resizedItems = new int[_counter * 2];
            for (var index = 0; index < _counter; index++)
            {
                resizedItems[index] = _items[index];
            }

            _items = resizedItems;
        }

        for (var index = ++_counter; index > position; index--)
        {
            _items[index] = _items[index - 1];
        }

        _items[position] = item;
    }
}