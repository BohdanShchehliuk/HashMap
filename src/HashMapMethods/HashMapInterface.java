package HashMapMethods;

import java.util.List;

public interface HashMapInterface<K, V> {
    void put(K Inputkey, V value);

    V get(K key);

    int size();

    List<V> getKeySet();

    List<K> getValues();
}
