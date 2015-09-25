package tryout;

//NOTE change project settings -> language level -> lambda expressions otherwise errors
// will pop up about no lambda expression support

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ddewitte on 18.09.15.
 */
public class Java8Playground {

    public static void main(String [] args){

        lambdaExpressions();
        methodReferences();
        functionalInterfaces();
        streamAPI();
        dateTimeAPI();
        optional();

    }

    public static void lambdaExpressions(){

        //function objects
        MathOperation addition = (int a, int b) -> a+b;
        MathOperation subtraction = (a,b) -> a-b; //automatic type inferencing
        MathOperation multiplication = (a,b) -> { return a*b;};  //optional return statement

        System.out.println(operate(10,5,addition));

        GreetingService pr = message -> System.out.println("Hello: "+message);

    }

    public static void methodReferences(){

        //method referencing with ::
        List<String> names = new ArrayList<>();

        names.add("Mahesh");
        names.add("Suresh");
        names.add("Ramesh");
        names.add("Naresh");
        names.add("Kalpesh");

        names.forEach(System.out::println);

        //NOTE
        //constructor referencing with ::new

    }

    public static void functionalInterfaces() {

    /*
            Functional interfaces have a single functionality to exhibit.
            For example, a Comparable interface with a single method ‘compareTo’ is used for comparison purpose.
            Java 8 has defined a lot of functional interfaces to be used extensively in lambda expressions.
           http://www.tutorialspoint.com/java8/java8_functional_interfaces.htm
        */
        //example interface: Predicate<T>: one argument -> boolean
        Predicate<Integer> even = n -> n%2 ==0;

        even.test(3);

        IntBinaryOperator doubleDiv = (a,b) -> { double temp = a*1.0 / b; return (int)Math.round(temp); };

        System.out.println("Doublediv: " + doubleDiv.applyAsInt(3,4));

    }

    public static void streamAPI(){

        //generating streams
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Stream<String> stream = strings.stream();
        //stream.forEach(s -> System.out.println(s));
        //stream.forEach(System.out::println);
        List<Integer> mappedstrings = stream.map(t -> t.length()).collect(Collectors.toList());
        mappedstrings.stream().forEach(System.out::println);




        //naast map ook filter, sorted


    }

    public static void dateTimeAPI(){

        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime.getDayOfMonth());



    }

    public static void optional(){

        Integer i = null;
        Optional<Integer> inb = Optional.ofNullable(i);

    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private static int operate(int a, int b, MathOperation m){
        return m.operation(a,b);
    }

}
