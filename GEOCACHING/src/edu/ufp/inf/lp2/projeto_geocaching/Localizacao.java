package edu.ufp.inf.lp2.projeto_geocaching;

/**
 * Classe Localizacao com os seus parametros
 * */

public class Localizacao {

  public String regiao;

  public double coordX;

  public double coordY;

  /**
   * funçao de retorno da Regiao
   * @param regiao- String Regiao
   */
  public Localizacao(String regiao){
    this.regiao = regiao;
  }
}