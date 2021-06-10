package edu.ufp.inf.lp2.projeto_geocaching;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Cache com os seus respectivos parametros /contem as metodos respectivas a item
 */

public class Cache {

    public String tipo_cache;
    public int dificuldade;
    public String regiao;
    public Integer numItens;
    public Integer id_cache;

    public SeparateChainingHashST<Integer, Item> itemsST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Plataforma_geocaching> user_cacheST = new SeparateChainingHashST<>();
    public SeparateChainingHashST<Integer, Utilizador> getUserVisitedST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Plataforma_geocaching> student_classST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, TravelBug> getTBCACHEST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Passagem> getPassagemCacheST = new SeparateChainingHashST<>();

    /**
     * Método para retorno da ST User_cacheST
     *
     * @return ST - User_cacheST
     */
    public SeparateChainingHashST<Integer, Plataforma_geocaching> getUser_cacheST() {
        return user_cacheST;
    }
    public SeparateChainingHashST<Integer, Passagem> getPassagemCacheST() {
        return getPassagemCacheST;
    }


    /**
     * Construtor da class cache!
     *
     * @param id_cache
     * @param tipo_cache
     * @param student_classST
     * @param dificuldade
     * @param numItens
     * @param regiao
     */
    public Cache(Integer id_cache, String tipo_cache, SeparateChainingHashST<Integer, Plataforma_geocaching> student_classST, int dificuldade, Integer numItens, String regiao) {
        this.id_cache = id_cache;
        this.tipo_cache = tipo_cache;
        this.student_classST = student_classST;
        this.dificuldade = dificuldade;
        this.numItens = numItens;
        this.regiao = regiao;
    }

    public Cache(Integer id_cache, String tipo_cache, int dificuldade, Integer numItens, String regiao) {
        this.id_cache = id_cache;
        this.tipo_cache = tipo_cache;
        this.dificuldade = dificuldade;
        this.numItens = numItens;
        this.regiao = regiao;
    }

    public Cache(Integer id_cache, String tipo_cache, int dificuldade, Integer numItens, String regiao, SeparateChainingHashST<Integer, Plataforma_geocaching> student_classST) {
        this.id_cache = id_cache;
        this.tipo_cache = tipo_cache;
        this.dificuldade = dificuldade;
        this.numItens = numItens;
        this.regiao = regiao;
        this.user_cacheST = user_cacheST;

    }

    /**
     * Funcao para ir buscar as caches associadas ao travel bug
     * @return
     */
    public SeparateChainingHashST<Integer, TravelBug> getTBCACHEST() {
        return getTBCACHEST;
    }

    /**
     * Método para definir
     */
    public void setTBCACHEST(SeparateChainingHashST<Integer, TravelBug> getTBCACHEST) {
        this.getTBCACHEST = getTBCACHEST;
    }

    /**
     * Método para retorno do tipo de cache
     *
     * @return int - tipo de cache
     */
    public String getTipo_cache() {
        return tipo_cache;
    }

    /**
     * Método para definir tipo de cache
     */
    public void setTipo_cache(String tipo_cache) {
        this.tipo_cache = tipo_cache;
    }

    /**
     * Método para retorno da regiao
     *
     * @return string - regiao
     */
    public String getRegiao() {
        return regiao;
    }

    /**
     * Método para indicar regiao da cache
     */
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    /**
     * Método para retorno da dificuldade
     *
     * @return int - dificuldade
     */
    public int getDificuldade() {
        return dificuldade;
    }

    /**
     * Método para indicar dificuldade da cache
     */
    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    /**
     * Método para retorno do numero de itens
     *
     * @return int - Numitens
     */
    public Integer getNumItens() {
        return numItens;
    }

    /**
     * Método para indicar Numitens da cache
     */
    public void setNumItens(Integer numItens) {
        this.numItens = numItens;
    }

    /**
     * Método para retorno do id da cache
     *
     * @return int - id cache
     */
    public Integer getId_cache() {
        return id_cache;
    }

    /**
     * Método para indicar idcache
     */
    public void setId_cache(Integer id_cache) {
        this.id_cache = id_cache;
    }

    /**
     * Método para retorno do st User_cache
     *
     * @return ST- User_cacheST
     */
    public SeparateChainingHashST<Integer, Plataforma_geocaching> getStudent_classST() {
        return student_classST;
    }

