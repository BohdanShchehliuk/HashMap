package HashMapMethods;


import java.util.Map;

class Node<K, V> {

    private K key; //private
    private V value;
    private Node next;
    private final int HASH; //private

    public void setNext(Node next) {
        this.next = next;
    }

    public void setValue(V value) {
        this.value = value;
    }
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.HASH = key.hashCode();
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public int getHASH() {
        return HASH;
    }
    @Override
    public String toString() {
        return "{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

}
