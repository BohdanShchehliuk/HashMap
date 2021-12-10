package HashMapMethods;

import java.util.Arrays;

public class HahsMapImplementation<K, V> implements HashMapInterface<K, V> {
    private final int INIZIALIZATION = 10;
    int counter = 0;

    Node<K, V>[] tab = new Node[INIZIALIZATION];

    @Override
    public void put(K key, V value) {
        Node<K, V> node = new Node();
        node.key = key;
        node.value = value;
        tab[counter] = node;
        counter++;
    }

    public void show() {
        System.out.println(Arrays.toString(tab));
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].key == key) {
                return tab[i].value;
            } else return null;
        }
        return null;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public K getKeySet() {
         return null;
    }

    @Override
    public V getValues() {
        return null;
    }
}