    /**
     * Método para indicar User_CacheST
     */
    public void setStudent_classST(SeparateChainingHashST<Integer, Plataforma_geocaching> student_classST) {
        this.student_classST = student_classST;
    }

    /**
     * Método para adicionar item
     *
     * @param itemST      ST - BSt dos itens
     * @param nome        String - nome dos itens
     * @param isTravelBug Boolean - se é travel bug
     */
    public static void adicionarItem(BST_Aed2<Integer, Item> itemST, BST_Aed2<Integer, TravelBug> bugST, String nome, Boolean isTravelBug) {
        try {
            Item i = new Item(itemST.max() + 1, nome, isTravelBug);
            TravelBug tb = new TravelBug(bugST.max() + 1, nome, true);
            if (isTravelBug) {
                bugST.put(bugST.max() + 1, tb);
                System.out.println("Travel Bug Added!\n");
            } else {
                itemST.put(itemST.max() + 1, i);
                System.out.println("Item Added!\n");
            }

        } catch (Exception e) {
            System.out.println("Was not possible to create the item!\n");
        }
    }

    /**
     * Método para remover item
     *
     * @param itemST  ST - BSt dos itens
     * @param id_item int - id dos  itens
     */
    public static void removerItem(BST_Aed2<Integer, Item> itemST, int id_item) {
        try {

            itemST.delete(id_item);
            System.out.println("Item Sucessfully Removed!\n");
        } catch (Exception e) {
            System.out.println("Not possible to remove!\n");
        }
    }

    /**
     * Funçao para remover travel bugs
     * @param itemST - St dos travel bugs
     * @param id_item- id do item -int
     */
    public static void removerTB(BST_Aed2<Integer, TravelBug> itemST, int id_item) {
        try {
            itemST.delete(id_item);
            System.out.println("Travel Bug Sucessfully Removed!\n");
        } catch (Exception e) {
            System.out.println("Not possible to remove!\n");
        }
    }

    /**
     * Método para editar item
     *
     * @param itemST  ST - BSt dos itens
     * @param id_item int - id dos  itens
     */
    public static void editarItem(BST_Aed2<Integer, Item> itemST, int id_item) {
        if (itemST.contains(id_item)) {

            System.out.println("Pick Wisely:");
            System.out.println("1-Nome:");
            System.out.println("2-isTravelbug in the cache?:NO!\n");
            System.out.println("3-isTravelbug in the cache?:YES!\n");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {

                case "1":
                    String nome = scanIn.nextLine();
                    itemST.get(id_item).setNome(nome);
                    break;
                case "2":
                    itemST.get(id_item).setTravelBug(false);
                    break;
                case "3":
                    itemST.get(id_item).setTravelBug(true);
                    break;


            }
            System.out.println("Correction made!\n");
        } else {
            System.out.println("Not possible to Correct now!\n");
        }

    }

    /**
     * Método para retorno do st getUserVisitedST
     *
     * @return ST- getUserVisitedST
     */
    public SeparateChainingHashST<Integer, Utilizador> getUserVisitedST() {
        return getUserVisitedST;
    }

    /**
     * Método para indicar getUserVisitedST
     */
    public void setUserVisitedST(SeparateChainingHashST<Integer, Utilizador> getUserVisitedST) {
        this.getUserVisitedST = getUserVisitedST;
    }

