package map;



public class HashMapTest<K,V> {
    private Entry<K,V>[] table ;
    private static Integer CAPITICY=8;
    private int size=0;
    public HashMapTest(){
        table=new Entry[CAPITICY];
    }

    public int size() {
        return size;
    }






    public V put(K key, V value) {
        //算下标
        int hash = key.hashCode();
        int index = hash % table.length;
        for (Entry<K,V> entry = table[index]; entry != null; entry = entry.next) {
            if(entry.k.equals(key)){
                V oldValue=entry.v;
                entry.v=value;
                return oldValue;
            }
        }
        addMethod(key,value, index);
        return null;
    }

    private void addMethod(K key, V value, int index) {

        Entry entry=new Entry(key,value,table[index]);
        table[index]=entry;
        size++;
    }
    public V get(Object key) {
        //算下标
        int hash = key.hashCode();
        int index = hash % table.length;
        for (Entry<K,V> entry = table[index]; entry != null; entry = entry.next) {
            if(entry.k.equals(key)){
                return entry.v;
            }
        }
        return null;
    }


    class Entry<K,V>{
        private K k;
        private V v;
        private Entry<K,V> next;

    public K getK() {
        return k;
    }


    public void setV(V v) {
        this.v = v;
    }

    public Entry(K k, V v, Entry<K, V> next) {
        this.k = k;
        this.v = v;
        this.next = next;
    }

    public Entry() {
    }
}

    public static void main(String[] args) {
        HashMapTest<String, String> hashMapTest=new HashMapTest<String, String>();
        hashMapTest.put("123","zhangsan");
        System.out.println(hashMapTest.get("123"));
    }


}

