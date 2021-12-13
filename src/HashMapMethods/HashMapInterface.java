package HashMapMethods;

public interface HashMapInterface <K, V>{
    void  put(K key, V value);
    V get(K key);
    int size();
    void getKeySet();
    void getValues();
}
