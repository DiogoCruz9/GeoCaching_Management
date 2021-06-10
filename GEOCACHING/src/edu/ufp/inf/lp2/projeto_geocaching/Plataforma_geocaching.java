package edu.ufp.inf.lp2.projeto_geocaching;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import javafx.event.ActionEvent;

import javax.sound.sampled.Line;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.stream.Stream;

/**
 * Classe Plafroma_geocaching  com os seus respectivos argumentos e metodos/ contem argumentos e metodos de user
 */
public class Plataforma_geocaching implements Serializable {

    private int idUtilizador;
    private String name;
    private String tipo_utilizador;
    private SeparateChainingHashST<Integer, Cache> hidden_caches_user_ST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Cache> visited_caches_user_ST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Cache> getCachesUserST = new SeparateChainingHashST<>();

    /**
     * Construtor da Cle assPlataforma geocaching«
     *
     * @param idUtilizador
     * @param name
     * @param tipo_utilizador
     * @param visited_caches_user_ST
     * @param hidden_caches_user_ST
     */
    public Plataforma_geocaching(int idUtilizador, String name, String tipo_utilizador, SeparateChainingHashST<Integer, Cache> visited_caches_user_ST, SeparateChainingHashST<Integer, Cache> hidden_caches_user_ST) {
        this.idUtilizador = idUtilizador;
        this.name = name;
        this.tipo_utilizador = tipo_utilizador;
        this.hidden_caches_user_ST = hidden_caches_user_ST;
        this.visited_caches_user_ST = visited_caches_user_ST;
    }

    /**
     * Construtor da Cle assPlataforma geocaching«
     *
     * @param idUtilizador
     * @param name
     * @param tipo_utilizador
     */
    public Plataforma_geocaching(int idUtilizador, String name, String tipo_utilizador) {
        this.idUtilizador = idUtilizador;
        this.name = name;
        this.tipo_utilizador = tipo_utilizador;

    }

    /**
     * Método para adicionar utilizador
     *
     * @param Plataforma_geocachingST ST - BSt dos utilizadores
     * @param nome                    String - nome dos utilizadores
     * @param tipo_utilizador         String - tipo de utilizador
     */
    public static void adicionarUtilizador(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST, String nome, String tipo_utilizador) {

        Plataforma_geocaching aux_Plataforma_geocaching = new Plataforma_geocaching(Plataforma_geocachingST.max() + 1, nome, tipo_utilizador);
        Plataforma_geocachingST.put(Plataforma_geocachingST.max() + 1, aux_Plataforma_geocaching);

        System.out.println("User inserido com sucesso");
    }

    /**
     * Método para remover utilizador
     *
     * @param Plataforma_geocachingST  ST - BSt dos utilizadores
     * @param id_Plataforma_geocaching int- id do user na plataforma
     */
    public static void removerUtilizador(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST, int id_Plataforma_geocaching) {

        Plataforma_geocaching aux_Plataforma_geocaching = Plataforma_geocachingST.get(id_Plataforma_geocaching);

        SeparateChainingHashST<Integer, Cache> aux_class_studentST = aux_Plataforma_geocaching.getCachesUserST();
        for (int i = 0; i < aux_class_studentST.size(); i++) {

            Cache aux_class = aux_class_studentST.get(i); //remover da turma st
            for (int j = 0; j < aux_class.getStudent_classST().size(); j++) {
                if (aux_Plataforma_geocaching == aux_class.getStudent_classST().get(j)) {
                    aux_class.getStudent_classST().delete(j);
                }
            }
        }
        Plataforma_geocachingST.delete(id_Plataforma_geocaching); //remover da st de Plataforma_geocachings
        System.out.println("User removido com sucesso");
    }

