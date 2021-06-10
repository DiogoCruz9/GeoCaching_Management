package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Date;

/**
 * Funçao main que permite chamar todas as funçoes do projecto Geocaching
 * */

public class Main {

    public static BST_Aed2<Integer, Cache> cacheST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Plataforma_geocaching> userST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Item> itemST = new BST_Aed2<>();
    public static RedBlackBST<Integer, Visita> visitasst = new RedBlackBST<>();
    public static BST_Aed2<Integer, TravelBug> tbst = new BST_Aed2<>();
    public static BST_Aed2<Integer, Entidade> entidadeST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Passagem> passagemST = new BST_Aed2<>();

    public static void main(String[] args) throws ParseException, IOException {

        String path1 = ".//data//users.txt";
        String path2 = ".//data//caches.txt";
        String path3 = ".//data//item.txt";
        String path4 = ".//data//visitas.txt";
        String path5 = ".//data//tb.txt";
        String path7 = ".//data//passagem.txt";

        String savepath1 = ".//data//saveusers.txt";
        String savepath2 = ".//data//savecaches.txt";
        String savepath3 = ".//data//saveitens.txt";
        String savepath4 = ".//data//savelogs.txt";

        BigInteger datafinal = new BigInteger("1620515334000");

        Plataforma_geocaching.loadUser(userST, path1);
        Utilizador.loadCaches(cacheST,userST, path2);
        Cache.loadItem(itemST,path3);
        Cache.loadTB(tbst,cacheST,path5);
        Passagem.loadPassagem(passagemST,cacheST,path7);
        Entidade.loadEntidade(entidadeST, path7);

    //---------------------------------------------------------------------------------------------
        //Plataforma_geocaching.loadUserBIN(userST,savepath1);

        //Plataforma_geocaching.loadUserBin(userST);

        BufferedReader br = null;
        Rede r = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/network.txt")));
            int V = Integer.parseInt(br.readLine());
            //System.out.println("V = " + V);
            r = new Rede(V);
            int E = Integer.parseInt(br.readLine());
            //System.out.println("E = " + E);

            String line = br.readLine();
            while(line!= null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());

                if ((cacheST.get(v).tipo_cache).equals("Premium") && (cacheST.get(w).tipo_cache).equals("Premium")) {

                    r.addEdge(new LigacaoMap(v, w, c1));


                }
                line = br.readLine();
            }

            System.out.println(r);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        r.shortestPath(r,0,4,entidadeST,passagemST);




        //----UTILIZADORES------------------------------------------------------------------------>

        //Cache.listarItem(itemST);
        //Cache.listarTB(tbst);
        //Cache.historicc_localizacoes_travel_bug(tbst);

        //Plataforma_geocaching.adicionarUtilizador(userST, "Xavi", "Admin");
        //Plataforma_geocaching.printuser(userST);
        //Plataforma_geocaching.removerUtilizador(userST,0);
        //Plataforma_geocaching.savePlataforma_geocaching(userST, savepath1);
        //Plataforma_geocaching.printuser(userST);

        //Plataforma_geocaching.editarUser(userST,3);

        //Plataforma_geocaching.printuser(userST);

        //----CACHE------------------------------------------------------------------------------->
        //Utilizador.loadCache(cacheST, path2);

        //Utilizador.listarCache(cacheST, 1);
        //Utilizador.addCaches(cacheST, "XXXX", 1, "lol", 3);
        //Utilizador.addCacheST(cacheST,userST,"sim",1,"lisboa",3);
        //Utilizador.editarCache();
        //Utilizador.removerCache();
        //Utilizador.saveCache(cacheST, savepath2);
        //Utilizador.editarCache(cacheST,1);
        //Utilizador.listarCache(cacheST, 4);
        //Utilizador.saveCache(cacheST, savepath2);



        //----ITEM------------------------------------------------------------------------------->

        //Cache.loadItem(itemST, path3);
        //Cache.listarItem(itemST,1);
        //Cache.adicionarItem(itemST, "Lapis", false);
        //Cache.editarItem(itemST,1);
        //Cache.listarItem(itemST,1);
        //Cache.removerItem(itemST,1);
        //Cache.saveItem(itemST,savepath3);


        Plataforma_geocaching u = userST.get(2);
        Plataforma_geocaching uc = userST.get(3);
        Cache c = cacheST.get(3);
        //Cache.listStudents(c);
        //Cache.addStudent(c,u);
        //Cache.listStudents(c);
        //Plataforma_geocaching.caches_visitadas_por_user(u);
        //Plataforma_geocaching.caches_visitadas_por_user_por_regiao(u, "porto");
        //Plataforma_geocaching.caches_nao_visitadas_por_user(u,cacheST, userST, path2);
        //Plataforma_geocaching.caches_nao_visitadas_por_user_por_regiao(u,cacheST, userST, path2,"porto");
        //Utilizador.getPremiumWithObjects(cacheST,path2);

        //Cache.addStudent(c,uc);
        //Cache.addStudent(c,u);

        //Plataforma_geocaching.removerUtilizador(userST,4);
        //Cache.listStudents(c);


       /*
       EPOCHS EXEMPLO
       Domingo, 9 de maio de 2021 00:08:54 - 1620515334
       Sexta-feira, 1 de janeiro de 2021 00:00:00 - 1609459200
       Quinta-feira, 1 de janeiro de 1970 01:00:00 - 0
       Sexta-feira, 25 de dezembro de 2015 12:00:00 - 1451044800
        */

        //Plataforma_geocaching.numero_caches_visitadas_top5("0","1620515334",path4,visitasst);

    }
}