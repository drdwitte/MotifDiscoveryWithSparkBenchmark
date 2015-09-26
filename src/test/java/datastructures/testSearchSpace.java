package datastructures;

import datastructures.search.DiskShapedSearchSpace;
import datastructures.search.SearchSpace;
import datastructures.search.SearchSpaceNavigator;
import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;

import org.junit.Assert;
import org.junit.Test;
import toolbox.RandStrings;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by ddewitte on 25.09.15.
 */
public class testSearchSpace {

    private Alphabet alph = new IUPACAlphabet(IUPACAlphabet.IUPACType.TWOFOLDSANDN)
    private SearchSpace s = new DiskShapedSearchSpace(6,12,1,alph);

    @Test
    public void testNavigator(){
        SearchSpaceNavigator sNav = s.getSSNavigator();

        Set<String> paths = RandStrings.generateRandomStringset(100);

        paths.forEach();




        sNav.hasChild();
        for (int i=0; i<randomPath.length(); i++){
            Assert.assertEquals(true,sNav.hasChild());

        }



    }

    @Test
    public void trackNavigator() {

    }

    @Test
    public void testObserverPattern() {
        Assert.assertEquals(false,true);
    }





}

/*
boolean hasSibling();
    boolean hasChild();
    boolean hasParent();

    char toSibling();
    char toFirstChild();
    void toParent();

    boolean inSearchSpace();
*/