    /**
     * Função para carregar os utilizdores do ficheiro
     *
     * @param Plataforma_geocachingST - BST que contem todos os utilizadores
     * @param path                    String           - caminho do ficheiro de onde se pretende ler os utilizadores
     */
    public static void loadUser(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {


                //SeparateChainingHashST<Integer, Plataforma_geocaching> aux_studentST = new SeparateChainingHashST<>();
                //SeparateChainingHashST<Integer, Cache> aux_class_studentST = new SeparateChainingHashST<>();


                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                String aux3 = token[2];


                Plataforma_geocaching aux_Plataforma_geocaching = new Plataforma_geocaching(Integer.parseInt(aux1), aux2, aux3);
                Plataforma_geocachingST.put(Integer.parseInt(aux1), aux_Plataforma_geocaching);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
         * Função para imprimir todos os utilizaodores da ST
         *
         * @param Plataforma_geocachingST - ST que contem todos os utilizadores
         */
    public static void printuser(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST) {
        System.out.println("################## Listagem dos Users##################" + "\n");
        for (Integer key : Plataforma_geocachingST.emordem()) {
            print_Plataforma_geocaching(Plataforma_geocachingST, key);
        }
        System.out.println("\n");
    }

    /**
     * Função para imprimir todos os alunos da ST de uma forma simplificada
     *
     * @param Plataforma_geocachingST - ST que contem todos os alunos
     */
    public static void printPlataforma_geocachings_simple(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST) {
        System.out.println("################## Listagem dos Users##################" + "\n");
        for (Integer key : Plataforma_geocachingST.emordem()) {
            print_Plataforma_geocaching_simple(Plataforma_geocachingST, key);
        }
        System.out.println("\n");
    }

    /**
     * Função para imprimir todas as informações de um Utilizador dada uma key
     *
     * @param Plataforma_geocachingST - ST que contem todos os alunos
     * @param key                     - chave do Utilizador que se pretende imprimir
     */
    public static void print_Plataforma_geocaching(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST, Integer key) {
        System.out.println("ID: " + Plataforma_geocachingST.get(key).getIdPlataforma_geocaching() + " -" +
                " Nome: " + Plataforma_geocachingST.get(key).getName() + " -" +
                " Tipo: " + Plataforma_geocachingST.get(key).gettipo());


    }

    /**
     * Função para imprimir apenas o nome e o id de um aluno dada uma key
     *
     * @param Plataforma_geocachingST - ST que contem todos os alunos
     * @param key                     - chave do aluno que se pretende imprimir
     */
    public static void print_Plataforma_geocaching_simple(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST, Integer key) {
        System.out.println("ID: " + Plataforma_geocachingST.get(key).getIdPlataforma_geocaching() + " -" +
                " Nome: " + Plataforma_geocachingST.get(key).getName());
    }

    /**
     * Função que grava no txt todos os utilizadores da ST
     *
     * @param Plataforma_geocachingST - ST que contem todos os utilizadores
     * @param path                    - caminho do ficheiro para onde se pretende ler todos os utilizadores
     */
    public static void savePlataforma_geocaching(BST_Aed2<Integer, Plataforma_geocaching> Plataforma_geocachingST, String path) {
        Out out = new Out(path);

        for (Integer key : Plataforma_geocachingST.emordem()) {
            out.println(
                    Plataforma_geocachingST.get(key).getIdPlataforma_geocaching() + ";"
                            + Plataforma_geocachingST.get(key).getName() + ";"
                            + Plataforma_geocachingST.get(key).gettipo() + ";");
        }
        System.out.println("Users guardados com sucesso");
    }

    /**
     * Função para imprimir caches visitadas dado um user id
     *
     * @param u Plataforma_geocaching -variavel proveniente da class Plataforma-geocaching para invocar a class para se usar na funçao
     */
    public static void caches_visitadas_por_user(Plataforma_geocaching u) {

        System.out.println("Caches visitadas pelo user " + u.getName());
        SeparateChainingHashST<Integer, Cache> aux = u.getCachesUserST();
        for (int i = 0; i < aux.size(); i++) {
            Cache aux_class = aux.get(i);
            if (aux_class != null) {
                System.out.println("ID: " + aux_class.getId_cache()
                        + " -" +
                        " Dificuldade: " + aux_class.getDificuldade()
                        + " -" +
                        " Regiao: " + aux_class.getRegiao()
                        + " -" +
                        " Numero de itens: " + aux_class.getNumItens()
                        + " -" +
                        " Tipo: " + aux_class.getTipo_cache());
            }
        }
    }

    /**
     * Função para imprimir caches visitadas dado um user id
     *
     * @param u      Plataforma_geocaching -variavel proveniente da class Plataforma-geocaching para invocar a class para se usar na funçao
     * @param regiao - regiao a escolher
     */
    public static void caches_visitadas_por_user_por_regiao(Plataforma_geocaching u, String regiao) {

        System.out.println("Caches visitadas pelo user " + u.getName());
        SeparateChainingHashST<Integer, Cache> aux = u.getCachesUserST();
        for (int i = 0; i < aux.size(); i++) {
            Cache aux_class = aux.get(i);
            if (aux_class != null) {
                if (aux_class.getRegiao().equals(regiao)) {
                    System.out.println("ID: " + aux_class.getId_cache()
                            + " -" +
                            " Dificuldade: " + aux_class.getDificuldade()
                            + " -" +
                            " Regiao: " + aux_class.getRegiao()
                            + " -" +
                            " Numero de itens: " + aux_class.getNumItens()
                            + " -" +
                            " Tipo: " + aux_class.getTipo_cache());
                }
            }
        }
    }

    /**
     * Funçao que indica o top 5 de caches mais visitadas pelos utilizadores
     *
     * @param datainicial String - Data inicial
     * @param datafinal   String - Data final
     * @param path        - caminho do ficheiro que se pretende ler o top 5
     * @param visitast    BST - VisitaST
     */
    public static void numero_caches_visitadas_top5(String datainicial, String datafinal, String path, RedBlackBST<Integer, Visita> visitast) {

        In in = new In(path);

        String line, word = "";

        int count = 0, maxCount = 0;

        ArrayList<String> words = new ArrayList<String>();

        int a = Integer.parseInt(datainicial);
        int b = Integer.parseInt(datafinal);

        long unix_seconds = a;
        long unix_seconds2 = b;

        //convert seconds to milliseconds
        Date date = new Date(unix_seconds * 1000L);
        Date date2 = new Date(unix_seconds2 * 1000L);

        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        String java_date = jdf.format(date);
        String java_date2 = jdf.format(date2);

        System.out.println("Utilizadores que visitaram caches entre " + java_date + " e " + java_date2 + ":\n");

        try {
            while (!in.isEmpty()) {

                String[] token = in.readLine().split(";");
                String aux1 = token[0]; //epoch
                String aux2 = token[1]; //id cache
                String aux3 = token[2]; // user

                if (a <= Integer.parseInt(aux1) && b >= Integer.parseInt(aux1)) {

                    Visita aux_visita = new Visita(Integer.parseInt(aux1), Integer.parseInt(aux2), aux3);
                    visitast.put(Integer.parseInt(aux1), aux_visita);

                    words.add(aux3);

                    for (int i = 0; i < words.size(); i++) {
                        count = 1;
                        //Count each word in the file and store it in variable count
                        for (int j = i + 1; j < words.size(); j++) {
                            if (words.get(i).equals(words.get(j))) {
                                count++;
                            }
                        }
                        if (count > maxCount) {
                            maxCount = count;
                            word = words.get(i);
                        }
                    }
                }
            }
            System.out.println("Maior visitante de caches foi o " + word + " com " + maxCount + " caches");

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Metodo para indicar as caches nao visitadas por um utilizador dado um id
     *
     * @param u       -variavel proveniente da class Plataforma-geocaching para invocar a class para se usar na funçao
     * @param cacheST BST - CachesST
     * @param userST  BST - UsersST
     * @param path    -caminho do ficheiro que se pretende ler
     */
    public static void caches_nao_visitadas_por_user(Plataforma_geocaching u, BST_Aed2<Integer, Cache> cacheST, BST_Aed2<Integer, Plataforma_geocaching> userST, String path) {
        System.out.println("Caches NÃO visitadas pelo user " + u.getName());
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
                for (int i = 0; i < j; i++) { //meter os estudantes na st
                    //String aux7 = token[x + i + 1]; // ALUNO X
                    //para meter no ficheiro txt as posicoes 6+alunoA+1 para adicionar sempre +1
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux6));
                    aux_userST.put(i, aux_user);
                }
                Cache aux_cache = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_userST);
                cacheST.put(Integer.parseInt(aux1), aux_cache);

                int z = Integer.parseInt(aux6);
                int count = 0;
                for (int i = 0; i < z; i++) { //associar esta turma a cada um dos estudantes
                    String aux8 = token[x + i + 1];
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux8));
                    aux_cache_userST = aux_user.getCachesUserST();
                    aux_cache_userST.put(aux_cache_userST.size(), aux_cache);

                    int w = Integer.parseInt(aux8);
                    if (w == u.idUtilizador) {
                        count = 1;
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println("ID: " + aux_cache.getId_cache()
                            + " -" +
                            " Dificuldade: " + aux_cache.getDificuldade()
                            + " -" +
                            " Regiao: " + aux_cache.getRegiao()
                            + " -" +
                            " Numero de itens: " + aux_cache.getNumItens()
                            + " -" +
                            " Tipo: " + aux_cache.getTipo_cache());
                }


            }


        } catch (Exception exception) {
            System.out.println(exception);
        }


    }

    /**
     * Metodo para indicar as caches nao visitadas por um utilizador dado um id
     *
     * @param u       -variavel proveniente da class Plataforma-geocaching para invocar a class para se usar na funçao
     * @param cacheST BST - CachesST
     * @param userST  BST - UsersST
     * @param path    -caminho do ficheiro que se pretende ler
     * @param regiao  - regiao a escolher
     */
    public static void caches_nao_visitadas_por_user_por_regiao(Plataforma_geocaching u, BST_Aed2<Integer, Cache> cacheST, BST_Aed2<Integer, Plataforma_geocaching> userST, String path, String regiao) {
        System.out.println("Caches NÃO visitadas pelo user " + u.getName());
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
                for (int i = 0; i < j; i++) { //meter os estudantes na st
                    //String aux7 = token[x + i + 1]; // ALUNO X
                    //para meter no ficheiro txt as posicoes 6+alunoA+1 para adicionar sempre +1
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux6));
                    aux_userST.put(i, aux_user);
                }
                Cache aux_cache = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_userST);
                cacheST.put(Integer.parseInt(aux1), aux_cache);

                int z = Integer.parseInt(aux6);
                int count = 0;
                for (int i = 0; i < z; i++) { //associar esta turma a cada um dos estudantes
                    String aux8 = token[x + i + 1];
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux8));
                    aux_cache_userST = aux_user.getCachesUserST();
                    aux_cache_userST.put(aux_cache_userST.size(), aux_cache);

                    int w = Integer.parseInt(aux8);
                    if (w == u.idUtilizador) {
                        count = 1;
                        break;
                    }
                }
                if (count == 0) {
                    if (aux_cache.getRegiao().equals(regiao))
                        System.out.println("ID: " + aux_cache.getId_cache()
                                + " -" +
                                " Dificuldade: " + aux_cache.getDificuldade()
                                + " -" +
                                " Regiao: " + aux_cache.getRegiao()
                                + " -" +
                                " Numero de itens: " + aux_cache.getNumItens()
                                + " -" +
                                " Tipo: " + aux_cache.getTipo_cache());
                }


            }


        } catch (Exception exception) {
            System.out.println(exception);
        }


    }

    /** Método para retorno da ST getCachesUserST
     * @return ST- CachesUserST
     */
    public SeparateChainingHashST<Integer, Cache> getCachesUserST() {
        return getCachesUserST;
    }

    /** Método para definir CachesUserST
     */
    public void setCachesUserST(SeparateChainingHashST<Integer, Cache> getCachesUserST) {
        this.getCachesUserST = getCachesUserST;
    }

    /** Método para retorno do idPlataforma_geocaching
     * @return int- idutilizador
     */
    public int getIdPlataforma_geocaching() {
        return idUtilizador;
    }

    /** Método para definir idPlataforma_geocaching
     */
    public void setIdPlataforma_geocaching(int idPlataforma_geocaching) {
        this.idUtilizador = idUtilizador;
    }

    /** Método para retorno do nome
     * @return String- name
     */
    public String getName() {
        return name;
    }

    /** Método para definir name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Método para retorno do tipo
     * @return int- tipo_utilizador
     */
    public String gettipo() {
        return tipo_utilizador;
    }

    /** Método para definir tipo_utilizador
     */
    public void settipo(String tipo_utilizador) {
        this.tipo_utilizador = tipo_utilizador;
    }

    /**
     * Funcao que edita um determinado user
     * @param userst - st dos users
     * @param id_user - id do user que queremos alterar
     */
    public static void editarUser(BST_Aed2<Integer, Plataforma_geocaching> userst, int id_user) {
        if (userst.contains(id_user)) {

            System.out.println("O que pretende alterar:");
            System.out.println("1-Nome");
            System.out.println("2-Tipo");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {
                case "1":
                    String nome = scanIn.nextLine();
                    userst.get(id_user).setName(nome);
                    break;
                case "2":
                    String tipo = scanIn.nextLine();
                    userst.get(id_user).settipo(tipo);
                    break;
            }
            System.out.println("Correction made!\n");
        } else {
            System.out.println("Not possible to Correct now!\n");
        }

    }

    /**
     * Função que imprime caracteristicas do user na interface gráfica
     *
     * @param p - user que se pretende imprimir na interface gráfica
     * @return - imprime o user através do método toString
     */
    public static String print_user_gui(Plataforma_geocaching p) {
        StringBuilder s = new StringBuilder();
        s.append("ID: " + p.getIdPlataforma_geocaching() + "\n");
        s.append("Nome: " + p.getName() + "\n");
        s.append("Tipo: " + p.gettipo() + "\n");
        return s.toString();
    }

    /**
     * Imprimir a pesquisa de caches do user na GUI
     * @param p- user que se pretende imprimir na interface grafica
     * @return  imprime os users na gui atraves metodo toString
     */
    public static String print_search_user_caches_gui(Plataforma_geocaching p){
        SeparateChainingHashST<Integer, Cache> aux = p.getCachesUserST();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < aux.size(); i++) {
            Cache aux_class = aux.get(i);
            if (aux_class != null) {
                s.append("ID: " + aux_class.getId_cache() + "\n");
            }
        }
            return s.toString();

    }

    /**
     * Funçao que mostra as caches nao visitadas dado o id dum user
     * @param u- Id do user
     * @param cacheST - St das caches
     * @param userST - St dos users
     * @param path -caminho do ficheiro que se pretende ler
     * @return -imprime as caches nao visitadas usando o metodo to string
     */
    public static String caches_nao_visitadas_por_user_gui(Plataforma_geocaching u, BST_Aed2<Integer, Cache> cacheST, BST_Aed2<Integer, Plataforma_geocaching> userST, String path) {
        //System.out.println("Caches NÃO visitadas pelo user " + u.getName());
        StringBuilder s = new StringBuilder();
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
                for (int i = 0; i < j; i++) { //meter os estudantes na st
                    //String aux7 = token[x + i + 1]; // ALUNO X
                    //para meter no ficheiro txt as posicoes 6+alunoA+1 para adicionar sempre +1
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux6));
                    aux_userST.put(i, aux_user);
                }
                Cache aux_cache = new Cache(Integer.parseInt(aux1), aux2, Integer.parseInt(aux3), Integer.parseInt(aux4), aux5, aux_userST);
                cacheST.put(Integer.parseInt(aux1), aux_cache);

                int z = Integer.parseInt(aux6);
                int count = 0;
                for (int i = 0; i < z; i++) { //associar esta turma a cada um dos estudantes
                    String aux8 = token[x + i + 1];
                    Plataforma_geocaching aux_user = userST.get(Integer.parseInt(aux8));
                    aux_cache_userST = aux_user.getCachesUserST();
                    aux_cache_userST.put(aux_cache_userST.size(), aux_cache);

                    int w = Integer.parseInt(aux8);
                    if (w == u.idUtilizador) {
                        count = 1;
                        break;
                    }
                }
                if (count == 0) {
                    s.append("ID: " + aux_cache.getId_cache() + "\n");
                }


            }


        } catch (Exception exception) {
            System.out.println(exception);
        }

        return s.toString();
    }

    /**
     * Funçao que procura o top 5 de caches mais visitadas pelos users
     * @param datainicial -data inicial no intervalo que se quer a pesquisa ao top5
     * @param datafinal- data final no intervalo que se quer a pesquisa ao top5
     * @param path -caminho do ficheiro de onde se pretende ler
     * @param visitast - ST das Visitas
     * @return - imprime o top 5 das caches usando o metodo toString
     */
    public static String numero_caches_visitadas_top5_gui(String datainicial, String datafinal, String path, RedBlackBST<Integer, Visita> visitast) {
        StringBuilder s = new StringBuilder();
        In in = new In(path);

        String line, word = "";

        int count = 0, maxCount = 0;

        ArrayList<String> words = new ArrayList<String>();

        int a = Integer.parseInt(datainicial);
        int b = Integer.parseInt(datafinal);

        long unix_seconds = a;
        long unix_seconds2 = b;

        //convert seconds to milliseconds
        Date date = new Date(unix_seconds * 1000L);
        Date date2 = new Date(unix_seconds2 * 1000L);

        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        String java_date = jdf.format(date);
        String java_date2 = jdf.format(date2);

        //ArrayList<String> nomes= new ArrayList<>(5);
        //ArrayList<Integer> num= new ArrayList<>(5);

        System.out.println("Utilizadores que visitaram caches entre " + java_date + " e " + java_date2 + ":\n");

        try {
            while (!in.isEmpty()) {

                String[] token = in.readLine().split(";");
                String aux1 = token[0]; //epoch
                String aux2 = token[1]; //id cache
                String aux3 = token[2]; // user

                if (a <= Integer.parseInt(aux1) && b >= Integer.parseInt(aux1)) {

                    Visita aux_visita = new Visita(Integer.parseInt(aux1), Integer.parseInt(aux2), aux3);
                    visitast.put(Integer.parseInt(aux1), aux_visita);

                    words.add(aux3);

                    for (int i = 0; i < words.size(); i++) {
                        count = 1;
                        //Count each word in the file and store it in variable count
                        for (int j = i + 1; j < words.size(); j++) {
                            if (words.get(i).equals(words.get(j))) {
                                count++;
                            }
                        }
                        if (count > maxCount) {
                            maxCount = count;
                            word = words.get(i);

                            //if(!nomes.contains(aux3)){
                            //    nomes.add(aux3);
                            //}

                            //num.add(maxCount);
                        }
                    }
                }

            }

            //System.out.println("Maior visitante de caches foi o " + word + " com " + maxCount + " caches");
            s.append(word + " com " + maxCount + " caches");

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return s.toString();
    }

    /**
     * metodo para ler os users do ficheiro binario
     * @param userst
     * @throws IOException
     */
    public static void loadUserBin(BST_Aed2<Integer, Plataforma_geocaching> userst) throws IOException {

        FileInputStream f = new FileInputStream(".//data//saveusers.dat");
        DataInputStream d = new DataInputStream(f);

        for (int i=0 ;i<5;i++) {
            int id = d.readInt();
            System.out.println(id);
            String nome = d.readUTF();
            System.out.println(nome);
            String tipo = d.readUTF();
            System.out.println(tipo);

            Plataforma_geocaching aux_users = new Plataforma_geocaching(id,nome,tipo);
            userst.put(id, aux_users);
        }
    }


}