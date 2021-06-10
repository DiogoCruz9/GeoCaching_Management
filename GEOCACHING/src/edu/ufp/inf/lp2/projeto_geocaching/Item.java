package edu.ufp.inf.lp2.projeto_geocaching;


import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 * Classe item com os seus respectivos parametros
 */
public class Item {

    public String nome;

    public Boolean isTravelBug;

    public Integer id_item;



    /**
     * Contrutor de item e seus atributos
     *
     * @param id_item- int
     * @param nome - String
     * @param isTravelBug- Boolean
     */
    public Item(Integer id_item, String nome, Boolean isTravelBug) {
        this.id_item = id_item;
        this.nome = nome;
        this.isTravelBug = isTravelBug;
    }

    /**
     * Construtor de Item
     */
    public Item() {
    }

    /**
     * Método para retorno do nome do item
     *
     * @return String - nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para indicar nome de item
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para  retorno se é travelbug ou nao
     *
     * @return Boolean - isTravelBug
     */

    public Boolean getTravelBug() {
        return isTravelBug;
    }

    /**
     * Método para indicar se e travelbug ou nao
     */

    public void setTravelBug(Boolean travelBug) {
        isTravelBug = travelBug;
    }

    /**
     * Método para  retorno do id do item
     *
     * @return int - id_item
     */

    public Integer getId_item() {
        return id_item;
    }

    /**
     * Método para indicar o id do item
     */

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }


    /**
     * Função que imprime as caracteristicas do item na interface gráfica
     *
     * @param p - user que se pretende imprimir na interface gráfica
     * @return - imprime o user através do método toString
     */
    public static String print_item_gui(Item p) {
        StringBuilder s = new StringBuilder();
        s.append("ID: " + p.getId_item() + "\n");
        s.append("Nome: " + p.getNome() + "\n");
        s.append("É TravelBug? " + p.getTravelBug() + "\n");
        return s.toString();
    }
}