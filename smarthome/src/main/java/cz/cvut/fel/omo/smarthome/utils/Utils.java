package cz.cvut.fel.omo.smarthome.utils;

import java.util.Random;

public class Utils {
    private static Random rnd = new Random();

    public static int randomInt(int max){
        return rnd.nextInt(max);
    }

    public static boolean randomBoolean(){
        return rnd.nextBoolean();
    }

}
