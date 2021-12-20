package HashMapMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HahsMapImplementation<K, V> implements HashMapInterface<K, V> {
    private final int INIZIALIZATION_SIZE = 16;
    private final double RESIZE_LIMIT = 0.70;// 70 persantege
    private final double RESIZE_COEFFICIENT = 1.50;// 150 persantege
    int counter; // визначає кількість заповнених бакетів у масиві.
    int size; // кількість всіх елементів

    private Node<K, V>[] tab;
    private Node<K, V> nodeTemporary;

    public HahsMapImplementation() {
        tab = new Node[INIZIALIZATION_SIZE];
    }

    public int findPosition(int code) {
        int positionInTab = Math.abs((code >> 2)) % tab.length;
        return positionInTab;
    }

    // метод, який збільшує розір масиву, у випадку заповнення його на 70%
    // Використовую >> 2 а не >>> 16, оскільки під час моєї простих обкєтів: хеш.код >>> 16 завжди = 0
    private void resize() {
        if (counter < RESIZE_LIMIT * tab.length) {
            return;
        } else {
            size = 0;
            counter = 0;
            Node<K, V>[] tabOld = tab;
            tab = new Node[(int) (tab.length * RESIZE_COEFFICIENT)];
            for (Node<K, V> tmp : tabOld) {
                if (tmp != null) {
                    Node<K, V> nodeTemporary = tmp;
                    while (nodeTemporary != null) {
                        put(nodeTemporary.getKey(), nodeTemporary.getValue());
                        nodeTemporary = nodeTemporary.getNext();
                    }
                }
            }

        }
    }

    @Override
    public void put(K InputKey, V value) {
        Node<K, V> node = new Node(InputKey, value);
        int position = findPosition(node.getKey().hashCode());
        if (tab[position] == null) {
            tab[position] = node;
            counter++;
            size++;
        } else {
            Node nodeTemporary = tab[position];
            while (nodeTemporary.getNext() != null) {
                if (nodeTemporary.getKey().equals(InputKey)) {
                    nodeTemporary.setValue(value);
                    return;
                } else {
                    nodeTemporary = nodeTemporary.getNext();
                }
            }
            if (nodeTemporary.getKey().equals(InputKey)) {
                nodeTemporary.setValue(value);
                return;
            }
            nodeTemporary.setNext(new Node(InputKey, value));
            size++;
        }
        resize();
    }

    //для самопепевірки
    public void show() {
        System.out.println("ВСІ бакети:" + Arrays.toString(tab));
    }

    @Override
    public V get(K key) {
        int position = findPosition(key.hashCode());
        Node<K, V> nodeTemporary = tab[position];
        while (nodeTemporary.getNext() != null) {
            if (nodeTemporary.getKey().equals(key)) {
                return nodeTemporary.getValue();
            } else {
                nodeTemporary = nodeTemporary.getNext();
            }

        }
        if (nodeTemporary.getKey().equals(key)) {
            return nodeTemporary.getValue();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List getKeySet() {
        List<K> list = new ArrayList<K>();
        System.out.println("All Keys:");
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                System.out.print(tab[i].getKey() + "; ");
                Node<K, V> nodeTemporary = tab[i];
                while (nodeTemporary.getNext() != null) {
                    list.add(nodeTemporary.getKey());
                    nodeTemporary = nodeTemporary.getNext();
                }
            }
        }
        return list;
    }

    @Override
    public List getValues() {
        List<V> list = new ArrayList<>();
        System.out.println("All Values:");
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                Node<K, V> nodeTemporary = tab[i];
                System.out.print(nodeTemporary.getValue() + "; ");
                while (nodeTemporary.getNext() != null) {
                    list.add(nodeTemporary.getValue());
                    nodeTemporary = nodeTemporary.getNext();
                }
            }
        }
        return list;
    }
}

class Node<K, V> {

    private K key; //private
    private V value;
    private Node next;

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


    @Override
    public String toString() {
        return "{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

}
