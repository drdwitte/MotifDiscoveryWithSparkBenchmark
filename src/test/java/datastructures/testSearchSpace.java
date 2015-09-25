package datastructures;

import models.alphabets.IUPACAlphabet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ddewitte on 25.09.15.
 */
public class testSearchSpace {

    private SearchSpace s = new DiskShapedSearchSpace(6,12,1, new IUPACAlphabet(IUPACAlphabet.IUPACType.TWOFOLDSANDN));

    @Test
    public void testNavigator(){
        SearchSpaceNavigator sNav = s.getSSNavigator();

        String randomPath = "ACCGNNT";

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
