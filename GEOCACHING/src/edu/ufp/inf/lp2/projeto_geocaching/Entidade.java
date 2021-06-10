package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.In;

/**
 * Classe Entidade com os seus respectivos parametros
 */
public class Entidade {

    private int id;
    private int tipo;
    private int id_entidade;
    private float coordx;
    private float coordy;
    private String name;

    /**
     * Construtor da Entidade
     * @param id- int
     * @param tipo - int
     * @param id_entidade- int
     * @param coordx- int
     * @param coordy- int
     * @param name- String
     */
    public Entidade(int id, int tipo, int id_entidade, float coordx, float coordy, String name) {
        this.id = id;
        this.tipo = tipo;
        this.id_entidade = id_entidade;
        this.coordx = coordx;
        this.coordy = coordy;
        this.name = name;
    }

    /**
     * funçao de retorno de id de entidade
     * @return int-id
     */
    public int getId() {
        return id;
    }

    /**
     * funçao para definir id de entidade
     * @param id -int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * funcao para retorno de  id de tipo entidade
     * @return int-id_entidade
     */
    public int getId_entidade() {
        return id_entidade;
    }

    /**
     * funcao para definir id_entidade
     * @param id_entidade- int
     */
    public void setId_entidade(int id_entidade) {
        this.id_entidade = id_entidade;
    }

    /**
     * retorno do tipo de entidade
     * @return int- Tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * funcao para definir tipo de entidade
     * @param tipo - int
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * funcao de retorno Coordx
     * @return int-coordx
     */
    public float getCoordx() {
        return coordx;
    }

    /**
     * funcao para definir coordx
     * @param coordx - int
     */
    public void setCoordx(float coordx) {
        this.coordx = coordx;
    }

    /**
     * funçao de retorno de Coordy
     * @return int-coordy
     */
    public float getCoordy() {
        return coordy;
    }

    /**
     * funcao para definir coordy
     * @param coordy- int
     */
    public void setCoordy(float coordy) {
        this.coordy = coordy;
    }

    /**
     * funçao para retorno do nome da entidade
     * @return string- Name
     */
    public String getName() {
        return name;
    }

    /**
     * ´funçao para definir nome de entidade
     * @param name- string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Função para carregar as entidades do ficheiro
     *
     * @param entidadeST - ST que contem todas as entidades
     * @param path1 - caminho do ficheiro de onde se pretende ler as entidades
     */
    public static void loadEntidade(BST_Aed2<Integer, Entidade> entidadeST, String path1) {
        int id = 0;

        In in1 = new In(path1);

        try {
            while (!in1.isEmpty()) {

                String[] token = in1.readLine().split(";");
                String aux = token[token.length-2];
                String aux2 = token[token.length-1];
                String aux3 = token[0];
                String aux4 = token[1];
                Entidade aux_entidade = new Entidade(id,1, Integer.parseInt(aux3), Float.parseFloat(aux), Float.parseFloat(aux2), aux4);
                entidadeST.put(id,aux_entidade);
                id++;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Função para imprimir todas as entidades da ST
     *
     * @param entidadeST - ST que contem todas as entidades
     */
    public static void printEntidades(BST_Aed2<Integer, Entidade> entidadeST) {
        System.out.println("################## Listagem das Entidade##################" + "\n");
        for (Integer key : entidadeST.emordem()) {
            print_entidade(entidadeST, key);
        }
        System.out.println("\n");
    }

    /**
     * Função para imprimir todas as informações de uma entidade dada uma key
     *
     * @param entidadeST - ST que contem todas as entidades
     * @param key - chave da entidade que se pretende imprimir
     */
    public static void print_entidade(BST_Aed2<Integer, Entidade> entidadeST, Integer key) {
        System.out.println("ID: " + entidadeST.get(key).getId() + " - " +
                "Tipo: " + entidadeST.get(key).getTipo() + " - " +
                "ID Entidade: " + entidadeST.get(key).getId_entidade() + " - " +
                "CoordX: " + entidadeST.get(key).getCoordx() + " - " +
                "CoordY: " + entidadeST.get(key).getCoordy());
    }

    /**
     * Função usada para conseguir o nome de uma entidade através das passagens
     *
     * @param ent - entidade da qual se pretende o nome
     * @param passagemST - ST que contem todas as passagens
     * @return se a entidade for encontrada do tipo 1 é retornado o nome da mesma
     *         senão é retornado 0
     */
    public int getNomeEntidade(Entidade ent, BST_Aed2<Integer, Passagem> passagemST) {
        return passagemST.get(ent.id).getId();
    }
}
