package toolbox;

import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ddewitte on 26.09.15.
 */
public class RandStrings {

    public static int UPPERBOUND = 15;
    public static Alphabet alph = new IUPACAlphabet(IUPACAlphabet.IUPACType.TWOFOLDSANDN);

    public static String generateRandomPath(){
        int k = randInt(UPPERBOUND);
        return generateRandomPath(k);
    }

    public static String generateRandomPath(int k){

        StringBuilder sb = new StringBuilder();

        IntStream.range(0,k)
                .mapToObj(i -> alph.generateRandomChar())
                .forEach(sb::append);

         return sb.toString();

    }

    public static int randInt(int width){
        return (int)Math.random()*width;
    }

    public static Set<String> generateRandomStringset(int size){

        return IntStream.range(0, size)
                .mapToObj(i -> generateRandomPath())
                .collect(Collectors.toSet());
    }


}