    /**
     * Função para ir buscar travel bugs aos ficheiros
     * @param itemST- ST dos travel bugs
     * @param cacheSY- ST das caches
     * @param path - caminho de onde se pretende ler os Travel bugs
     */
    public static void loadTB(BST_Aed2<Integer, TravelBug> itemST, BST_Aed2<Integer, Cache> cacheSY, String path) {
        try {
            In in = new In(path);
            while (!in.isEmpty()) {
                SeparateChainingHashST<Integer, Cache> aux_cacheST = new SeparateChainingHashST<>();
                SeparateChainingHashST<Integer, TravelBug> aux_item_cacheST = new SeparateChainingHashST<>();
                //String[] split = in.readLine().split(";");
                int x = 3;
                String[] token = in.readLine().split(";");
                String aux1 = token[0]; //ID item
                String aux2 = token[1]; //nome
                String aux3 = token[2]; //istravelbug
                String aux4 = token[3]; //numero users visitados
                int j = Integer.parseInt(aux4);
                for (int i = 0; i < j; i++) { //meter os estudantes na st
                    String aux7 = token[x + i + 1]; // ALUNO X
                    //para meter no ficheiro txt as posicoes 6+alunoA+1 para adicionar sempre +1
                    Cache aux_user = cacheSY.get(Integer.parseInt(aux7));
                    aux_cacheST.put(i, aux_user);
                }
                TravelBug aux_cache = new TravelBug(Integer.parseInt(aux1), aux2, Boolean.getBoolean(aux3), aux_cacheST);
                itemST.put(Integer.parseInt(aux1), aux_cache);

                int z = Integer.parseInt(aux4);
                for (int i = 0; i < z; i++) { //associar esta turma a cada um dos estudantes
                    String aux8 = token[x + i + 1];
                    Cache aux_user = cacheSY.get(Integer.parseInt(aux8));
                    aux_item_cacheST = aux_user.getTBCACHEST();
                    aux_item_cacheST.put(aux_item_cacheST.size(), aux_cache);
                }

            }
           // System.out.println("Travel Bugs Sucessfully Loaded!\n");
        } catch (NumberFormatException e) {
            System.out.println("Not possible to Load TB!\n");
        }
    }

    /**
     * Método para carregar itens do ficheiro txt
     *
     * @param itemST ST - BSt dos itens
     * @param path   String               - caminho do ficheiro de onde se pretende ler os items
     */
    public static void loadItem(BST_Aed2<Integer, Item> itemST, String path) {
        try {
            In in = new In(path);
            while (!in.isEmpty()) {
                String[] split = in.readLine().split(";");

                int id_item = Integer.parseInt(split[0]);
                String nome = split[1];
                Boolean isTravelBug = Boolean.parseBoolean(split[2]);

                Item i = new Item(id_item, nome, isTravelBug);
                itemST.put(id_item, i);
            }
           // System.out.println("Itens Sucessfully Loaded!\n");
        } catch (NumberFormatException e) {
            System.out.println("Not possible to Load Itens!\n");
        }
    }

    /**
     * Método para imprimir itens
     *
     * @param itemST ST - BSt dos itens
     */
    public static void listarItem(BST_Aed2<Integer, Item> itemST) {
        try {
            for (Integer key : itemST.emordem()) {
                Item item = itemST.get(key);
                System.out.println("Nome: " + item.getNome() + " *" +
                        " isTravelBug?: " + item.getTravelBug() + " *" +
                        " ID_item: " + item.getId_item());

            }
        } catch (Exception e) {
            System.out.println("Not Possible To load ITEMS!\n");
        }
    }

    /**
     * Método para imprimir travel bugs
     *
     * @param itemST ST - BSt dos travel bugs
     */
    public static void listarTB(BST_Aed2<Integer, TravelBug> itemST) {
        try {
            for (Integer key : itemST.emordem()) {
                TravelBug item = itemST.get(key);

                Integer a = item.getTb_cacheST().get(0).id_cache;

                System.out.println("Nome: " + item.getNome() + " *" +
                        " isTravelBug?: true" + " *" +
                        " ID_item: " + item.getId_item() + " *" + " ID Cache: " + a
                );

            }
        } catch (Exception e) {
            System.out.println("Not Possible To load Bugs!\n");
        }
    }

    /**
     * Funcao para ir buscar a localizacao atual e ex-localizacoes dos travel bugs
     * @param itemST- St dos Travel bugs
     */
    public static void historicc_localizacoes_travel_bug(BST_Aed2<Integer, TravelBug> itemST) {
        int count = 0;
        for (Integer key : itemST.emordem()) {
            TravelBug item = itemST.get(key);
            System.out.println("\n\nNome: " + item.getNome());
            String a = item.getTb_cacheST().get(0).regiao;
            System.out.println("Localizacao atual: " + a + "\nLocalizacoes antigas: ");
            String[] c = new String[10];
            int x = item.getTb_cacheST().size();
            for (int i = 0; i <= x - 1; i++) {
                String b = item.getTb_cacheST().get(i).regiao;
                c[i] = b;
                System.out.println(c[i] + ";");
                count++;

            }
            System.out.println(count);
        }
    }

