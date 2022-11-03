import java.util.LinkedList;

public class HashTable {
    private final LinkedList<Entry>[] entries;

    public HashTable(int capacity) {
        this.entries = new LinkedList[capacity];
    }

    public void put(int key, String value) {
        var item = getEntry(key);
        if (item != null) {
            item.value = value;
            return;
        }
        var bucket = getOrCreateBucket(key);
        bucket.addLast(new Entry(key, value));
    }

    public String get(int key) {
        var item = getEntry(key);
        return item == null ? null : item.value;
    }

    public LinkedList<Entry> getBucket(int key) {
        return entries[hash(key)];
    }

    public LinkedList<Entry> getOrCreateBucket(int key) {
        int index = hash(key);
        if (entries[index] == null)
            entries[index] = new LinkedList<>();
        return entries[index];
    }

    public void remove(int key) {
        var bucket = getBucket(key);
        if (bucket != null) bucket.removeIf(item -> item.key == key);
    }

    private int hash(int key) {
        return key % entries.length;
    }

    private Entry getEntry(int key) {
        var index = hash(key);
        var bucket = entries[index];
        if (bucket != null)
            for (var item : bucket)
                if (item.key == key)
                    return item;
        return null;
    }

    private static class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
