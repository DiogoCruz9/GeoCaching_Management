package edu.ufp.inf.lp2.projeto_geocaching;


import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 classe TravelBug  que provem de item
 */
public class TravelBug extends Item {

  private Integer id_cache;
  private SeparateChainingHashST<Integer, Cache> tb_cacheST = new SeparateChainingHashST<>();

  /**
   * construtor
   * @param id_item
   * @param nome
   * @param isTravelBug
   */
  public TravelBug(Integer id_item, String nome, Boolean isTravelBug) {
    super(id_item,nome, isTravelBug);

  }

  /**
   * Construtor de Travel bug
   * @param id_item-int
   * @param nome- string
   * @param isTravelBug- Boolean
   * @param tb_cacheST- Chaining de Caches
   */
  public TravelBug(Integer id_item,String nome, Boolean isTravelBug, SeparateChainingHashST<Integer, Cache> tb_cacheST){
    this.id_item = id_item;
    this.nome = nome;
    this.isTravelBug = isTravelBug;
    this.tb_cacheST = tb_cacheST;
  }



  /**
   * Funcao de retorno Id-cache
   * @return-int-id_cache
   */
  public Integer getId_cache() {
    return id_cache;
  }

  /**
   * Método para definir o id do item
   * @param id_cache-int
   */

  public void setId_cache(Integer id_cache) {
    this.id_cache = id_cache;
  }

  /**
   * funçao de retorno da ST das caches associadas ao travel bug
   * @return ST- tb_cacheST
   */
  public SeparateChainingHashST<Integer, Cache> getTb_cacheST() {
    return tb_cacheST;
  }

  /**
   * Função que imprime a interface gráfica do travel bug
   *
   * @param p - travel bug que se pretende imprimir na interface gráfica
   * @return - imprime o travel bug através do método toString
   */
  public static String print_tb_gui(TravelBug p) {
    StringBuilder s = new StringBuilder();
    s.append("ID: " + p.getId_item() + "\n");
    s.append("Nome: " + p.getNome() + "\n");
    s.append("Cache: " + p.getTb_cacheST().get(0).id_cache + "\n");
    s.append("Localização: " + p.getTb_cacheST().get(0).regiao + "\n");
    return s.toString();
  }
}