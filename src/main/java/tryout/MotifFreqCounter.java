package tryout;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by ddewitte on 21.09.15.
 */
public class MotifFreqCounter {

    public static void main(String [] args){

        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("MotifFreqCounter");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> dnaSequences = new ArrayList<>();
        dnaSequences.add("ACGT");
        dnaSequences.add("TTCT");
        dnaSequences.add("CGCG");
        dnaSequences.add("AAAA");

        JavaRDD<String> geneSeqs = sc.parallelize(dnaSequences);

        JavaPairRDD<String,Integer> substrings = geneSeqs.flatMapToPair(fam -> new MotifResults(fam));


        Map<String,Integer> substrCounts =
                substrings
                    .reduceByKey((f1, f2) -> f1 + f2)
                    .collectAsMap()
                    ;


        substrCounts.entrySet().stream()
                .map(e -> e.getKey()+","+e.getValue())
                .sorted()
                .forEach(System.out::println);
    }

    static class MotifResults implements Iterable<Tuple2<String, Integer>> {

        protected String seq;
        protected int k=3;

        public MotifResults(String seq) {

            this.seq = seq;
        }

        @Override
        public Iterator<Tuple2<String, Integer>> iterator() {
            return new Iterator<Tuple2<String, Integer>>(){

                int pos = 0;

                @Override
                public boolean hasNext() {
                    return (pos+k)<= seq.length();
                }

                @Override
                public Tuple2<String, Integer> next() {
                    Tuple2<String, Integer>  t  = new Tuple2<>(seq.substring(pos,pos+k),1);
                    pos++;
                    return t;
                }
            };
        }
    }


}
