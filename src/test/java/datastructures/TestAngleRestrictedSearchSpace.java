package datastructures;

import datastructures.search.AngleRestrictedSearchSpace;
import factories.IUPACFactory;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.alphabets.CharacterIteratorFactory;
import models.alphabets.CharacterIteratorProvider;
import models.alphabets.IUPACAlphabet;
import models.motifs.Pattern;
import motifalgorithms.AlgorithmStubs;
import motifalgorithms.LazyExactDiscovery;
import motifalgorithms.LazyMotifDiscoveryAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddewitte on 19.10.15.
 */
public class TestAngleRestrictedSearchSpace {


    @Test
    public void testInvalidAngleRestrictedSearchSpace(){

        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        int kmin = 3;
        int kmax = 6;
        int degmax =2;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        AngleRestrictedSearchSpace ang = new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"NNNN");
        Assert.assertEquals(2, ang.getMaxLength());


        ang = new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"ACCG");
        Assert.assertEquals(6,ang.getMaxLength());

        ang = new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"ANNAN");
        Assert.assertEquals(4,ang.getMaxLength());

        ang = new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"ANQ");
        Assert.assertEquals(2,ang.getMaxLength());

    }

    /**
     * Find all 3-6 mers in angle restricted search space with degeneracy restrictions (already met in prefix before
     * exiting prefix)
     */
    @Test
    public void testSearchSpaceContent9(){
        IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
        Alphabet alph = new IUPACAlphabet(type);
        PatternFactory patternFactory = new IUPACFactory(type);
        int kmin = 3;
        int kmax = 6;
        int degmax =2;
        CharacterIteratorProvider provider = new CharacterIteratorFactory(alph);

        LazyMotifDiscoveryAlgorithm alg =
                new LazyExactDiscovery.Builder()
                        .searchSpace(new AngleRestrictedSearchSpace(kmin,kmax,degmax,alph,provider,"ANNN"))
                        .patternFactory(patternFactory)
                        .discoveryStructure(AlgorithmStubs.discoveryStructureStub())
                        .bnbcondition(AlgorithmStubs.survivesBranchAndBoundConditionStub())
                        .scoreCalculator(AlgorithmStubs.inScoreCalculatorStub())
                        .build();

        List<Pattern> patterns1 = new ArrayList<>();
        alg.forEach(tup -> patterns1.add(tup._1()));

        Assert.assertEquals(1,patterns1.size());
    }
}
