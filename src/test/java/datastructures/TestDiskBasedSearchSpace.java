package datastructures;

import datastructures.search.AngleRestrictedSearchSpace;
import datastructures.search.DiskShapedSearchSpace;
import factories.IUPACFactory;
import factories.PatternFactory;
import models.alphabets.*;


import models.motifs.Pattern;
import motifalgorithms.AlgorithmStubs;
import motifalgorithms.LazyExactDiscovery;
import motifalgorithms.LazyMotifDiscoveryAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by ddewitte on 25.09.15.
 */
public class TestDiskBasedSearchSpace {

    private static Logger LOGGER = Logger.getLogger(TestDiskBasedSearchSpace.class.getName());

    /**
     * Find all 6 mers in diskbased search space => 4^6 = 4096
     */
    @Test
    public void testSearchSpaceContent1(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 6;
        int kmax = 6;
        int degmax =0;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                    .searchSpace(new DiskShapedSearchSpace(kmin,kmax,degmax,alph,provider))
                    .patternFactory(patternFactory)
                    .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                    .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                    .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                    .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(4096,patterns1.size());
    }

    /**
     * Find all 6 mers and 7 mers in diskbased search space => 4^6 + 4^7 = 5*4096
     */
    @Test
    public void testSearchSpaceContent2(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 6;
        int kmax = 7;
        int degmax =0;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new DiskShapedSearchSpace(kmin,kmax,degmax,alph,provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(5*4096,patterns1.size());
    }

    /**
     * Find all 6 mers in diskbased search space with 1 deg position of ACGTN alph:
     * 0 errors + 1 error = 4096 + C1_6 * 1 * 4^5 = 4*1024+6*1024=10*1024
     */
    @Test
    public void testSearchSpaceContent3(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.DONTCARES;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 6;
        int kmax = 6;
        int degmax =1;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new DiskShapedSearchSpace(kmin,kmax,degmax,alph,provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(10*1024,patterns1.size());
    }

    /**
     * Find all 6 mers in diskbased search space with 2 errors ACGTN
     * 0 + 1 + 2 errors
     * 4^6 + C1_6 4^5 + C2_6 4^4
     * (16 + 24 + 15) * 4^4
     */
    @Test
    public void testSearchSpaceContent4(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.DONTCARES;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 6;
        int kmax = 6;
        int degmax =2;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new DiskShapedSearchSpace(kmin,kmax,degmax,alph,provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(256*(16+24+15),patterns1.size());
    }

    /**
     * Find all 5 mers in diskbased search space with 1 error ACGTN+2folds alphabet
     */
    @Test
    public void testSearchSpaceContent5(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 5;
        int kmax = 5;
        int degmax =1;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new DiskShapedSearchSpace(kmin,kmax,degmax,alph,provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(1024+7*5*256,patterns1.size());
    }

    /**
     * Find all 3-5 mers in diskbased search space with no degeneracy restrictions
     */
    @Test
    public void testSearchSpaceContent6(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 3;
        int kmax = 5;
        int degmax =5;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new DiskShapedSearchSpace(kmin,kmax,degmax,alph,provider))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals((int)(Math.pow(11,3)+Math.pow(11,4)+Math.pow(11,5)),patterns1.size());
    }

    /**
     * Find all 3-5 mers in angle restricted search space with no degeneracy restrictions
     */
    @Test
    public void testSearchSpaceContent7(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 3;
        int kmax = 5;
        int degmax =5;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"AAAA"))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(13,patterns1.size());
    }

    /**
     * Find all 3-6 mers in angle restricted search space with degeneracy restrictions (already met in prefix)
     */
    @Test
    public void testSearchSpaceContent8(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 3;
        int kmax = 6;
        int degmax =2;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"ANNA"))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(1+1+4+4*4,patterns1.size());
    }



}
