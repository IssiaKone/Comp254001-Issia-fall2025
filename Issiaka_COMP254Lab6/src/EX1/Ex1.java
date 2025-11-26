package EX1;
import java.util.Random;
import java.util.*;


abstract class AbstractHashMap<K,V> {
    protected int n = 0, capacity, prime;
    protected long scale, shift;
    protected double maxLoadFactor;

    public AbstractHashMap(int cap, int p, double maxLoad) {
        capacity = cap; prime = p; maxLoadFactor = maxLoad;
        Random r = new Random();
        scale = r.nextInt(prime - 1) + 1;
        shift = r.nextInt(prime);
        createTable();
    }
    public AbstractHashMap(int cap) { this(cap,109345121,0.5); }

    protected abstract void createTable();
    protected abstract V bucketGet(int h,K k);
    protected abstract V bucketPut(int h,K k,V v);
    protected abstract V bucketRemove(int h,K k);
    protected abstract Object[] backup();

    private int hash(K k) {
        return (int)((Math.abs(k.hashCode()*scale+shift)%prime)%capacity);
    }
    public V get(K k){ return bucketGet(hash(k),k); }
    public V put(K k,V v){
        V ans = bucketPut(hash(k),k,v);
        if(n > maxLoadFactor*capacity) resize(2*capacity-1);
        return ans;
    }
    public V remove(K k){ return bucketRemove(hash(k),k); }

    private void resize(int newCap) {
        Object[] old = backup();
        capacity = newCap; n = 0;
        createTable();
        for(Object o: old) {
            if(o instanceof Entry) {
                Entry<K,V> e=(Entry<K,V>)o;
                if(e!=null && e.key!=null) put(e.key,e.value);
            }
        }
    }

    public static class Entry<K,V>{
        K key; V value;
        Entry(K k,V v){ key=k; value=v; }
        public K getKey(){ return key; }
        public V getValue(){ return value; }
    }
}


class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
    private List<Entry<K,V>>[] table;

    public ChainHashMap(int cap,double load){ super(cap,109345121,load); }

    @Override
    protected void createTable() {
        table = new List[capacity];
        for(int i=0;i<capacity;i++) table[i]=new ArrayList<>();
    }

    protected V bucketGet(int h,K k){
        for(Entry<K,V> e: table[h]) if(e.key.equals(k)) return e.value;
        return null;
    }
    protected V bucketPut(int h,K k,V v){
        for(Entry<K,V> e: table[h])
            if(e.key.equals(k)){ V old=e.value; e.value=v; return old; }
        table[h].add(new Entry<>(k,v)); n++; return null;
    }
    protected V bucketRemove(int h,K k){
        Iterator<Entry<K,V>> it = table[h].iterator();
        while(it.hasNext()){
            Entry<K,V> e=it.next();
            if(e.key.equals(k)){ V old=e.value; it.remove(); n--; return old; }
        }
        return null;
    }
    protected Object[] backup(){ return table.clone(); }
}


class ProbeHashMap<K,V> extends AbstractHashMap<K,V>{
    private Entry<K,V>[] table;
    private Entry<K,V> DEF = new Entry<>(null,null);

    public ProbeHashMap(int cap,double load){ super(cap,109345121,load); }

    protected void createTable(){ table = new Entry[capacity]; }

    private int findSlot(int h,K k){
        int avail=-1, j=h;
        do{
            if(table[j]==null){
                if(avail<0) avail=j;
                break;
            }
            if(table[j]==DEF){
                if(avail<0) avail=j;
            } else if(table[j].key.equals(k)) return j;
            j=(j+1)%capacity;
        }while(j!=h);
        return -(avail+1);
    }

    protected V bucketGet(int h,K k){
        int s=findSlot(h,k);
        return s>=0? table[s].value: null;
    }
    protected V bucketPut(int h,K k,V v){
        int s=findSlot(h,k);
        if(s>=0){ V old=table[s].value; table[s].value=v; return old; }
        int ins=-(s+1);
        table[ins]=new Entry<>(k,v); n++; return null;
    }
    protected V bucketRemove(int h,K k){
        int s=findSlot(h,k);
        if(s>=0){ V old=table[s].value; table[s]=DEF; n--; return old; }
        return null;
    }
    protected Object[] backup(){ return table.clone(); }
}


class HashMapPerformanceTest {
    public static void main(String[] args) {
        double[] loads={0.3,0.5,0.7,0.9};
        int N=30000;
        Random r=new Random();

        for(double lf: loads){
            ChainHashMap<Integer,Integer> map = new ChainHashMap<>(17,lf);
            long t1=System.nanoTime();
            for(int i=0;i<N;i++) map.put(r.nextInt(),i);
            long t2=System.nanoTime();
            System.out.println("LF="+lf+" | time="+(t2-t1)/1e6+"ms | size="+map.n);
        }
    }
}
