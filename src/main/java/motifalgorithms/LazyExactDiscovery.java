package motifalgorithms;

import datastructures.DSNavigator;
import datastructures.DiscoveryStructure;
import datastructures.SearchSpace;
import datastructures.SearchSpaceNavigator;
import models.motifs.Pattern;
import motifconservation.INScore;
import motifconservation.INScoreCalculator;
import scala.Tuple2;
import toolbox.Checks;
import toolbox.NotImplementedException;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by ddewitte on 23.09.15.
 */
public class LazyExactDiscovery implements LazyMotifDiscoveryAlgorithm {

    private SearchSpace searchSpace;
    private DiscoveryStructure discoveryStructure;
    private INScoreCalculator inScoreCalculator;
    private Function<INScore,Boolean> survivesBranchAndBoundCondition;

    /**
     * Build for discovery algorithm
     */
    public static class Builder {

        private DiscoveryStructure discoveryStructure;
        private SearchSpace searchSpace;
        private INScoreCalculator inScoreCalculator;
        private Function<INScore,Boolean> survivesBranchAndBoundCondition;

        public Builder discoveryStructure(DiscoveryStructure d){
            this.discoveryStructure=d;
            return this;
        }
        public Builder searchSpace(SearchSpace s){
            this.searchSpace=s;
            return this;
        }
        public Builder scoreCalculator(INScoreCalculator s){
            this.inScoreCalculator=s;
            return this;
        }
        public Builder bnbcondition(Function<INScore,Boolean> bnb){
            this.survivesBranchAndBoundCondition=bnb;
            return this;
        }

        public LazyExactDiscovery build(){

            LazyExactDiscovery lazy = new LazyExactDiscovery();
            lazy.searchSpace = this.searchSpace;
            lazy.discoveryStructure = this.discoveryStructure;
            lazy.inScoreCalculator = this.inScoreCalculator;
            lazy.survivesBranchAndBoundCondition = this.survivesBranchAndBoundCondition;

            //are  there combinations that do not make sense => exception?
            if (Checks.survivesNullCheck(this.searchSpace)
                    && Checks.survivesNullCheck(this.discoveryStructure)
                    && Checks.survivesNullCheck(this.inScoreCalculator)
                    && Checks.survivesNullCheck(this.survivesBranchAndBoundCondition)

                    ){}
            else
                throw new NullPointerException("Not all components initialized");

            return lazy;
        }
    }

    /**
     * Constructor
     */
    private LazyExactDiscovery(){}

    @Override
    public Iterator<Tuple2<Pattern, INScore>> iterator() {
        return new MotifResultsIterator();
    }

    private class MotifResultsIterator implements Iterator<Tuple2<Pattern, INScore>> {

        private boolean hasNext = true;
        private Tuple2<Pattern,INScore> currentResult = null;
        private DSNavigator dsNav = discoveryStructure.getDSNavigator();
        private SearchSpaceNavigator ssNav = searchSpace.getSSNavigator();

        public MotifResultsIterator(){
            initializeIteratorToFirstNode();

            //TODO verify that always at same position in search space
            ssNav.attachDSNavigator(dsNav);

        }

        private void initializeIteratorToFirstNode() {
            throw new NotImplementedException();
            //in case of prefix move navigators
        }

        public MotifResultsIterator(String prefix){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Tuple2<Pattern, INScore> next() {

            Tuple2<Pattern,INScore> result = currentResult;

            boolean found = false;
            while (hasNext && !found) {

                if (ssNav.hasChild())
                    ssNav.toFirstChild();

                else if (ssNav.hasSibling())
                    ssNav.toSibling();

                 else {

                    while (!ssNav.hasSibling() && ssNav.hasParent())
                        ssNav.toParent();

                    if (ssNav.hasParent())
                        ssNav.toSibling();
                    else
                        hasNext = false;

                }

                Pattern pattern = dsNav.trail().clonePattern();
                INScore inScore;
                if (dsNav.hasMatches()) {
                    inScore = inScoreCalculator.calc(dsNav.getInfo());

                    if (ssNav.inSearchSpace()
                            && survivesBranchAndBoundCondition.apply(inScore)) {

                        hasNext = true;
                        currentResult = new Tuple2<>(pattern, inScore);
                        found = true;

                    } else {}

                } else {}



            }
            return result;

        }
    }


}