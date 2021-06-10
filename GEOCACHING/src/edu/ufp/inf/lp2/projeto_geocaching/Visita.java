package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.SeparateChainingHashST;
/**
 * Class visita com os seus respectivos argumentos
 * */
public class Visita {

    public int epoch;
    public int id_cache;
    public String user;
    public SeparateChainingHashST<Integer, Visita> visitasST = new SeparateChainingHashST<>();

    /**
     * Construtor da Class Visita com os seus parametros
     * @param epoch-int
     * @param id_cache-int
     * @param user-int
     */
    public Visita(int epoch, int id_cache,String user) {
        this.epoch = epoch;
        this.id_cache = id_cache;
        this.user = user;
    }
}
