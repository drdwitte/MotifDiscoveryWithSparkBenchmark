package motifalgorithms;

import datastructures.indexing.DSNavigator;
import datastructures.indexing.DiscoveryStructure;
import datastructures.search.SearchSpace;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import motifconservation.INInfo;
import motifconservation.INScore;
import motifconservation.INScoreCalculator;
import toolbox.Checks;

import java.util.function.Function;

/**
 * Created by ddewitte on 02.10.15.
 */
public class AlgorithmStubs {


    public static DiscoveryStructure discoveryStructureStub(){
        return new DiscoveryStructure() {
            @Override
            public DSNavigator getDSNavigator(Alphabet alphabet) {
                return new DSNavigator() {
                    @Override
                    public void jumpTo(char edge) {

                    }
                    @Override
                    public void backtrack() {

                    }

                    @Override
                    public boolean hasMatches() {
                        return true;
                    }

                    @Override
                    public INInfo getInfo() {
                        return null;
                    }
                };
            }
        };
    }

    public static INScoreCalculator inScoreCalculatorStub(){
        return new INScoreCalculator() {
            @Override
            public INScore calc(INInfo info) {
                return null;
            }
        };

    }

    public static Function<INScore,Boolean> survivesBranchAndBoundConditionStub(){

        return new Function<INScore, Boolean>() {
            @Override
            public Boolean apply(INScore inScore) {
                return true;
            }
        };

    }




}
