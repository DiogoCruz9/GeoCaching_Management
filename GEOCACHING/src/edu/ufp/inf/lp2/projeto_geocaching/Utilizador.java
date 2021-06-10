package edu.ufp.inf.lp2.projeto_geocaching;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Funçao utilizador com os seus respectivos argumentos e metodos
 */
public class Utilizador {

    public String tipo_utilizador;
    public String nome;
    public Integer id_utilizador;
    public SeparateChainingHashST<Integer, Cache> cachesST = new SeparateChainingHashST<>();


    /**
     * construtor do utilizador e seus argumentos
     *
     * @param tipo_utilizador
     * @param nome
     * @param id_utilizador
     */
    public Utilizador(String tipo_utilizador, String nome, Integer id_utilizador) {
        this.tipo_utilizador = tipo_utilizador;
        this.nome = nome;
        this.id_utilizador = id_utilizador;
    }

    /**
     * Método para carregar caches do ficheiro txt
     *
     * @param cacheST ST - BSt das caches
     * @param userST  ST - BSt dos utilizadores
     * @param path    String- caminho do ficheiro de onde se pretende carregar as caches
     */
    public static void loadCaches(BST_Aed2<Integer, Cache> cacheST, BST_Aed2<Integer, Plataforma_geocaching> userST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {
                int x = 5;

                SeparateChainingHashST<Integer, Plataforma_geocaching> aux_userST = new SeparateChainingHashST<>();
                SeparateChainingHashST<Integer, Cache> aux_cache_userST = new SeparateChainingHashST<>();

                String[] token = in.readLine().split(";");
                String aux1 = token[0]; //ID CACHE
                String aux2 = token[1]; //DIFICULDADE
                String aux3 = token[2]; //NUMERO ITEMS
                String aux4 = token[3]; //TIPO
                String aux5 = token[4]; //REGIAO
                String aux6 = token[5]; //numero users visitados
                int j = Integer.parseInt(aux6);
                for (int i = 0; i < j; i++) { //meter os users na st
                    String aux7 = token[x + i + 1]; // ALUNO X
                    //para meter no ficheiro txt as posicoes 6+user+1 para adicionar sempre +1
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux7));
                    aux_userST.put(i, aux_user);
                }
                Cache aux_cache = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_userST);
                cacheST.put(Integer.parseInt(aux1), aux_cache);

                int z = Integer.parseInt(aux6);
                for (int i = 0; i < z; i++) { //associar esta cache a cada um dos users
                    String aux8 = token[x + i + 1];
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux8));
                    aux_cache_userST = aux_user.getCachesUserST();
                    aux_cache_userST.put(aux_cache_userST.size(), aux_cache);
                }
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public static SeparateChainingHashST<Integer, Plataforma_geocaching> loadCachesFORGUI(BST_Aed2<Integer, Cache> cacheST, BST_Aed2<Integer, Plataforma_geocaching> userST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {
                int x = 5;

                SeparateChainingHashST<Integer, Plataforma_geocaching> aux_userST = new SeparateChainingHashST<>();
                SeparateChainingHashST<Integer, Cache> aux_cache_userST = new SeparateChainingHashST<>();

                String[] token = in.readLine().split(";");
                String aux1 = token[0]; //ID CACHE
                String aux2 = token[1]; //DIFICULDADE
                String aux3 = token[2]; //NUMERO ITEMS
                String aux4 = token[3]; //TIPO
                String aux5 = token[4]; //REGIAO
                String aux6 = token[5]; //numero users visitados
                int j = Integer.parseInt(aux6);
                for (int i = 0; i < j; i++) { //meter os users na st
                    String aux7 = token[x + i + 1]; // ALUNO X
                    //para meter no ficheiro txt as posicoes 6+alunoA+1 para adicionar sempre +1
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux7));
                    aux_userST.put(i, aux_user);
                }

                Cache aux_cache = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_userST);
                cacheST.put(Integer.parseInt(aux1), aux_cache);
                return aux_userST;

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

        return null;
    }
    /**
     * Método para adicionar caches
     *
     * @param cachesST    ST - BSt das caches
     * @param tipo_cache  int -tipo de cache
     * @param dificuldade int - dificuladade
     * @param regiao      string - Regiao
     * @param numItens    int - Numero de itens
     */
    public static void addCaches(BST_Aed2<Integer, Cache> cachesST, String tipo_cache, int dificuldade, String regiao, int numItens) {

        Cache aux_Plataforma_geocaching = new Cache(cachesST.max() + 1, tipo_cache, dificuldade, numItens, regiao);
        cachesST.put(cachesST.max() + 1, aux_Plataforma_geocaching);

        System.out.println("Cache inserido com sucesso");
    }

    /**
     * Método para editar caches
     *
     * @param cachesST ST - BSt das caches
     * @param id_cache int _id ca cache
     */
    public static void editarCache(BST_Aed2<Integer, Cache> cachesST, int id_cache) {
        if (cachesST.contains(id_cache)) {

            System.out.println("Pick Wisely:");
            System.out.println("1-tipo_cache");
            System.out.println("2-Dificuldade");
            System.out.println("3-NumeroItens");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {

                case "1":
                    String tipo_cache = scanIn.nextLine();
                    cachesST.get(id_cache).setTipo_cache(tipo_cache);
                    break;
                case "2":
                    Integer dificuldade = scanIn.nextInt();
                    cachesST.get(id_cache).setDificuldade(dificuldade);
                    break;
                case "3":
                    Integer numItens = scanIn.nextInt();
                    cachesST.get(id_cache).setNumItens(numItens);
                    break;
            }
            System.out.println("Job Done!");
        } else {
            System.out.println("Error 404!");
        }
    }

    /**
     * Método para remover caches
     *
     * @param cachesST ST - BSt das caches
     * @param id_cache int _id ca cache
     */
    public static void removerCache(BST_Aed2<Integer, Cache> cachesST, int id_cache) {
        try {
            cachesST.delete(id_cache); //remover da st de Plataforma_geocachings
            System.out.println("Cache removida com sucesso");
        } catch (Exception e) {
            System.out.println("Not possible to remove!\n");
        }

    }

    /**
     * Método para dar load das caches do txt
     *
     * @param cachesST ST - BSt das caches
     * @param path     String- caminho do ficheiro de onde se pretende carregar as caches
     */
    public static void loadCache(BST_Aed2<Integer, Cache> cachesST, String path) {
        try {
            In in = new In(path);
            while (!in.isEmpty()) {
                String[] split = in.readLine().split(";");
                String aux = split[0];
                String aux1 = split[1];
                String aux2 = split[2];
                String aux3 = split[3];
                String aux4 = split[4];
                Cache c = new Cache(Integer.parseInt(aux), aux1, Integer.parseInt(aux2), Integer.parseInt(aux3), aux4);
                cachesST.put(Integer.parseInt(aux), c);
            }
            System.out.println("Caches Loaded!");
        } catch (NumberFormatException e) {
            System.out.println("Not possible to Load Cache!");
        }

    }

    /**
     * Metodo em que seleciona todas as caches que sao premium e que tem itens
     *
     * @param cacheST ST - BSt das caches
     * @param path    String- caminho do ficheiro de onde se pretende carregar as caches
     */
    public static void getPremiumWithObjects(BST_Aed2<Integer, Cache> cacheST, String path) {
        In in = new In(path);
        System.out.println("Todas as caches PREMIUM com pelo menos 1 item:\n");
        while (!in.isEmpty()) {


            SeparateChainingHashST<Integer, Plataforma_geocaching> aux_studentST = new SeparateChainingHashST<>();


            String[] token = in.readLine().split(";");
            String aux1 = token[0]; //ID CACHE
            String aux2 = token[1]; //DIFICULDADE
            String aux3 = token[2]; //NUMERO ITEMS
            String aux4 = token[3]; //TIPO
            String aux5 = token[4]; //REGIAO

            Cache aux_class = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_studentST);
            cacheST.put(Integer.parseInt(aux1), aux_class);

            if ((Integer.parseInt(aux3) != 0) && (aux2.equals("premium"))) {

                System.out.println(
                        " ID Cache: " + aux1 +
                                "  ---- Numero de Itens: " + aux3 +
                                "  ---- Tipo: " + aux2
                );



            }
        }


    }

    /**
     * Metodo que grava num ficheiro as caches
     *
     * @param cachesST ST- CachesST
     * @param path     String- caminho do ficheiro para onde se pretende gravar as caches
     */
    public static void saveCache(BST_Aed2<Integer, Cache> cachesST, String path) {
        try {
            Out out = new Out(path);
            for (Integer key : cachesST.emordem()) {
                Cache cache = cachesST.get(key);
                out.println(
                        cache.getId_cache() + ";" +
                                cache.getTipo_cache() + ";"
                                + cache.getDificuldade() + ";" + cache.getNumItens()
                );
            }

            System.out.println("Cache saved!");
        } catch (Exception e) {
            System.out.println("Not Possible to save Caches!");
        }
    }

    /**
     * Metodo para imprimir todas as caches provenientes da ST
     *
     * @param cachesST ST- CacheST
     * @param id_cache int - Id da cache
     */
    public static void listarCache(BST_Aed2<Integer, Cache> cachesST, int id_cache) {
        try {
            for (Integer key : cachesST.emordem()) {
                Cache cache = cachesST.get(key);
                System.out.println(
                        " ID Cache: " + cache.getId_cache() + " *" +
                                "Tipo: " + cache.getTipo_cache() + " -" +
                                " Dificuldade " + cache.getDificuldade() + " -" +
                                " Numero de itens: " + cache.getNumItens() + " *"
                );

            }
        } catch (Exception e) {
            System.out.println("Not Possible To load Caches!");
        }
    }

    /**
     * Método para retorno do tipo_utilizador
     *
     * @return int- tipo_utilizador
     */
    public String getTipo_utilizador() {
        return tipo_utilizador;
    }

    /**
     * Método para definir tipo_utilizador
     */
    public void setTipo_utilizador(String tipo_utilizador) {
        this.tipo_utilizador = tipo_utilizador;
    }

    /**
     * Método para retorno do nome do utilizador
     *
     * @return String- Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para definir nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para retorno do id_utilizador
     *
     * @return int- id_utilizador
     */
    public Integer getId_utilizador() {
        return id_utilizador;
    }

    /**
     * Método para definir id_utilizador
     */
    public void setId_utilizador(Integer id_utilizador) {
        this.id_utilizador = id_utilizador;
    }

    /**
     * Função para criar caches
     *
     * @param cachesST    - st de caches
     * @param studentST   - st de users
     * @param tipo_cache  - int tipo de cache
     * @param dificuldade - int dificuldade
     * @param regiao      - String regiao
     * @param numItens    - int numero de itens
     */
    public static void addCacheST(BST_Aed2<Integer, Cache> cachesST, BST_Aed2<Integer, Plataforma_geocaching> studentST, String tipo_cache, int dificuldade, String regiao, int numItens) {

        Cache aux_class = new Cache(cachesST.max() + 1, tipo_cache, dificuldade, numItens, regiao);
        cachesST.put(cachesST.max() + 1, aux_class);

        System.out.println("Indique o número de alunos a adicionar:");
        Scanner scanIn5 = new Scanner(System.in);
        String choice5 = scanIn5.nextLine();

        Plataforma_geocaching.printPlataforma_geocachings_simple(studentST);

        int x = 1;

        for (int i = 0; i < Integer.parseInt(choice5); i++) {
            System.out.println("Indique o ID do aluno " + x + ":");
            x++;
            Scanner scanIn6 = new Scanner(System.in);
            String choice6 = scanIn6.nextLine();
            Plataforma_geocaching aux_student = studentST.get(Integer.parseInt(choice6));
            SeparateChainingHashST<Integer, Plataforma_geocaching> aux_student_classST = aux_class.getStudent_classST();
            aux_student_classST.put(aux_student_classST.size(), aux_student);
            aux_class.setStudent_classST(aux_student_classST);

            aux_student.getCachesUserST().put(aux_student.getCachesUserST().size() + 1, aux_class);
        }


        System.out.println("Cache adicionada com sucesso");

    }

    /**
     * Funcao para remover uma cache
     *
     * @param classST  - ST que contem todas as caches
     * @param id_cache - id da cache que se pretende remover
     */
    public static void removeClass(BST_Aed2<Integer, Cache> classST, int id_cache) {

        Cache aux_class = classST.get(id_cache);

        if (aux_class.getUser_cacheST().size() > 0) {

            for (int i = 0; i < aux_class.getStudent_classST().size(); i++) {

                Plataforma_geocaching aux_student = aux_class.getStudent_classST().get(i);
                SeparateChainingHashST<Integer, Cache> aux_class_studentST = aux_student.getCachesUserST();
                for (int j = 0; j < aux_class_studentST.size(); j++) {
                    if (aux_class.getId_cache().equals(aux_class_studentST.get(j).getId_cache())) {
                        aux_class_studentST.delete(j);
                    }
                }
                aux_student.setCachesUserST(aux_class_studentST);

            }

            classST.delete(id_cache);
            System.out.println("Cache removida com sucesso");

        } else {

            classST.delete(id_cache);
            System.out.println("Cache removida com sucesso");

        }

    }

    /**
     * Funcao que devolve numa pesquisa as caches premium com pelo menos 1 item
     * @param cacheST - st da Cache
     * @param path- caminho de onde se pretende ler as caches
     * @return imprime atraves do metod toString as caches premium com pelo menos 1 item
     */
    public static String getPremiumWithObjects_GUI(BST_Aed2<Integer, Cache> cacheST, String path) {
        In in = new In(path);
        StringBuilder s = new StringBuilder();
        //System.out.println("Todas as caches PREMIUM com pelo menos 1 item:\n");
        while (!in.isEmpty()) {

            SeparateChainingHashST<Integer, Plataforma_geocaching> aux_userST = new SeparateChainingHashST<>();

            String[] token = in.readLine().split(";");
            String aux1 = token[0]; //ID CACHE
            String aux2 = token[1]; //DIFICULDADE
            String aux3 = token[2]; //NUMERO ITEMS
            String aux4 = token[3]; //TIPO
            String aux5 = token[4]; //REGIAO

            Cache aux_cache = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_userST);
            cacheST.put(Integer.parseInt(aux1), aux_cache);

            if ((Integer.parseInt(aux3) != 0) && (aux2.equals("Premium"))) {
               s.append("ID Cache: " + aux1 + "\n");
               s.append("Numero de Itens: " + aux3 + "\n");
               s.append("Tipo: " + aux2 + "\n");
            }
        }
    return s.toString();

    }

    /**
     * funçao que imprime na interface grafica as caches
     * @param p- id da Cache
     * @return imprime as caches
     */
    public static String print_cache_gui(Cache p) {
        StringBuilder s = new StringBuilder();
        s.append("ID: " + p.getId_cache() + "\n");
        s.append("Tipo: " + p.getTipo_cache() + "\n");
        s.append("Dificuldade: " + p.getDificuldade() + "\n");
        s.append("Localização: " + p.getRegiao() + "\n");
        s.append("NºItens: " + p.getNumItens() + "\n");
        //s.append("Users: " + p.getUserVisitedST.get(0).id_utilizador + "\n");
        return s.toString();
    }

    /**
     * metodo para ler os caches do ficheiro binario
     * @param cachest
     * @throws IOException
     */
    public static void loadCacheBin(BST_Aed2<Integer, Cache> cachest) throws IOException {

        FileInputStream f = new FileInputStream(".//data//savecaches.dat");
        DataInputStream d = new DataInputStream(f);

        for (int i=0 ;i<33;i++) {
            int id = d.readInt();
            System.out.println(id);
            String tipo = d.readUTF();
            System.out.println(tipo);
            int dificuldade = d.readInt();
            System.out.println(dificuldade);
            int numitens = d.readInt();
            System.out.println(numitens);
            String regiao = d.readUTF();
            System.out.println(regiao);

            Cache aux_cache = new Cache(id,tipo,dificuldade,numitens,regiao);
            cachest.put(id, aux_cache);
        }
    }


}