package datastructures;

import datastructures.search.ContentShapedSearchSpace;
import factories.IUPACFactory;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.alphabets.CharacterIteratorFactory;
import models.alphabets.CharacterIteratorProvider;
import models.alphabets.IUPACAlphabet;
import models.motifs.IUPACPattern;
import models.motifs.Pattern;
import motifalgorithms.AlgorithmStubs;
import motifalgorithms.LazyExactDiscovery;
import motifalgorithms.LazyMotifDiscoveryAlgorithm;
import motifconservation.INScore;

import org.junit.Assert;
import org.junit.Test;

import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ddewitte on 19.10.15.
 */
public class TestContentShapedSearchSpace {

    /**
     * Find all k mers in content restricted search space
     */
    @Test
    public void testContentSearchSpace1(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new ContentShapedSearchSpace(alph, new IUPACPattern("ACGT"), provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();

        Iterator<Tuple2<Pattern, INScore>> it = alg.iterator();

        while (it.hasNext()){
            patterns1.add(it.next()._1());
        }

        //alg.(tup ->

        Assert.assertEquals(4 * 3 * 2, patterns1.size());
    }

    @Test
    public void testContentSearchSpace2(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new ContentShapedSearchSpace(alph, new IUPACPattern("AAAA"), provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();

        Iterator<Tuple2<Pattern, INScore>> it = alg.iterator();

        while (it.hasNext()){
            patterns1.add(it.next()._1());
        }

        //alg.(tup ->

        Assert.assertEquals(1, patterns1.size());
    }

    @Test
    public void testContentSearchSpace3(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new ContentShapedSearchSpace(alph, new IUPACPattern("CCAAC"), provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();

        Iterator<Tuple2<Pattern, INScore>> it = alg.iterator();

        while (it.hasNext()){
            patterns1.add(it.next()._1());
        }

        //alg.(tup ->

        Assert.assertEquals(5*4*3*2/3/2/2, patterns1.size());

        //patterns1.forEach(System.out::println);
    }

    /**
     * Does one permutation group fit in memory? (max 3 degenerate positions
     */
    @Test
    public void testContentSearchSpace4(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new ContentShapedSearchSpace(alph, new IUPACPattern("AAACCGGTTNRS"), provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();

        Iterator<Tuple2<Pattern, INScore>> it = alg.iterator();
        long t1 = System.currentTimeMillis();

        alg.forEach(x -> patterns1.add(x._1()));
        long t2 = System.currentTimeMillis();

        Assert.assertEquals(12 * 11 * 10 * 9 * 8 * 7 * 6 * 5 /2, patterns1.size());


        System.out.println((t2-t1)/1000);


        //patterns1.forEach(System.out::println);
    }
}
