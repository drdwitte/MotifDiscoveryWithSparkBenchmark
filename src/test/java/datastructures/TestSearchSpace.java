package datastructures;

import datastructures.search.DiskShapedSearchSpace;
import datastructures.search.SearchSpace;
import datastructures.search.SearchSpaceNavigator;
import factories.IUPACFactory;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;


import models.motifs.IUPACPattern;
import org.junit.Assert;
import org.junit.Test;
import toolbox.RandStrings;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ddewitte on 25.09.15.
 */
public class TestSearchSpace {

    private static Logger LOGGER = Logger.getLogger(TestSearchSpace.class.getName());

    private static final IUPACAlphabet.IUPACType type = IUPACAlphabet.IUPACType.TWOFOLDSANDN;
    private Alphabet alph = new IUPACAlphabet(type);
    private PatternFactory patternFactory = new IUPACFactory(type);

    private int kmin = 3;
    private int kmax = 6;
    private int degmax =1;
    private SearchSpace s = new DiskShapedSearchSpace(kmin,kmax,degmax,alph);

    static {
        RandStrings.UPPERBOUND = 8;
    }

    @Test
    public void testDiskShapedNavigator(){

        //NOTE: console handler is on INFO -> lower levels have no effect
        LOGGER.setLevel(Level.SEVERE);


        Set<String> randomPaths = RandStrings.generateRandomStringset(1000);
        randomPaths.stream()
                .map(s -> new IUPACPattern(s))
                .forEach(
                        (pattern) -> {
                            LOGGER.warning("p: "+pattern.toString());
                            SearchSpaceNavigator sNav = s.getSSNavigator(patternFactory);

                            boolean found = true;
                            charloop:for (int i = 0; i < pattern.length(); i++) {

                                if (sNav.hasChild()) {
                                    sNav.toFirstChild();
                                    char c = sNav.trail().last();
                                    while (c != pattern.charAt(i)) {
                                        if (sNav.hasSibling()) {
                                            sNav.toSibling();
                                            c = sNav.trail().last();
                                        } else {
                                            found = false;
                                            break charloop;
                                        }
                                    }
                                } else {
                                    found = false;
                                    break charloop;
                                }
                            }

                            LOGGER.info("found: " + found);

                            if (!found) { //means: motif is not in alphabet or exceeds outer radius
                                    //motif >kmax
                                LOGGER.info("motiflength: " + pattern.length());
                                LOGGER.info("largerThanOuterRadius: " + sNav.largerThanOuterRadius());

                                Assert.assertEquals(pattern.length() > kmax, !found);

                            } else {

                                LOGGER.info("smallerThanInnerRadius: " + sNav.smallerThanInnerRadius());
                                LOGGER.info("numberOfDegPositions: " + pattern.numberOfDegPositions());
                                LOGGER.info("motiflength: " + pattern.length());
                                LOGGER.info("largerThanOuterRadius: " + sNav.largerThanOuterRadius());

                                //<kmin
                                Assert.assertEquals(pattern.length() < kmin, sNav.smallerThanInnerRadius());

                                Assert.assertEquals(pattern.numberOfDegPositions() > degmax, sNav.largerThanOuterRadius());

                             }

                        }
                );
    }



    @Test
    public void testDiskShapedNavigatorBinding() {

        //TODO implement SmallTrie first

    }
}
