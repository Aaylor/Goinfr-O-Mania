package engine;

import com.sun.javaws.exceptions.InvalidArgumentException;
import helpers.ExtMath;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Tuple<X, Y> {
    X x;
    Y y;

    public Tuple (X x, Y y) {
        this.x = x;
        this.y = y;
    }
}

public class RandomCreation {

    private Map<String, Tuple<Integer, Integer>> map;
    private int total;

    public RandomCreation() {
        map   = new HashMap<>();
        total = 0;
    }

    public void add(String s, Integer prob) {
        Tuple<Integer, Integer> t = new Tuple<>(total, prob + total - 1);
        map.put(s, t);
        total += prob;
    }

    public String getRandomlyName() {
        int next    = ExtMath.getRandomBewteen(0, total);

        for (String s : map.keySet()) {
            Tuple<Integer, Integer> t = map.get(s);

            if (next >= t.x && next <= t.y) {
                return s;
            }

        }

        throw new UnknownError();
    }

}
