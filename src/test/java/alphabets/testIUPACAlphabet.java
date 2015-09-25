package alphabets;

import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ddewitte on 25.09.15.
 */
public class testIUPACAlphabet {

    private Alphabet seqAlph = new IUPACAlphabet(IUPACAlphabet.IUPACType.BASEPAIRS);
    private Alphabet mAlph1 = new IUPACAlphabet(IUPACAlphabet.IUPACType.DONTCARES);
    private Alphabet mAlph2 = new IUPACAlphabet(IUPACAlphabet.IUPACType.TWOFOLDSANDN);
    private Alphabet mAlph3 = new IUPACAlphabet(IUPACAlphabet.IUPACType.FULL);

    @Test
    public void testStringRepresentations(){

        Assert.assertEquals("ACGT",seqAlph.getAllChars());
        Assert.assertEquals("ACGTN",mAlph1.getAllChars());
        Assert.assertEquals("ACGTNMRWSYK",mAlph2.getAllChars());
        Assert.assertEquals("ACGTNMRWSYKBDHV",mAlph3.getAllChars());

    }

    /*
    A 	Adenine
    C 	Cytosine
    G 	Guanine
    T (or U) 	Thymine (or Uracil)
    R 	A or G
    Y 	C or T
    S 	G or C
    W 	A or T
    K 	G or T
    M 	A or C
    B 	C or G or T
    D 	A or G or T
    H 	A or C or T
    V 	A or C or G
    N 	any base
    . or - 	gap
     */
    @Test
    public void testDegeneracy(){

        Assert.assertEquals(1,seqAlph.getMaxDegPerChar());
        Assert.assertEquals(4,mAlph1.getMaxDegPerChar());
        Assert.assertEquals(4,mAlph2.getMaxDegPerChar());
        Assert.assertEquals(4,mAlph3.getMaxDegPerChar());

        Assert.assertEquals(1,seqAlph.getNumberOfMatchingCharacters('A'));
        Assert.assertEquals(1,seqAlph.getNumberOfMatchingCharacters('C'));
        Assert.assertEquals(1,seqAlph.getNumberOfMatchingCharacters('G'));
        Assert.assertEquals(1,seqAlph.getNumberOfMatchingCharacters('T'));

        Assert.assertEquals(4,mAlph1.getNumberOfMatchingCharacters('N'));

        Assert.assertEquals(2,mAlph2.getNumberOfMatchingCharacters('R'));
        Assert.assertEquals(2,mAlph2.getNumberOfMatchingCharacters('S'));
        Assert.assertEquals(2,mAlph2.getNumberOfMatchingCharacters('Y'));
        Assert.assertEquals(2,mAlph2.getNumberOfMatchingCharacters('W'));
        Assert.assertEquals(2,mAlph2.getNumberOfMatchingCharacters('K'));
        Assert.assertEquals(2,mAlph2.getNumberOfMatchingCharacters('M'));

        Assert.assertEquals("A",""+mAlph2.getComplement('T'));
        Assert.assertEquals("C",""+mAlph2.getComplement('G'));
        Assert.assertEquals("G",""+mAlph2.getComplement('C'));
        Assert.assertEquals("T",""+mAlph2.getComplement('A'));

        Assert.assertEquals("R",""+mAlph2.getComplement('Y'));
        Assert.assertEquals("Y",""+mAlph2.getComplement('R'));
        Assert.assertEquals("S",""+mAlph2.getComplement('S'));
        Assert.assertEquals("W",""+mAlph2.getComplement('W'));
        Assert.assertEquals("K",""+mAlph2.getComplement('M'));
        Assert.assertEquals("M",""+mAlph2.getComplement('K'));

        Assert.assertEquals("N",""+mAlph2.getComplement('N'));

        Assert.assertEquals(false,mAlph2.isDegenerate('A'));
        Assert.assertEquals(false,mAlph2.isDegenerate('C'));
        Assert.assertEquals(false,mAlph2.isDegenerate('G'));
        Assert.assertEquals(false,mAlph2.isDegenerate('T'));

        Assert.assertEquals(true,mAlph2.isDegenerate('R'));
        Assert.assertEquals(true,mAlph2.isDegenerate('Y'));
        Assert.assertEquals(true,mAlph2.isDegenerate('S'));
        Assert.assertEquals(true,mAlph2.isDegenerate('W'));
        Assert.assertEquals(true,mAlph2.isDegenerate('K'));
        Assert.assertEquals(true,mAlph2.isDegenerate('M'));
        Assert.assertEquals(true,mAlph2.isDegenerate('N'));
    }


}
