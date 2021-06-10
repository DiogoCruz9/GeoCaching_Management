/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Classe Ligacao que faz um extend de DirectedEdge com os seus argumentos
 */
public class Ligacao extends DirectedEdge {

    private int comum;

    /**
     * Funçao ligaçao faz ligaçao entre vertices
     * @param v -vertice -int
     * @param w- vertice- int
     * @param comum
     */
    public Ligacao(int v, int w, int comum) {

        super(v, w, comum);
    }

}
    

    
    
    

    
   
