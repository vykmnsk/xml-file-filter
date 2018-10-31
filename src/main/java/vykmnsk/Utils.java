package vykmnsk;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class Utils {
    public static boolean areAnagrams(String one, String another) {
        if (null == one ||
            null == another ||
            0 == one.length() ||
            0 == another.length() ){
            return false;
        }
        String[] letterArray1 = one.toUpperCase().split("");
        String[] letterArray2 = another.toUpperCase().split("");
        Set letterSet1 = Arrays.stream(letterArray1).collect(Collectors.toSet());
        Set letterSet2 = Arrays.stream(letterArray2).collect(Collectors.toSet());
        return letterSet1.equals(letterSet2);
    }
}