    /**
     * Método para salva itens no ficheiro TXT
     *
     * @param itemST ST - BSt dos itens
     * @param path   String  - caminho do ficheiro de onde se pretende ler os items
     */
    public static void saveItem(BST_Aed2<Integer, Item> itemST, String path) {
        try {
            Out out = new Out(path);
            for (Integer key : itemST.emordem()) {
                Item item = itemST.get(key);
                out.println(
                        item.getNome() + ";" +
                                item.getTravelBug() + ";" + item.getId_item());
            }

            System.out.println("Item saved!\n");
        } catch (Exception e) {
            System.out.println("Not Possible to save items!\n");
        }
    }

    /**
     * Método para adicionar utilizadores em cache
     *
     * @param c -  id cache
     * @param s - id user
     */
    public static boolean addUser(Cache c, Plataforma_geocaching s) {

        c.getStudent_classST().put(c.getStudent_classST().size(), s);
        s.getCachesUserST().put(s.getCachesUserST().size(), c);
        System.out.println("User inserido na cache");
        return true;
    }

    /**
     * Método para remover utilizadores em cache
     *
     * @param c -  id cache
     * @param s - id user
     */
    public static boolean removeStudent(Cache c, Plataforma_geocaching s) {

        for (int i = 0; i < c.getStudent_classST().size(); i++) {
            Plataforma_geocaching aux_student = c.getStudent_classST().get(i);
            if (aux_student == s) {
                c.getStudent_classST().delete(i);
            }
        }
        s.getCachesUserST().delete(c.getId_cache());
        return true;
    }

    /**
     * Método para imprimir utilizadores em cache
     *
     * @param c -  id cache
     */
    public static void listStudents(Cache c) {

        System.out.println("Users da cache " + c.getId_cache());
        SeparateChainingHashST<Integer, Plataforma_geocaching> aux = c.getUser_cacheST();
        for (int i = 0; i < aux.size(); i++) {
            Plataforma_geocaching aux_student = aux.get(i);
            System.out.println("ID: " + aux_student.getIdPlataforma_geocaching() + " -" +
                    " Nome: " + aux_student.getName());
        }
    }

    /**
     * Funçao que recebe a ST de item
     * vai buscar a quantidade de localizacoes de cada  travel bug e imprime o nome e nºde localizacoes na gui
     * @param itemST
     * @return
     */
    public static String historicc_localizacoes_travel_bug_gui(BST_Aed2<Integer, TravelBug> itemST) {
        StringBuilder s = new StringBuilder();
        int count = 0;
        for (Integer key : itemST.emordem()) {
            TravelBug item = itemST.get(key);
            s.append("Nome: " + item.getNome() + "\n");

            int x = item.getTb_cacheST().size();

            s.append("Localizacões: " + x + "\n");
        }
        return s.toString();
    }

    /**
     * metodo para ler os itens do ficheiro binario
     * @param itemst
     * @throws IOException
     */
    public static void loadItemBin(BST_Aed2<Integer, Item> itemst) throws IOException {

        FileInputStream f = new FileInputStream(".//data//saveitens.dat");
        DataInputStream d = new DataInputStream(f);

        for (int i=0 ;i<1;i++) {
            int id = d.readInt();
            System.out.println(id);
            String nome = d.readUTF();
            System.out.println(nome);
            boolean istb = d.readBoolean();
            System.out.println(istb);

            Item aux_item = new Item(id,nome,istb);
            itemst.put(id, aux_item);
        }
    }

    /**
     * metodo para ler os travel bugs do ficheiro binario
     * @param tbst
     * @throws IOException
     */
    public static void loadTBBin(BST_Aed2<Integer, TravelBug> tbst) throws IOException {

        FileInputStream f = new FileInputStream(".//data//savetravelbugs.dat");
        DataInputStream d = new DataInputStream(f);

        for (int i=0 ;i<2;i++) {
            int id = d.readInt();
            System.out.println(id);
            String nome = d.readUTF();
            System.out.println(nome);
            boolean istb = d.readBoolean();
            System.out.println(istb);

            TravelBug aux_tb = new TravelBug(id,nome,istb);
            tbst.put(id, aux_tb);
        }
    }
}