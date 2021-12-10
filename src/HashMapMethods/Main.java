package HashMapMethods;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HahsMapImplementation <String, String> one = new HahsMapImplementation<>();
        one.put("Богдан", "Щеглюк");
        one.put("Олексій", "Хомишин");
        one.show();
        System.out.println(one.get("Богдан"));
        System.out.println(one.size());
         }
}
