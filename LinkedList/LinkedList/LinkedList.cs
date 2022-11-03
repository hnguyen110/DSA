namespace LinkedList;

public class LinkedList
{
    private Node? _head;
    private Node? _tail;
    private int _size;

    public LinkedList()
    {
        _head = null;
        _tail = null;
        _size = 0;
    }

    public void AddLast(int value)
    {
        var node = new Node {Value = value, Next = null};
        if (_tail == null)
        {
            _head = _tail = node;
        }
        else
        {
            _tail.Next = node;
            _tail = node;
        }

        _size += 1;
    }

    public void AddFirst(int value)
    {
        var node = new Node {Value = value, Next = null};
        if (_head == null)
        {
            _head = _tail = node;
        }
        else
        {
            var previous = _head;
            _head = node;
            _head.Next = previous;
        }

        _size += 1;
    }

    public int IndexOf(int value)
    {
        var index = 0;
        var node = _head;
        while (node != null)
        {
            if (node.Value == value)
            {
                return index;
            }

            index += 1;
            node = node.Next;
        }

        return -1;
    }

    public bool Contains(int value)
    {
        return IndexOf(value) != -1;
    }

    public void RemoveFirst()
    {
        if (_head == null) return;
        if (_head == _tail)
        {
            _head = _tail = null;
        }
        else
        {
            var next = _head.Next;
            _head.Next = null;
            _head = next;
        }

        _size -= 1;
    }

    public void RemoveLast()
    {
        if (_tail == null || _head == null) return;
        if (_head == _tail)
        {
            _head = _tail = null;
        }
        else
        {
            var previous = _head;
            var node = _head;
            while (true)
            {
                if (node.Next == null)
                {
                    _tail = previous;
                    _tail.Next = null;
                    break;
                }

                previous = node;
                node = node.Next;
            }
        }

        _size -= 1;
    }

    public int[] ToArray()
    {
        var index = 0;
        var array = new int[_size];
        var node = _head;
        while (node != null)
        {
            array[index++] = node.Value;
            node = node.Next;
        }

        return array;
    }

    public int Size()
    {
        return _size;
    }

    public void ReverseThisShit()
    {
        var previous = _head;
        var current = _head?.Next;
        while (current != null)
        {
            var next = current.Next;
            current.Next = previous;
            previous = current;
            current = next;
        }

        _tail = _head;
        if (_tail != null) _tail.Next = null;
        _head = previous;
    }

    public int? GetKthFromTheEnd(int step)
    {
        if (step < 0) return null;
        if (_head == null) return null;
        if (step > _size) return null;
        var first = _head;
        var second = _head;
        for (var index = 0; index < step - 1; index++)
        {
            second = second?.Next;
        }

        while (second != _tail)
        {
            first = first?.Next;
            second = second?.Next;
        }

        return first?.Value ?? null;
    }

    public int[] FindMiddle()
    {
        var first = _head;
        var second = _head;
        while (second != _tail && second?.Next != _tail)
        {
            second = second?.Next?.Next;
            first = first?.Next;
        }

        return second == _tail ? new[] {first!.Value} : new[] {first!.Value, first.Next!.Value};
    }

    public bool HasLoop()
    {
        var first = _head;
        var second = _head;
        while (second is {Next: { }})
        {
            first = first?.Next;
            second = second.Next.Next;
            if (first == second)
            {
                return true;
            }
        }

        return false;
    }

    public LinkedList CreateWithLoop()
    {
        var list = new LinkedList();
        list.AddLast(10);
        list.AddLast(20);
        list.AddLast(30);
        var node = list._tail;
        list.AddLast(40);
        list.AddLast(50);
        if (list._tail != null) list._tail.Next = node;
        return list;
    }

    public void Print()
    {
        var node = _head;
        while (node != null)
        {
            Console.WriteLine(node.Value);
            node = node.Next;
        }
    }
}