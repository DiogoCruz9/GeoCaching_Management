package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

/**
 * Classe Rede com os seus respectivos parametros onde faz um extend de EdgeWeightedDigraph
 */
public class Rede extends EdgeWeightedDigraph {

    private static final double _eQuatorialEarthRadius = 6378.1370D;
    private static final double _d2r = (Math.PI / 180D);

    public Rede(int e) {
        super(e);
    }

    /**
     * Função que calcula o caminho mais curto de um vértice a outro
     *
     * @param g - grafo
     * @param from - vértice de partida
     * @param to - vértice de chegada
     * @param entidadeST - ST que contem todas as entidades
     * @param passagemST - ST que contem todos os pontos de passagem
     * @return - imprime retornando o que é retornado da função toString
     */
    public static String shortestPath(EdgeWeightedDigraph g, int from, int to, BST_Aed2<Integer, Entidade> entidadeST, BST_Aed2<Integer, Passagem> passagemST) {
        StringBuilder sb = new StringBuilder();

        DijkstraSP sp = new DijkstraSP(g, from);

        Entidade local_from = entidadeST.get(from);
        int nome_local_from = local_from.getNomeEntidade(local_from, passagemST);
        Entidade local_to = entidadeST.get(to);
        int nome_local_to = local_to.getNomeEntidade(local_to, passagemST);

        // print shortest path
        if (sp.hasPathTo(to)) {

            sb.append(nome_local_from).append(" to ").append(nome_local_to).append(":\n\n");
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {
                    Entidade s1 = entidadeST.get(c.from());
                    int nome_local_s1 = s1.getNomeEntidade(s1, passagemST);
                    Entidade s2 = entidadeST.get(c.to());
                    int nome_local_s2 = s2.getNomeEntidade(s2, passagemST);
                    sb.append(nome_local_s1).append(" > ").append(nome_local_s2).append("\n");
                }
            }
            sb.append("\nTotal distance - ").append(String.format("%.2f", sp.distTo(to))).append("metros ");
        } else {
            sb.append(local_from.getId()).append(" to ").append(local_to.getId()).append("\t has no path");
        }

        System.out.println(sb.toString());
        return sb.toString();
    }


    /**
     * Função que usa coordenadas de 2 pontos diferentes para calcular a distância entre eles
     *
     * @param lat1 - latitude do ponto 1
     * @param long1 - longitude do ponto 1
     * @param lat2 - latitude do ponto 2
     * @param long2 - longitude do ponto 2
     * @return - distância entre os 2 pontos
     */
    public static double HaversineInM(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r) * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d * 1000;
    }

    /**
     * Função que verifica se o grafo é conexo ou não
     *
     * @param g - grafo
     */
    public static void isConnected(EdgeWeightedDigraph g) {
        int s = 0;
        int flag = 0;

        DijkstraSP sp = new DijkstraSP(g, s);

        for (int t = 0; t < g.V(); t++) {
            if (!sp.hasPathTo(t)) {
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("GEO GRAPH is connected!");
        } else {
            System.out.println("GEO GRAPH is not connected!");
        }
    }
}

