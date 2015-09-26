package motifalgorithms;

import models.motifs.Pattern;
import motifconservation.INScore;
import scala.Tuple2;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface LazyMotifDiscoveryAlgorithm extends Iterable<Tuple2<Pattern, INScore>> {

}
