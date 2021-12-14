package HashMapMethods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        HahsMapImplementation<Integer, String> two = new HahsMapImplementation<>();
        for (int i = 0; i<100; i++) {
            two.put((int)(Math.random()*10000), String.valueOf((int)(Math.random()*10000)));
        }
        System.out.println("Fiiled Buckets: "+two.counter);
        System.out.println("Numbers of elements: "+ two.size());
        two.show();
        two.getValues();
        two.getKeySet();
    }
}
