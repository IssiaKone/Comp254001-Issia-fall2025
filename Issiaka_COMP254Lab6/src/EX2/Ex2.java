package EX2;

import java.util.ArrayList;
import java.util.List;

class SortedTableMap<K extends Comparable<K>, V> {

    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K k, V v) { key = k; value = v; }
        public K getKey()   { return key; }
        public V getValue() { return value; }
        public V setValue(V v) { V old = value; value = v; return old; }
        @Override
        public String toString() { return "(" + key + ", " + value + ")"; }
    }


    private List<Entry<K, V>> table = new ArrayList<>();

    public int size()           { return table.size(); }
    public boolean isEmpty()    { return size() == 0; }


    private int findIndex(K key) {
        int low = 0, high = table.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareTo(table.get(mid).getKey());
            if (cmp == 0) return mid;
            else if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    public V get(K key) {
        int j = findIndex(key);
        if (j == size() || !table.get(j).getKey().equals(key)) return null;
        return table.get(j).getValue();
    }

    public V put(K key, V value) {
        int j = findIndex(key);
        if (j < size() && table.get(j).getKey().equals(key)) {
            return table.get(j).setValue(value);
        }
        table.add(j, new Entry<>(key, value));
        return null;
    }

    public V remove(K key) {
        int j = findIndex(key);
        if (j == size() || !table.get(j).getKey().equals(key)) return null;
        V old = table.get(j).getValue();
        table.remove(j);
        return old;
    }


    public boolean containsKey(K key) {
        int j = findIndex(key);
        return j < size() && table.get(j).getKey().equals(key);
    }


    @Override
    public String toString() {
        return table.toString();
    }
}

// Test Application
 class SortedTableMapTest {
    public static void main(String[] args) {
        SortedTableMap<Integer, String> map = new SortedTableMap<>();

        map.put(10, "Ten");
        map.put(5,  "Five");
        map.put(20, "Twenty");
        map.put(15, "Fifteen");

        System.out.println("Map: " + map);
        System.out.println("containsKey(10)  = " + map.containsKey(10));  // true
        System.out.println("containsKey(7)   = " + map.containsKey(7));   // false
        System.out.println("containsKey(20)  = " + map.containsKey(20));  // true
        System.out.println("get(15)          = " + map.get(15));          // Fifteen
        System.out.println("remove(5)        = " + map.remove(5));        // Five
        System.out.println("containsKey(5)   = " + map.containsKey(5));   // false
        System.out.println("Final map: " + map);
    }
}
