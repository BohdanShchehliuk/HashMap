package HashMapMethods;

import java.util.Arrays;

public class HahsMapImplementation<K, V> implements HashMapInterface<K, V> {
    private final int INIZIALIZATION = 16;
    private final double RESIZE_LIMIT = 0.70;// 70 persantege
    private final double RESIZE_COEFFICIENT = 1.50;// 150 persantege
    public int numberOfCoinsedence =0;
    int counter = 0;
    int numberOfElements = 0;

    public Node<K, V>[] getTab() {
        return tab;
    }

    private Node<K, V>[] tab;

    public HahsMapImplementation() {
        tab = new Node[INIZIALIZATION];
    }

    public void resize() {
        if (counter < RESIZE_LIMIT * tab.length) {
            return;
        } else {
            Node<K, V>[] tabNew = new Node[(int) (tab.length * RESIZE_COEFFICIENT)];
            for (Node tmp : tab) {
                if (tmp != null) {
                    int position = Math.abs((tmp.getHASH() >> 2)) % tab.length;
                    tabNew[position] = tmp;
                }
            }
            tab = tabNew;
        }
    }

    @Override
    public void put(K key, V value) {
        resize();
        Node<K, V> node = new Node(key, value);
        int position = Math.abs((node.getHASH() >> 2)) % tab.length;
        if (tab[position] == null) {
            tab[position] = node;
            counter++;
            numberOfElements++;
        } else if (tab[position].getKey().equals(key)) {
            tab[position] = node;
        } else {
             Node nodeOne = tab[position];
             while (nodeOne.getNext() != null) {
                if (nodeOne.getKey().equals(key)) {
                    numberOfCoinsedence++;
                    nodeOne.setValue(value);
                    return;
                } else {
                    nodeOne = nodeOne.getNext();
                }
            }
             nodeOne.setNext(new Node(key, value));
             numberOfElements++;
        }
    }

    public void show() {
        System.out.println("ВСІ бакети:"+Arrays.toString(tab));
    }

    @Override
    public V get(K key) {
        int position = Math.abs((key.hashCode() >> 2)) % tab.length;
        if (tab[position].getKey().equals(key)) {
            return tab[position].getValue();
        } else {
            Node nodeOne = tab[position];
            nodeOne = nodeOne.getNext();
            while (nodeOne != null) {
                if (nodeOne.getNext().getKey().equals(key)) {
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
        return numberOfElements;
    }

    @Override
    public void getKeySet() {
        int printNumbOfKey = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                System.out.print(tab[i].getKey()+"; ");
                printNumbOfKey++;
                Node nodeOne = tab[i];
                nodeOne = nodeOne.getNext();
                while (nodeOne != null) {
                    System.out.print(nodeOne.getKey()+"; ");
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
                System.out.print(nodeOne.getValue()+"; ");
                printNumbOfValuses++;
                nodeOne = nodeOne.getNext();
                while (nodeOne!= null) {
                    System.out.print(nodeOne.getValue()+"; ");
                    printNumbOfValuses++;
                    nodeOne = nodeOne.getNext();
                }
            }
        }
        System.out.println("\nNuymber of Values:" + printNumbOfValuses);
    }
}

