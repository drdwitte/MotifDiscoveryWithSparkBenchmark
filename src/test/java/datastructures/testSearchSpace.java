package datastructures;

import datastructures.search.DiskShapedSearchSpace;
import datastructures.search.SearchSpace;
import datastructures.search.SearchSpaceNavigator;
import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;


import org.junit.Assert;
import org.junit.Test;
import toolbox.RandStrings;

import java.util.Set;

/**
 * Created by ddewitte on 25.09.15.
 */
public class testSearchSpace {

    private Alphabet alph = new IUPACAlphabet(IUPACAlphabet.IUPACType.TWOFOLDSANDN);
    private SearchSpace s = new DiskShapedSearchSpace(3,6,1,alph);

    @Test
    public void testNavigator(){

        RandStrings.UPPERBOUND = 7;
        Set<String> randomPaths = RandStrings.generateRandomStringset(100);

        for (String p : randomPaths){

            SearchSpaceNavigator sNav = s.getSSNavigator();
            for (int i=0; i<p.length(); i++){
                boolean found = true;
                if (sNav.hasChild()) {
                    char c = sNav.toFirstChild();
                    while (c != p.charAt(i)) {
                        if (sNav.hasSibling()) {
                            c = sNav.toSibling();
                        } else {
                            found = false;
                        }
                    }
                } else found = false;

                if (!found){
                    //Assert.assertEquals(, sNav.inSearchSpace());
                } else {

                    //Assert.assertEquals(, sNav.inSearchSpace());
                }





            }
        }

        /*Stream< Tuple2 <String,SearchSpaceNavigator > > patternNavigatorStream =
            RandStrings
                    .generateRandomStringset(100)
                    .stream()
                    .map(p -> {
                        SearchSpaceNavigator sNav = s.getSSNavigator();
                        for (int i = 0; i < p.length(); i++) {
                            if (sNav.hasChild()) {
                                char c = sNav.toFirstChild();
                                while (c != p.charAt(i)) {
                                    if (sNav.hasSibling()) {
                                        c = sNav.toSibling();
                                    } else {
                                        return new Tuple2<String, SearchSpaceNavigator>(p, null);
                                    }
                                }
                            } else return new Tuple2<String, SearchSpaceNavigator>(p, null);


                        }
                        return new Tuple2<String, SearchSpaceNavigator>(p, sNav);
                    });




        patternNavigatorStream
                .map(pair -> pair._2() != null)
                .forEach(p -> Assert.assertEquals(p,true));
        */





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