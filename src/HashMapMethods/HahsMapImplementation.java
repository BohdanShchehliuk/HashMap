package HashMapMethods;

import java.util.Arrays;

public class HahsMapImplementation<K, V> implements HashMapInterface<K, V> {
    private final int INIZIALIZATION_SIZE = 16;
    private final double RESIZE_LIMIT = 0.70;// 70 persantege
    private final double RESIZE_COEFFICIENT = 1.50;// 150 persantege
       int counter;
    int size; // кількість всіх елементів

    private Node<K, V>[] tab;

    public HahsMapImplementation() {
        tab = new Node[INIZIALIZATION_SIZE];
    }
    public int position(int code) {
        int position = Math.abs((code >> 2)) % tab.length;
        return position;
    }

// метод, який збільшує розір масиву, у випадку заповнення його на 70%
   // Використовую >> 2 а не >>> 16, оскільки під час моєї простих обкєтів: хеш.код >>> 16 завжди = 0
    private void resize() {
        if (counter < RESIZE_LIMIT * tab.length) {
            return;
        } else {
            Node<K, V>[] tabNew = new Node[(int) (tab.length * RESIZE_COEFFICIENT)];
            for (Node tmp : tab) {
                if (tmp != null) {
                   tabNew[position(tmp.getHASH())] = tmp;
                }
            }
            tab = tabNew;
        }
    }



    @Override
    public void put(K key, V value) {
        Node<K, V> node = new Node(key, value);
        int position = position(node.getHASH());
        if (tab[position] == null) {
            tab[position] = node;
            counter++;
            size++;
        } else if (tab[position].getKey().equals(key)) {
            tab[position] = node;
        } else {
            Node nodeOne = tab[position];
            while (nodeOne.getNext() != null) {
                if (nodeOne.getKey().equals(key)) {
                    nodeOne.setValue(value);
                    return;
                } else {
                    nodeOne = nodeOne.getNext();
                }
            }
            nodeOne.setNext(new Node(key, value));
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
        if (key.equals(null)) {
            return null;
        } else {
            int position = position(key.hashCode());
            Node nodeOne = tab[position];
            while (nodeOne.getNext() != null) {
                if (nodeOne.getKey().equals(key)) {
                    return (V) nodeOne.getValue();
                } else {
                    nodeOne = nodeOne.getNext();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void getKeySet() {
        int printNumbOfKey = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                System.out.print(tab[i].getKey() + "; ");
                printNumbOfKey++;
                Node nodeOne = tab[i];
                nodeOne = nodeOne.getNext();
                while (nodeOne != null) {
                    System.out.print(nodeOne.getKey() + "; ");
                    printNumbOfKey++;
                    nodeOne = nodeOne.getNext();
                }
            }
        }
        System.out.println("\nNuymber of Keys:" + printNumbOfKey);
    }

    @Override
    public void getValues() {
        int printNumbOfValuses = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                Node nodeOne = tab[i];
                System.out.print(nodeOne.getValue() + "; ");
                printNumbOfValuses++;
                nodeOne = nodeOne.getNext();
                while (nodeOne != null) {
                    System.out.print(nodeOne.getValue() + "; ");
                    printNumbOfValuses++;
                    nodeOne = nodeOne.getNext();
                }
            }
        }
        System.out.println("\nNuymber of Values:" + printNumbOfValuses);
    }
}
