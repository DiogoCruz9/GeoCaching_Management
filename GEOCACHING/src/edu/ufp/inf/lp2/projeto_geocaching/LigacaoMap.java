package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Funçao LigacaoMap que faz extend de DirectedEdge
 */
public class LigacaoMap extends DirectedEdge {

    public LigacaoMap(int l1, int l2, double distance) {

        super(l1, l2, distance);

    }

    /**
     * funçao de retorno Distance
     * @return int- devolve weight proveniente do construtor da classe pai "DirectedEdge"
     */
    public double getDistance() {
        return super.weight();
    }
}
