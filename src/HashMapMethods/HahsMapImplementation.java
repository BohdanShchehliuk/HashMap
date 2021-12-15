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
                    Node<K, V> nodeOne = tmp;
                    while (nodeOne != null) {
                        put(nodeOne.getKey(), nodeOne.getValue());
                        nodeOne = nodeOne.getNext();
                    }
                }
            }

        }
    }

    @Override
    public void put(K InputKey, V value) {
        Node<K, V> node = new Node(InputKey, value);
        int position = findPosition(node.getHASH());
        if (tab[position] == null) {
            tab[position] = node;
            counter++;
            size++;
        } else {
            Node nodeOne = tab[position];
            while (nodeOne.getNext() != null) {
                if (nodeOne.getKey().equals(InputKey)) {
                    nodeOne.setValue(value);
                    return;
                } else {
                    nodeOne = nodeOne.getNext();
                }
            }
            nodeOne.setNext(new Node(InputKey, value));
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
        Node<K, V> nodeOne = tab[position];
        while (nodeOne.getNext() != null) {
            if (nodeOne.getKey().equals(key)) {
                return nodeOne.getValue();
            } else {
                nodeOne = nodeOne.getNext();
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
        System.out.println("All Keys:");
        int printNumbOfKey = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                System.out.print(tab[i].getKey() + "; ");
                printNumbOfKey++;
                Node<K, V> nodeOne = tab[i];
                while (nodeOne.getNext() != null) {
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
        System.out.println("All Values:");
        int printNumbOfValuses = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                Node<K, V> nodeOne = tab[i];
                System.out.print(nodeOne.getValue() + "; ");
                printNumbOfValuses++;
                while (nodeOne.getNext() != null) {
                    System.out.print(nodeOne.getValue() + "; ");
                    printNumbOfValuses++;
                    nodeOne = nodeOne.getNext();
                }
            }
        }
        System.out.println("\nNuymber of Values:" + printNumbOfValuses);
    }
}
