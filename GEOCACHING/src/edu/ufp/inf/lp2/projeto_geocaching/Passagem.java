package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 * Classe Passagem com os seus respectivos parametros
 */
public class Passagem {

    private int id;
    private int idcache;
    private float coordx;
    private float coordy;

    private SeparateChainingHashST<Integer, Cache> aux_cacheST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Cache> cache_passagem_st = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Passagem> getPassagemCacheST = new SeparateChainingHashST<>();



    public Passagem(int id, int idcache, float coordx, float coordy) {
        this.id = id;
        this.idcache = idcache;
        this.coordx = coordx;
        this.coordy = coordy;
    }

    /**
     * Construtor da Classe passagem
     *
     * @param id- int
     * @param aux_cacheST- ST de caches provenientes de outra classe
     * @param coordx- int
     * @param coordy- int
     */
    public Passagem(int id, SeparateChainingHashST<Integer, Cache> aux_cacheST, float coordx, float coordy) {
        this.id = id;
        this.aux_cacheST = aux_cacheST;
        this.coordx = coordx;
        this.coordy = coordy;
    }

    public SeparateChainingHashST<Integer, Cache> getPassagem_cacheST() {
        return cache_passagem_st;
    }
    public void setPassagemCacheST(SeparateChainingHashST<Integer, Passagem> getPassagemCacheST) {
        this.getPassagemCacheST = getPassagemCacheST;
    }


    /**
     * funçao para retorno do id de passagem
     *
     * @return int- id
     */
    public int getId() {
        return id;
    }

    /**
     * funçao para definir o id de passagem
     *
     * @param id -int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * funçao para retorno do idcache de passagem
     *
     * @return int- idcache
     */
    public int getIdcache() {
        return idcache;
    }

    /**
     * funçao para definir o idcache
     *
     * @param idcache- int
     */
    public void setIdcache(int idcache) {
        this.idcache = idcache;
    }

    /**
     * funçao de retorno de coordx
     *
     * @return int-coordx
     */
    public float getCoordx() {
        return coordx;
    }

    /**
     * funçao para definir coordx
     *
     * @param coordx - int
     */
    public void setCoordx(float coordx) {
        this.coordx = coordx;
    }

    /**
     * funçao de retorno Coordy
     *
     * @return- int-Coordy
     */
    public float getCoordy() {
        return coordy;
    }

    /**
     * funçao para definir Coordy
     *
     * @param coordy - int
     */
    public void setCoordy(float coordy) {
        this.coordy = coordy;
    }

    /**
     * Função para carregar os pontos de passagem do ficheiro
     *
     * @param passagemST - ST que contem todos os pontos de passagem
     * @param path       - caminho do ficheiro de onde se pretende ler os pontos de passagem
     */
    public static void loadPassagem(BST_Aed2<Integer, Passagem> passagemST, BST_Aed2<Integer, Cache> cachest, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {
                SeparateChainingHashST<Integer, Cache> aux_cacheST = new SeparateChainingHashST<>();

                SeparateChainingHashST<Integer, Passagem> aux_passagem_cacheST = new SeparateChainingHashST<>();

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                String aux3 = token[2];
                String aux4 = token[3];
                int j = Integer.parseInt(aux2);
                for (int i = 0; i < 1; i++) {
                    Cache aux_user = cachest.get(Integer.parseInt(aux2));
                    aux_cacheST.put(i, aux_user);
                }
                Passagem aux_passagem = new Passagem(Integer.parseInt(aux1), aux_cacheST.get(0).id_cache, Float.parseFloat(aux3), Float.parseFloat(aux4));
                passagemST.put(Integer.parseInt(aux1), aux_passagem);
                Cache aux_user = cachest.get(Integer.parseInt(aux2));
                aux_passagem_cacheST = aux_user.getPassagemCacheST();
                aux_passagem_cacheST.put(aux_passagem_cacheST.size(), aux_passagem);

            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Função para imprimir todas as passagens da ST
     *
     * @param passagemST - ST que contem todos os pontos de passagem
     */
    public static void printPassagens(BST_Aed2<Integer, Passagem> passagemST) {
        System.out.println("##################Listagem dos Pontos de Passagem##################" + "\n");
        for (Integer key : passagemST.emordem()) {
            print_passagem(passagemST, key);
        }
        System.out.println("\n");
    }

    /**
     * Função para imprimir todas as informações de uma passagem dada uma key
     *
     * @param passagemST - ST que contem todos os pontos de passagem
     * @param key        - chave da passagem que se pretende imprimir
     */
    public static void print_passagem(BST_Aed2<Integer, Passagem> passagemST, Integer key) {
        System.out.println("ID: " + passagemST.get(key).getId() + " -" +
                " Nome: " + passagemST.get(key).getId() + " -" +
                " Coord X: " + passagemST.get(key).getCoordx() + " -" +
                " Coord Y: " + passagemST.get(key).getCoordy());
    }


    @Override
    public String toString() {
        return "Passagem{" +
                "id=" + id +
                ", nome='" + idcache + '\'' +
                ", coordx=" + coordx +
                ", coordy=" + coordy +
                '}';
    }
}
