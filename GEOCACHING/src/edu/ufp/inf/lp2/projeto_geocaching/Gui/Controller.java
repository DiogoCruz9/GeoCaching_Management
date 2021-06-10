package edu.ufp.inf.lp2.projeto_geocaching.Gui;

import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.projeto_geocaching.Cache;
import edu.ufp.inf.lp2.projeto_geocaching.EdgeWeightedGraph;
import edu.ufp.inf.lp2.projeto_geocaching.Item;
import edu.ufp.inf.lp2.projeto_geocaching.RedBlackBST;
import edu.ufp.inf.lp2.projeto_geocaching.TravelBug;
import edu.ufp.inf.lp2.projeto_geocaching.Utilizador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.ufp.inf.lp2.projeto_geocaching.*;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller da Interface Grafica (onde se configura os Fxml)
 */
public class Controller {

    public static BST_Aed2<Integer, Cache> cacheST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Plataforma_geocaching> userST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Item> itemST = new BST_Aed2<>();
    public static RedBlackBST<Integer, Visita> visitasst = new RedBlackBST<>();
    public static BST_Aed2<Integer, TravelBug> tbst = new BST_Aed2<>();
    public static BST_Aed2<Integer, Passagem> entidadeST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Entidade> entidadesST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Passagem> passagemST = new BST_Aed2<>();

    private SeparateChainingHashST<Integer, Cache> tb_cacheST = new SeparateChainingHashST<>();

    String path2 = ".//data//caches.txt";
    String path3 = ".//data//visitas.txt";

    @FXML
    public ImageView geocachingimageview;

    public TextField cachecaixeiroviajanteField;
    public TextField maxtempocaixeiroviajanteField;
    public TextArea cachesreachedcaixeiroviajanteArea;

    public TextField nomeField;
    public TextField tipoField;

    public TextField tipoCacheField;
    public TextField regiaoCacheField;
    public TextField dificuldadeCacheField;
    public TextField XCacheField;
    public TextField YCacheField;
    public TextField numitensCacheField;

    public TextField nomeTBField;
    public TextField tbcacheField;

    public TextField nomeItemField;
    public TextField isTBItemField;

    public TextField fromField;
    public TextField toField;

    public TextField doesntWantCacheField;
    public TextField premiumfromField;
    public TextField premiumtoField;
    public TextField doesntWantPremiumCacheField;
    public TextArea quickestRouteAreaWithoutPassingPremiumCacheArea;
    public Button seeQuickestRouteButtonWithoutPassingPremiumCaches;
    public Button showAllPremiumRoutesButton;
    public Button usersthatvisitedthiscacheButton;
    public TextArea allPremiumRoutesArea;
    public Button seeQuickestPremiumRouteButton;
    public TextArea quickestPremiumRouteArea;

    public ComboBox userComboBox;
    public ComboBox itemComboBox;
    public ComboBox tbComboBox;
    public ComboBox cacheComboBox;
    public ComboBox usercachesComboBox;
    public ComboBox cachegraphComboBox;
    public ComboBox usersthatvisitedthiscache;

    public TextArea usersthatvisitedthiscacheArea;
    public TextArea userArea;
    public TextArea itemArea;
    public TextArea tbArea;
    public TextArea cacheArea;
    public TextArea usercachesArea;
    public TextArea userNotCachesArea;
    public TextArea PremiumCacheMinObgArea;
    public TextArea TBWithMoreLocationsArea;
    public TextArea TOP5UsersWithMoreVisitedCachesArea;
    public TextArea allRoutesArea;
    public TextArea quickestRouteArea;
    public TextArea quickestRouteAreaWithoutPassingCacheArea;
    public TextArea cachegraphArea;

    public TextField dataminField;
    public TextField datamaxField;

    public Button addUser;
    public Button addItem;
    public Button addTB;
    public Button addCache;

    public Button removeUser;
    public Button removeItem;
    public Button removeTB;
    public Button removeCache;
    public Button GoButton;

    public Button editUser;
    public Button editCache;
    public Button editItem;
    public Button editTB;

    public Button ShowAllRoutesButton;
    public Button seeQuickestRouteButton;
    public Button seeQuickestRouteButtonWithoutPassingCaches;
    public Button PremiumGraphButton;

    public Group mapGraphGroup;
    public ComboBox mapComboBox;

    private double radius = 10.0;

    In map = new In(".//data//map.txt");
    EdgeWeightedGraph mapgrafo = new EdgeWeightedGraph(map);

    /**
     * Funçao que permite dar load na imagem "geo4"
     *
     * @param url
     * @param resourceBundle
     */
    /*
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File GeocachingFile = new File("C://Users//Diogo Cruz//Desktop//LP2_GEOCACHING//data//geo4.png");
        Image Geocachingimage = new Image(GeocachingFile.toURI().toString());
        geocachingimageview.setImage(Geocachingimage);
    }
    */

    /**
     * funcao que troca da primeira window para a segunda passando  de fxml Geocaching para fxml Menu
     *
     * @param event
     * @throws IOException
     */
    public void changeScreenOnButtonpush(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) GoButton.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * metodo que se chama na interface grafica no menu para salvar FILES TXT, da save em tudo nos txt.
     *
     * @param event
     */
    public void saveAllFilesTXT(ActionEvent event) {
        saveUserFileTXT(event);
        saveItemFileTXT(event);
        saveTBFileTXT(event);
        saveCacheFileTXT(event);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardar em TXT");
        alert.setHeaderText(null);
        alert.setContentText("Informação guardada com sucesso!");
        alert.showAndWait();
    }

    /**
     * metodo que vai procurar o maximo de caches que se consegue percorrer a partir de uma certa cache num limite maximo de tempo (INCOMPLETO)
     * @param event
     */
    public void caixeiroViajante(ActionEvent event) {
        mapGraphGroup.getChildren().clear();

        if (cachecaixeiroviajanteField.getText() != null) {
            for (Integer key : entidadeST.emordem()) {
                Passagem p1 = entidadeST.get(key);
                ArrayList<Passagem> list = new ArrayList<>();
                ArrayList<Passagem> list2 = new ArrayList<>();
                if (p1.getIdcache() == Integer.parseInt(cachecaixeiroviajanteField.getText())) {
                    list.add(p1);
                    String s = mapgrafo.ligacoes_diretas(entidadeST, key);
                    String[] split = s.split("\n");
                    for (int i = 0; i < split.length -1; i++) {
                        int a = Integer.parseInt(split[i]);
                        Passagem b = entidadeST.get(a);
                        Passagem nextlocal = entidadeST.get(b.getId());
                        list.add(nextlocal);
                        String s1 = mapgrafo.ligacoes_diretas(entidadeST, a);
                        String[] split1 = s1.split("\n");
                        for (int j = 0; j < split1.length -1 ; j++) {
                            int d = Integer.parseInt(split1[j]);
                            Passagem c = entidadeST.get(d);
                            Passagem nextlocal1 = entidadeST.get(c.getId());
                            list2.add(nextlocal1);

                        }
                        System.out.println(list2);
                        createGraphGroup(b, list2);
                    }

                    System.out.println(list);
                    createGraphGroup(p1, list);

                }
            }
        }
    }

    /**
     * metodo que vai chamar todos os metodos de guardar as sts em ficheiro binario
     * @param event
     * @throws IOException
     */
    public void saveAllFilesBIN(ActionEvent event) throws IOException {
        saveUserFileBIN(event);
        saveItensFileBIN(event);
        saveTBFileBIN(event);
        saveCachesFileBIN(event);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardar em BIN");
        alert.setHeaderText(null);
        alert.setContentText("Informação guardada com sucesso!");
        alert.showAndWait();
    }

    /**
     * vai guardar os users da ST  no saveusers.txt
     *
     * @param event
     */
    public void saveUserFileTXT(ActionEvent event) {

        String savepath1 = ".//data//saveusers.txt";
        Out out = new Out(savepath1);

        for (Integer key : userST.emordem()) {
            out.println(
                    userST.get(key).getIdPlataforma_geocaching() + ";"
                            + userST.get(key).getName() + ";"
                            + userST.get(key).gettipo() + ";");
        }
    }

    /**
     * vai guardar os itens ST  no saveitens.txt
     *
     * @param event
     */
    public void saveItemFileTXT(ActionEvent event) {

        String savepath1 = ".//data//saveitens.txt";
        Out out = new Out(savepath1);

        for (Integer key : itemST.emordem()) {
            out.println(
                    itemST.get(key).getId_item() + ";"
                            + itemST.get(key).getNome() + ";"
                            + itemST.get(key).getTravelBug() + ";");
        }
    }

    /**
     * vai guardar os travel bugs da ST no savetravelbugs.txt
     *
     * @param event
     */
    public void saveTBFileTXT(ActionEvent event) {

        String savepath1 = ".//data//savetravelbugs.txt";
        Out out = new Out(savepath1);

        for (Integer key : tbst.emordem()) {
            out.println(
                    tbst.get(key).getId_item() + ";"
                            + tbst.get(key).getNome() + ";"
                            + tbst.get(key).getTb_cacheST().get(0).id_cache + ";"
                            + tbst.get(key).getTb_cacheST().get(0).regiao);
        }
    }

    /**
     * vai guardar as caches da ST  no savecaches.txt
     *
     * @param event
     */
    public void saveCacheFileTXT(ActionEvent event) {

        String savepath1 = ".//data//savecaches.txt";
        Out out = new Out(savepath1);

        for (Integer key : cacheST.emordem()) {
            out.println(
                    cacheST.get(key).getId_cache() + ";"
                            + cacheST.get(key).getTipo_cache() + ";"
                            + cacheST.get(key).getDificuldade() + ";"
                            + cacheST.get(key).getNumItens() + ";"
                            + cacheST.get(key).getRegiao() + ";"
                    // + cacheST.get(key).getUserVisitedST().get(0).id_utilizador+ ";"
            );
        }
    }

    /**
     * vai guardar os users em ficheiro binario no saveusers.dat
     *
     * @param event
     * @throws IOException
     */
    public void saveUserFileBIN(ActionEvent event) throws IOException {

        // Creating binary file
        FileOutputStream fout = new FileOutputStream(".//data//saveusers.dat");
        DataOutputStream dout = new DataOutputStream(fout);

        for (Integer key : userST.emordem()) {
            dout.writeInt(userST.get(key).getIdPlataforma_geocaching());
            dout.writeUTF(userST.get(key).getName());
            dout.writeUTF(userST.get(key).gettipo());
        }

        fout.close();
        dout.close();
    }

    /**
     * Metodo para guardar os travel bugs em ficheiro binario
     * @param event
     * @throws IOException
     */
    public void saveTBFileBIN(ActionEvent event) throws IOException {
        // Creating binary file
        FileOutputStream fout = new FileOutputStream(".//data//savetravelbugs.dat");
        DataOutputStream dout = new DataOutputStream(fout);

        for (Integer key : tbst.emordem()) {
            dout.writeInt(tbst.get(key).getId_item());
            dout.writeUTF(tbst.get(key).getNome());
            dout.writeBoolean(tbst.get(key).getTravelBug());
            dout.writeInt(tbst.get(key).getTb_cacheST().get(key).id_cache);
        }

        fout.close();
        dout.close();
    }

    /**
     * Metodo para guardar os itens em ficheiro binario
     * @param event
     * @throws IOException
     */
    public void saveItensFileBIN(ActionEvent event) throws IOException {
        // Creating binary file
        FileOutputStream fout = new FileOutputStream(".//data//saveitens.dat");
        DataOutputStream dout = new DataOutputStream(fout);

        for (Integer key : itemST.emordem()) {
            dout.writeInt(itemST.get(key).getId_item());
            dout.writeUTF(itemST.get(key).getNome());
            dout.writeBoolean(itemST.get(key).getTravelBug());
        }

        fout.close();
        dout.close();
    }

    /**
     * Metodo para guardar as caches em ficheiro binario
     * @param event
     * @throws IOException
     */
    public void saveCachesFileBIN(ActionEvent event) throws IOException {
        // Creating binary file
        FileOutputStream fout = new FileOutputStream(".//data//savecaches.dat");
        DataOutputStream dout = new DataOutputStream(fout);

        for (Integer key : cacheST.emordem()) {
            dout.writeInt(cacheST.get(key).getId_cache());
            dout.writeUTF(cacheST.get(key).getTipo_cache());
            dout.writeInt(cacheST.get(key).getDificuldade());
            dout.writeInt(cacheST.get(key).getNumItens());
            dout.writeUTF(cacheST.get(key).getRegiao());
        }

        fout.close();
        dout.close();
    }

    /**
     * Metodo para dar laad de todos os ficheiros binarios e meter o conteudo nas STs e comboboxes
     * @param event
     * @throws IOException
     */
    public void readAllFilesBIN(ActionEvent event) throws IOException {
        Plataforma_geocaching.loadUserBin(userST);
        Utilizador.loadCacheBin(cacheST);
        Cache.loadItemBin(itemST);
        Cache.loadTBBin(tbst);
        addUsersComboBox();
        addCachesComboBox();
        addtbComboBox();
        addItensComboBox();
    }

    /**
     * Funcao chamada no Main Controller para se dar load da imagem do Geocaching
     */
    public void handleImageLoadInitialScreen() {
        File GeocachingFile = new File("C://Users//Diogo Cruz//Desktop//LP2_GEOCACHING//data//geo4.png");
        Image Geocachingimage = new Image(GeocachingFile.toURI().toString());
        geocachingimageview.setImage(Geocachingimage);
    }

    /**
     * Muda de window passado do Fxml Menu para o fxml utilizador quando carregamos no botao de adicionar user
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleAddUserEvent(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Utilizador.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        //UserCreateGuiController controller = loader.getController();
        //controller.setDatabase(this.database);
        //controller.iniciarCampos();

        Stage primaryStage = (Stage) addUser.getScene().getWindow();
        primaryStage.setTitle("Adicionar User");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Remove user dentro Da interface Grafica
     *
     * @param actionEvent
     */
    public void handleRemoveUserEvent(ActionEvent actionEvent) {
        Plataforma_geocaching x = null;
        for (Integer keys : userST.emordem()) {
            if (userST.get(keys).getName() == userComboBox.getValue()) {
                x = userST.get(keys);
            }
        }
        Plataforma_geocaching.removerUtilizador(userST, x.getIdPlataforma_geocaching());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remover User");
        alert.setHeaderText(null);
        alert.setContentText("User Removido com sucesso!");
        alert.showAndWait();

        userArea.clear();

        loadParams(userST, itemST, tbst, cacheST, entidadeST);
    }

    /**
     * metodo para remover Cache  da ST dentro da interface grafica
     *
     * @param actionEvent
     */
    public void handleRemoveCacheEvent(ActionEvent actionEvent) {
        Cache x = null;
        for (Integer keys : cacheST.emordem()) {
            if (cacheST.get(keys).getId_cache() == cacheComboBox.getValue()) {
                x = cacheST.get(keys);
            }
        }
        Utilizador.removerCache(cacheST, x.getId_cache());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remover Cache");
        alert.setHeaderText(null);
        alert.setContentText("Cache Removida com sucesso!");
        alert.showAndWait();

        cacheArea.clear();

        loadParams(userST, itemST, tbst, cacheST, entidadeST);
    }

    /**
     * Metodo para remover Item  da ST na interface Grafica
     *
     * @param actionEvent
     */
    public void handleRemoveItemEvent(ActionEvent actionEvent) {
        Item x = null;
        for (Integer keys : itemST.emordem()) {
            if (itemST.get(keys).getNome() == itemComboBox.getValue()) {
                x = itemST.get(keys);
            }
        }
        Cache.removerItem(itemST, x.getId_item());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remover Item");
        alert.setHeaderText(null);
        alert.setContentText("Item Removido com sucesso!");
        alert.showAndWait();

        itemArea.clear();

        loadParams(userST, itemST, tbst, cacheST, entidadeST);
    }

    /**
     * Metodo para Remover TravelBug da ST na interface Grafica
     *
     * @param actionEvent
     */
    public void handleRemoveTBEvent(ActionEvent actionEvent) {
        TravelBug x = null;
        for (Integer keys : tbst.emordem()) {
            if (tbst.get(keys).getNome() == tbComboBox.getValue()) {
                x = tbst.get(keys);
            }
        }
        Cache.removerTB(tbst, x.getId_item());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remover Travel Bug");
        alert.setHeaderText(null);
        alert.setContentText("Travel Bug removido com sucesso!");
        alert.showAndWait();

        tbArea.clear();

        loadParams(userST, itemST, tbst, cacheST, entidadeST);
    }

    /**
     * Metodo para Adicionar Cache na ST dentro da interface grafica (no popup))
     */
    public void handleAddCacheEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Caches.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        //UserCreateGuiController controller = loader.getController();
        //controller.setDatabase(this.database);
        //controller.iniciarCampos();
        Stage primaryStage = (Stage) addCache.getScene().getWindow();
        primaryStage.setTitle("Adicionar Cache");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Meetodo para adicionar Item na ST na interface Grafica no popup
     *
     * @param event
     * @throws IOException
     */
    public void handleAddItemEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) addItem.getScene().getWindow();
        primaryStage.setTitle("Adicionar Item");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metodo para Adicionar Travel Bug  na ST na interface grafica (no popup)
     *
     * @param event
     * @throws IOException
     */
    public void handleAddTBEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TravelBug.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        //UserCreateGuiController controller = loader.getController();
        //controller.setDatabase(this.database);
        //controller.iniciarCampos();
        Stage primaryStage = (Stage) addTB.getScene().getWindow();
        primaryStage.setTitle("Adicionar Travel Bug");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Vai dar load Dos ficheiros Txt na interface grafica
     *
     * @param actionEvent
     */
    public void handleLoadFilesAction(ActionEvent actionEvent) {

        String path3 = ".//data//users.txt";
        String path4 = ".//data//caches.txt";
        String path5 = ".//data//item.txt";
        String path6 = ".//data//tb.txt";
        String path7 = ".//data//visitas.txt";
        String path8 = ".//data//passagem.txt";

        //funcoes para ir buscar ao txt
        Plataforma_geocaching.loadUser(userST, path3);
        Utilizador.loadCaches(cacheST, userST, path4);
        Cache.loadItem(itemST, path5);
        Cache.loadTB(tbst, cacheST, path6);
        Passagem.loadPassagem(entidadeST, cacheST, path8);
        Entidade.loadEntidade(entidadesST, path8);

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
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());

                r.addEdge(new LigacaoMap(v, w, c1));

                //System.out.println(w + " " + v + " " + c1 + " " + c2 + " " + c3);
                line = br.readLine();
            }

            //System.out.println(r.toString());

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

        addUsersComboBox();
        addItensComboBox();
        addtbComboBox();
        addUserCachesComboBox();
        addCachesComboBox();
        addCachesGraphComboBox();
        addUsersVisitedThisCacheComboBox();

    }

    /**
     * vai preencher a combobox com o id dos users na interface grafica
     */
    public void addUsersComboBox() {
        userComboBox.getItems().clear();
        for (Integer key : userST.emordem()) {
            userComboBox.getItems().add(userST.get(key).getName());
        }
    }

    /**
     * vai preencher a combobox com o id dos caches na interface grafica
     */
    public void addCachesComboBox() {
        cacheComboBox.getItems().clear();
        for (Integer key : cacheST.emordem()) {
            cacheComboBox.getItems().add(cacheST.get(key).getId_cache());
        }
    }

    /**
     * vai preencher a combobox das caches no ecra do grafo
     */
    public void addCachesGraphComboBox() {
        cachegraphComboBox.getItems().clear();
        for (Integer key : cacheST.emordem()) {
            cachegraphComboBox.getItems().add(cacheST.get(key).getId_cache());
        }
    }

    /**
     * vai preencher a combobox com o id dos itens na interface grafica
     */
    public void addItensComboBox() {
        itemComboBox.getItems().clear();
        for (Integer key : itemST.emordem()) {
            itemComboBox.getItems().add(itemST.get(key).getNome());
        }
    }

    /**
     * vai preencher a combobox com o id dos travel bugs na interface grafica
     */
    public void addtbComboBox() {
        tbComboBox.getItems().clear();
        for (Integer key : tbst.emordem()) {
            tbComboBox.getItems().add(tbst.get(key).getNome());
        }
    }

    /**
     * vai preencher a combo box com  o  id das entidades na interface grafica
     */
    public void addEntidadeComboBox() {
        mapComboBox.getItems().clear();
        for (Integer key : entidadeST.emordem()) {
            mapComboBox.getItems().add(entidadeST.get(key).getId());
        }
    }

    /**
     * vai preencher a text area dos user referente a escolha user na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectUserAction(ActionEvent actionEvent) {
        for (Integer key : userST.emordem()) {
            Plataforma_geocaching p = userST.get(key);
            if (userComboBox.getValue().toString() == p.getName()) {
                userArea.setText(Plataforma_geocaching.print_user_gui(p));
            }
        }
    }

    /**
     * vai preencher a text area dos cacches referente a escolha cache na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectCacheAction(ActionEvent actionEvent) {
        for (Integer key : cacheST.emordem()) {
            Cache p = cacheST.get(key);
            if (cacheComboBox.getValue() == p.getId_cache()) {
                cacheArea.setText(Utilizador.print_cache_gui(p));
            }
        }
    }

    /**
     * vai preencher a text area dos cacches referente ao ecra dos grafos na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectCacheGraphAction(ActionEvent actionEvent) {
        for (Integer key : cacheST.emordem()) {
            Cache p = cacheST.get(key);
            if (cachegraphComboBox.getValue() == p.getId_cache()) {
                cachegraphArea.setText(Utilizador.print_cache_gui(p));
            }
        }
    }

    /**
     * vai preencher a text area dos itens referente a escolha item na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectItemAction(ActionEvent actionEvent) {
        for (Integer key : itemST.emordem()) {
            Item p = itemST.get(key);
            if (itemComboBox.getValue().toString() == p.getNome()) {
                itemArea.setText(Item.print_item_gui(p));
            }
        }
    }

    /**
     * vai preencher a text area dos travel Bugs referente a escolha travel bug na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectTBAction(ActionEvent actionEvent) {
        for (Integer key : tbst.emordem()) {
            TravelBug p = tbst.get(key);
            if (tbComboBox.getValue().toString() == p.getNome()) {
                tbArea.setText(TravelBug.print_tb_gui(p));
            }
        }
    }

    /**
     * Vai preencher a text area com caches premium com pelo menos um objecto na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectPremiumCachesWithMin1ObjAction(ActionEvent actionEvent) {
        PremiumCacheMinObgArea.setText(Utilizador.getPremiumWithObjects_GUI(cacheST, path2));
    }

    /**
     * Vai preencher a Text Area com o numero de localizaçoes de cada Travelbug na interface grafica
     *
     * @param actionEvent
     */
    public void handleSelectTBwithMoreLocationsAction(ActionEvent actionEvent) {
        TBWithMoreLocationsArea.setText(Cache.historicc_localizacoes_travel_bug_gui(tbst));
    }

    /**
     * vai preencher a text field com o top 5 caches visitadas
     *
     * @param actionEvent
     */
    public void handleTOP5UsersVisitedCaches(ActionEvent actionEvent) {
        String min = dataminField.getText();
        String max = datamaxField.getText();

        TOP5UsersWithMoreVisitedCachesArea.setText(Plataforma_geocaching.numero_caches_visitadas_top5_gui(min, max, path3, visitasst));
    }

    /**
     * Vai preencher as duas text areas de caches visitadas e nao visitadas do user
     *
     * @param actionEvent
     */
    public void handleSelectSearchUserVisitedCachesAction(ActionEvent actionEvent) {
        for (Integer key : userST.emordem()) {
            Plataforma_geocaching p = userST.get(key);
            if (usercachesComboBox.getValue().toString() == p.getName()) {
                usercachesArea.setText(Plataforma_geocaching.print_search_user_caches_gui(p));
                userNotCachesArea.setText(Plataforma_geocaching.caches_nao_visitadas_por_user_gui(p, cacheST, userST, path2));
            }
        }
    }

    /**
     * carrega a combobox users dado pela ST
     */
    public void addUserCachesComboBox() {
        usercachesComboBox.getItems().clear();
        for (Integer key : userST.emordem()) {
            usercachesComboBox.getItems().add(userST.get(key).getName());
        }
    }

    /**
     * metodo que vai preencher a combobox das caches na pesquisa de users que visitaram uma certa cache
     */
    public void addUsersVisitedThisCacheComboBox() {
        usersthatvisitedthiscache.getItems().clear();
        for (Integer key : cacheST.emordem()) {
            usersthatvisitedthiscache.getItems().add(cacheST.get(key).getId_cache());
        }
    }

    /**
     * metodo que vai preencher a textarea dos users na pesquisa de users que visitaram uma certa cache
     * @param event
     */
    public void handleSelectUsersThatVisitedThisCache(ActionEvent event) {
        usersthatvisitedthiscacheArea.clear();
        for (Integer key : cacheST.emordem()) {
            Cache p = cacheST.get(key);
            if (usersthatvisitedthiscache.getValue() == p.getId_cache()) {
                SeparateChainingHashST<Integer, Plataforma_geocaching> a = Utilizador.loadCachesFORGUI(cacheST, userST, path2);
                for (int i = 0; i < a.size(); i++) {
                    usersthatvisitedthiscacheArea.appendText(a.get(i).getName() + "\n");
                }
            }
        }

    }

    /**
     * Carrega a info nas combo box com as Sts
     *
     * @param aux_userST
     * @param aux_itemST
     * @param aux_tbST
     * @param aux_cacheST
     */
    public void loadParams(BST_Aed2<Integer, Plataforma_geocaching> aux_userST, BST_Aed2<Integer, Item> aux_itemST, BST_Aed2<Integer, TravelBug> aux_tbST, BST_Aed2<Integer, Cache> aux_cacheST, BST_Aed2<Integer, Passagem> aux_entidadeST) {
        userST = aux_userST;
        itemST = aux_itemST;
        tbst = aux_tbST;
        cacheST = aux_cacheST;
        entidadeST = aux_entidadeST;
        addUsersComboBox();
        addItensComboBox();
        addtbComboBox();
        //addEntidadeComboBox();
        addCachesComboBox();
        addCachesGraphComboBox();
        addUserCachesComboBox();
        addUsersVisitedThisCacheComboBox();
    }

    /**
     * metodo que serve para passar a ST de itens de um ecra para outro. Vai ser chamado por um metodo criado no USERCREATEGUICONTROLLER
     * @return
     */
    public static BST_Aed2<Integer, Item> loadParamsItens() {
        return itemST;
    }

    /**
     * metodo que serve para passar a ST de users de um ecra para outro. Vai ser chamado por um metodo criado no USERCREATEGUICONTROLLER
     * @return
     */
    public static BST_Aed2<Integer, Plataforma_geocaching> loadParamsUsers() {
        return userST;
    }

    /**
     * metodo que serve para passar a ST de caches de um ecra para outro. Vai ser chamado por um metodo criado no USERCREATEGUICONTROLLER
     * @return
     */
    public static BST_Aed2<Integer, Cache> loadParamsCaches() {
        return cacheST;
    }

    /**
     * metodo que serve para passar a ST de passagens de um ecra para outro. Vai ser chamado por um metodo criado no USERCREATEGUICONTROLLER
     * @return
     */
    public static BST_Aed2<Integer, Passagem> loadParamsPassagens() {
        return entidadeST;
    }

    /**
     * metodo que serve para passar a ST de travel bugs de um ecra para outro. Vai ser chamado por um metodo criado no USERCREATEGUICONTROLLER
     * @return
     */
    public static BST_Aed2<Integer, TravelBug> loadParamsTB() {
        return tbst;
    }

    /**
     * metodo para editar User na ST dentro da interface Grafica
     *
     * @param userst
     * @param id_user
     */
    public void editarUserGUI(BST_Aed2<Integer, Plataforma_geocaching> userst, int id_user) {
        if (userst.contains(id_user)) {
            String name = nomeField.getText();
            String tipo = tipoField.getText();

            if (name.equals("")) {
                userst.get(id_user).settipo(tipo);
            } else if (tipo.equals("")) {
                userst.get(id_user).setName(name);
            } else {
                userst.get(id_user).setName(name);
                userst.get(id_user).settipo(tipo);
            }
        }

    }

    /**
     * metodo para editar caches na ST dentro da interface Grafica
     *
     * @param cacheST - st de caches
     * @param id_cache - id da cache a editar
     * @param passagemST - st das passagens
     */
    public void editarCacheGUI(BST_Aed2<Integer, Cache> cacheST, BST_Aed2<Integer, Passagem> passagemST, int id_cache) {
        if (cacheST.contains(id_cache)) {
            String tipo = tipoCacheField.getText();
            String numitens = numitensCacheField.getText();
            String dif = dificuldadeCacheField.getText();
            String regiao = regiaoCacheField.getText();
            String coordy = YCacheField.getText();
            String coordx = XCacheField.getText();

            if (!tipo.equals("")) {
                cacheST.get(id_cache).setTipo_cache(tipo);
            } else if (!numitens.equals("")) {
                cacheST.get(id_cache).setNumItens(Integer.parseInt(numitens));
            } else if (!dif.equals("")) {
                cacheST.get(id_cache).setDificuldade(Integer.parseInt(dif));
            } else if (!regiao.equals("")) {
                cacheST.get(id_cache).setRegiao(regiao);
            } else if (!coordx.equals("")) {
                passagemST.get(id_cache).setCoordx(Float.parseFloat(coordx));
            } else if (!coordy.equals("")) {
                passagemST.get(id_cache).setCoordy(Float.parseFloat(coordy));
            } else {
                cacheST.get(id_cache).setTipo_cache(tipo);
                cacheST.get(id_cache).setNumItens(Integer.parseInt(numitens));
                cacheST.get(id_cache).setDificuldade(Integer.parseInt(dif));
                cacheST.get(id_cache).setRegiao(regiao);
                passagemST.get(id_cache).setCoordx(Float.parseFloat(coordx));
                passagemST.get(id_cache).setCoordy(Float.parseFloat(coordy));
            }
        }
    }

    /**
     * metodo para editar item na ST dentro da interface Grafica
     *
     * @param itemST
     * @param tbst - st dos travel bugs
     * @param id_item - id do item a editar
     */
    public void editarItemGUI(BST_Aed2<Integer, Item> itemST, BST_Aed2<Integer, TravelBug> tbst, int id_item) {
        if (itemST.contains(id_item)) {
            String name = nomeItemField.getText();
            String istb = isTBItemField.getText();

            if (name.equals("")) {
                if (Boolean.parseBoolean(istb)) {
                    itemST.get(id_item).setTravelBug(true);

                    Cache aux_user = cacheST.get(0);
                    tb_cacheST.put(0, aux_user);

                    TravelBug aux_tb = new TravelBug(tbst.max() + 1, itemST.get(id_item).nome, true, tb_cacheST);
                    tbst.put(tbst.max() + 1, aux_tb);

                    Cache.removerItem(itemST, id_item);
                } else {
                    itemST.get(id_item).setTravelBug(false);
                }
            } else if (istb.equals("")) {
                itemST.get(id_item).setNome(name);
            } else {
                itemST.get(id_item).setNome(name);
                if (Boolean.parseBoolean(istb)) {
                    itemST.get(id_item).setTravelBug(true);

                    Cache aux_user = cacheST.get(0);
                    tb_cacheST.put(0, aux_user);

                    TravelBug aux_tb = new TravelBug(tbst.max() + 1, name, true, tb_cacheST);
                    tbst.put(tbst.max() + 1, aux_tb);

                    Cache.removerItem(itemST, id_item);
                } else {
                    itemST.get(id_item).setTravelBug(false);
                }
            }
        }

    }

    /**
     * metodo para editar travel bug na ST dentro da interface Grafica
     *
     * @param tbst
     * @param id_tb
     */
    public void editarTBGUI(BST_Aed2<Integer, TravelBug> tbst, int id_tb) {
        if (tbst.contains(id_tb)) {
            String name = nomeTBField.getText();
            String associated_cache = tbcacheField.getText();

            if (name.equals("")) {
                tbst.get(id_tb).setId_cache(Integer.parseInt(associated_cache));
            } else if (associated_cache.equals("")) {
                tbst.get(id_tb).setNome(name);
            } else {
                tbst.get(id_tb).setNome(name);
                tbst.get(id_tb).getTb_cacheST().get(0).setId_cache(Integer.parseInt(associated_cache));
            }
        }
    }

    /**
     * vai guardar a alteraçao do editar user ao carregar no botao
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditUserEvent(ActionEvent actionEvent) throws IOException {
        Plataforma_geocaching x = null;
        for (Integer keys : userST.emordem()) {
            if (userST.get(keys).getName() == userComboBox.getValue()) {
                x = userST.get(keys);
            }
        }

        editarUserGUI(userST, x.getIdPlataforma_geocaching());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alterar User");
        alert.setHeaderText(null);
        alert.setContentText("User alterado com sucesso!");
        alert.showAndWait();

        userArea.clear();
        loadParams(userST, itemST, tbst, cacheST, entidadeST);

    }

    /**
     * vai guardar a alteraçao do editar cache ao carregar no botao
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditCacheEvent(ActionEvent actionEvent) throws IOException {
        Cache x = null;
        for (Integer keys : cacheST.emordem()) {
            if (cacheST.get(keys).getId_cache() == cacheComboBox.getValue()) {
                x = cacheST.get(keys);
            }
        }

        editarCacheGUI(cacheST, entidadeST, x.getId_cache());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alterar Cache");
        alert.setHeaderText(null);
        alert.setContentText("Cache alterada com sucesso!");
        alert.showAndWait();

        cacheArea.clear();
        loadParams(userST, itemST, tbst, cacheST, entidadeST);

    }

    /**
     * vai guardar a alteraçao do editar item ao carregar no botao
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditItemEvent(ActionEvent actionEvent) throws IOException {
        Item x = null;
        for (Integer keys : itemST.emordem()) {
            if (itemST.get(keys).getNome() == itemComboBox.getValue()) {
                x = itemST.get(keys);
            }
        }

        editarItemGUI(itemST, tbst, x.getId_item());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alterar Item");
        alert.setHeaderText(null);
        alert.setContentText("Item alterado com sucesso!");
        alert.showAndWait();

        itemArea.clear();
        loadParams(userST, itemST, tbst, cacheST, entidadeST);

    }

    /**
     * vai guardar a alteraçao do editar travel bug ao carregar no botao
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditTBEvent(ActionEvent actionEvent) throws IOException {
        TravelBug x = null;
        for (Integer keys : tbst.emordem()) {
            if (tbst.get(keys).getNome() == tbComboBox.getValue()) {
                x = tbst.get(keys);
            }
        }

        editarTBGUI(tbst, x.getId_item());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alterar Travel Bug");
        alert.setHeaderText(null);
        alert.setContentText("Travel Bug alterado com sucesso!");
        alert.showAndWait();

        tbArea.clear();
        loadParams(userST, itemST, tbst, cacheST, entidadeST);

    }

    /**
     * vai percorrer todas as passagens provenientes da ST e vai validar todas as passagens adjacentes à passagem atual
     *
     * @param actionEvent
     */
    public void handleMapGraphAction(ActionEvent actionEvent) {
        mapGraphGroup.getChildren().clear();

        //if(mapComboBox.getValue() != null) {
        for (Integer key : entidadeST.emordem()) {

            Passagem p1 = entidadeST.get(key);
            ArrayList<Passagem> list = new ArrayList<>();
            //if(p1.getNome().compareTo(mapComboBox.getValue().toString())==0) {
            list.add(p1);
            String s = mapgrafo.ligacoes_diretas(entidadeST, key);
            String[] split = s.split("\n");
            for (int i = 0; i < split.length - 1; i++) {
                int a = Integer.parseInt(split[i]);
                //for (Integer key1 : entidadeST.emordem()) {

                Passagem nextlocal = entidadeST.get(a);
                // if (nextlocal.getId() == i) {
                list.add(nextlocal);

                //}
                //}
            }
            System.out.println(list);
            createGraphGroup(p1, list);
            // }

        }
        //}
    }

    /**
     * vai percorrer todas as passagens provenientes da ST e vai validar todas as passagens premium adjacentes à passagem atual
     *
     * @param actionEvent
     */
    public void handleMapGraphPremiumAction(ActionEvent actionEvent) {
        mapGraphGroup.getChildren().clear();

        //if(mapComboBox.getValue() != null) {
        for (Integer key : entidadeST.emordem()) {
            if ((cacheST.get(key).tipo_cache).equals("Premium")) {
                Passagem p1 = entidadeST.get(key);
                ArrayList<Passagem> list = new ArrayList<>();
                //if(p1.getNome().compareTo(mapComboBox.getValue().toString())==0) {
                list.add(p1);
                String s = mapgrafo.ligacoes_diretas(entidadeST, key);
                String[] split = s.split("\n");
                for (int i = 0; i < split.length - 1; i++) {
                    int a = Integer.parseInt(split[i]);
                    //for (Integer key1 : entidadeST.emordem()) {
                    if ((cacheST.get(a).tipo_cache).equals("Premium")) {
                        Passagem nextlocal = entidadeST.get(a);
                        // if (nextlocal.getId() == i) {
                        list.add(nextlocal);
                    }
                    //}
                    //}
                }
                System.out.println(list);
                createGraphGroup(p1, list);
                // }
            }
        }
        //}
    }

    /**
     * vai desenhar As passagens Criadas no txt "map.txt" na interface grafica com as respetivas ligacoes
     *
     * @param vNumber
     * @param list
     */
    public void createGraphGroup(Passagem vNumber, ArrayList<Passagem> list) {
        Passagem init = list.get(0);

        double initialPosX = 0.0;
        double initialPosY = 0.0;
        //Random random = new Random();
        for (Passagem p : list) {
            double posX = p.getCoordx();
            double posY = p.getCoordy();
            if (p.equals(init)) {
                initialPosX = posX;
                initialPosY = posY;
            }

            String checkTypeOfCache = cacheST.get(p.getIdcache()).tipo_cache;

            Circle c = new Circle(posX, posY, radius);

            if (checkTypeOfCache.equals("Premium")) {
                c.setFill(Color.LIGHTGREEN);
            } else {
                c.setFill(Color.LIGHTPINK);
            }

            c.setId("" + p.getId());
            Text text = new Text("" + p.getId());
            text.setX(posX);
            text.setY(posY);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
            // if(!p.equals(p.getId())) {
            Line line = new Line(initialPosX, initialPosY, posX, posY);
            line.setStroke(Color.RED);
            mapGraphGroup.getChildren().add(line);
            //}
            mapGraphGroup.getChildren().addAll(c, text);
        }
    }

    /**
     * metodo para mostrar na textarea, todas as rotas disponiveis entre caches
     * @param actionEvent
     */
    public String handleShowAllRoutes(ActionEvent actionEvent) {
        StringBuilder s = new StringBuilder();
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
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());

                r.addEdge(new LigacaoMap(v, w, c1));

                line = br.readLine();
            }

            allRoutesArea.setText(String.valueOf(r));

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

        return s.toString();
    }

    /**
     * metodo para mostrar na textarea, todas as rotas disponiveis entre caches premium
     * @param actionEvent
     */
    public String handleShowAllPremiumRoutes(ActionEvent actionEvent) {
        StringBuilder s = new StringBuilder();
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
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());
                if ((cacheST.get(v).tipo_cache).equals("Premium") && (cacheST.get(w).tipo_cache).equals("Premium")) {
                    r.addEdge(new LigacaoMap(v, w, c1));
                }
                line = br.readLine();
            }

            allPremiumRoutesArea.setText(String.valueOf(r));

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

        return s.toString();
    }

    /**
     * metodo para mostrar na textarea, o caminho mais curto entre 2 caches
     * @param actionEvent
     */
    public void showQuickestRouteBetween2Caches(ActionEvent actionEvent) {

        BufferedReader br = null;
        Rede r = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/network.txt")));
            int V = Integer.parseInt(br.readLine());
            r = new Rede(V);
            int E = Integer.parseInt(br.readLine());

            String line = br.readLine();
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());

                r.addEdge(new LigacaoMap(v, w, c1));

                line = br.readLine();
            }

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

        int from = Integer.parseInt(fromField.getText());
        int to = Integer.parseInt(toField.getText());
        quickestRouteArea.clear();

        StringBuilder sb = new StringBuilder();

        DijkstraSP sp = new DijkstraSP(r, from);

        Entidade local_from = entidadesST.get(from);
        int nome_local_from = local_from.getNomeEntidade(local_from, entidadeST);
        Entidade local_to = entidadesST.get(to);
        int nome_local_to = local_to.getNomeEntidade(local_to, entidadeST);

        // print shortest path
        if (sp.hasPathTo(to)) {

            //sb.append(nome_local_from).append(" to ").append(nome_local_to).append(":\n\n");
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {
                    Entidade s1 = entidadesST.get(c.from());
                    int nome_local_s1 = s1.getNomeEntidade(s1, entidadeST);
                    Entidade s2 = entidadesST.get(c.to());
                    int nome_local_s2 = s2.getNomeEntidade(s2, entidadeST);
                    // sb.append(nome_local_s1).append(" > ").append(nome_local_s2).append("\n");
                    //quickestRouteArea.setText(String.valueOf(nome_local_s1) + " > " + String.valueOf(nome_local_s2) + "\n");
                    quickestRouteArea.appendText(String.valueOf(nome_local_s1) + " > " + String.valueOf(nome_local_s2) + "\n");
                }
            }
            //sb.append("\nTotal distance - ").append(String.format("%.2f", sp.distTo(to))).append("metros ");
        } else {
            //sb.append(local_from.getId()).append(" to ").append(local_to.getId()).append("\t has no path");
        }

        //  return sb.toString();
    }

    /**
     * metodo para mostrar na textarea, o caminho mais curto entre 2 caches premium
     * @param actionEvent
     */
    public void showQuickestRouteBetween2PremiumCaches(ActionEvent actionEvent) {

        BufferedReader br = null;
        Rede r = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/network.txt")));
            int V = Integer.parseInt(br.readLine());
            r = new Rede(V);
            int E = Integer.parseInt(br.readLine());

            String line = br.readLine();
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());

                if ((cacheST.get(v).tipo_cache).equals("Premium") && (cacheST.get(w).tipo_cache).equals("Premium")) {
                    r.addEdge(new LigacaoMap(v, w, c1));
                }
                line = br.readLine();
            }

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

        int from = Integer.parseInt(premiumfromField.getText());
        int to = Integer.parseInt(premiumtoField.getText());
        quickestPremiumRouteArea.clear();

        StringBuilder sb = new StringBuilder();

        DijkstraSP sp = new DijkstraSP(r, from);

        Entidade local_from = entidadesST.get(from);
        int nome_local_from = local_from.getNomeEntidade(local_from, entidadeST);
        Entidade local_to = entidadesST.get(to);
        int nome_local_to = local_to.getNomeEntidade(local_to, entidadeST);

        // print shortest path
        if (sp.hasPathTo(to)) {

            //sb.append(nome_local_from).append(" to ").append(nome_local_to).append(":\n\n");
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {
                    Entidade s1 = entidadesST.get(c.from());
                    int nome_local_s1 = s1.getNomeEntidade(s1, entidadeST);
                    Entidade s2 = entidadesST.get(c.to());
                    int nome_local_s2 = s2.getNomeEntidade(s2, entidadeST);
                    // sb.append(nome_local_s1).append(" > ").append(nome_local_s2).append("\n");
                    //quickestRouteArea.setText(String.valueOf(nome_local_s1) + " > " + String.valueOf(nome_local_s2) + "\n");
                    quickestPremiumRouteArea.appendText(String.valueOf(nome_local_s1) + " > " + String.valueOf(nome_local_s2) + "\n");
                }
            }
            //sb.append("\nTotal distance - ").append(String.format("%.2f", sp.distTo(to))).append("metros ");
        } else {
            //sb.append(local_from.getId()).append(" to ").append(local_to.getId()).append("\t has no path");
        }

        //  return sb.toString();
    }

    /**
     * metodo para mostrar na textarea, o caminho mais curto entre 2 caches sem passar por 1 certa cache
     * @param actionEvent
     */
    public void showQuickestRouteBetween2CachesWithoutCacheX(ActionEvent actionEvent) {
        int dontWant = Integer.parseInt(doesntWantCacheField.getText());
        int from = Integer.parseInt(fromField.getText());
        int to = Integer.parseInt(toField.getText());

        BufferedReader br = null;
        Rede r = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/network.txt")));
            int V = Integer.parseInt(br.readLine());
            r = new Rede(V);
            int E = Integer.parseInt(br.readLine());

            String line = br.readLine();
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());

                if (v == dontWant || w == dontWant) {
                    r.addEdge(new LigacaoMap(v, w, 1000000));
                } else {
                    r.addEdge(new LigacaoMap(v, w, c1));
                }
                line = br.readLine();
            }

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

        quickestRouteAreaWithoutPassingCacheArea.clear();

        DijkstraSP sp = new DijkstraSP(r, from);

        // print shortest path
        if (sp.hasPathTo(to)) {
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {
                    if (c.weight() > 100000) {

                    } else {
                        Entidade s1 = entidadesST.get(c.from());
                        int nome_local_s1 = s1.getNomeEntidade(s1, entidadeST);
                        Entidade s2 = entidadesST.get(c.to());
                        int nome_local_s2 = s2.getNomeEntidade(s2, entidadeST);
                        quickestRouteAreaWithoutPassingCacheArea.appendText(String.valueOf(nome_local_s1) + " > " + String.valueOf(nome_local_s2) + "\n");
                    }
                }
            }
            //quickestRouteAreaWithoutPassingCacheArea.appendText("\nTotal Distance: " + String.format("%.2f", sp.distTo(to)) + " metros \n");
        } else {
            quickestRouteAreaWithoutPassingCacheArea.appendText("There's no path");
        }
    }

    /**
     * metodo para mostrar na textarea, o caminho mais curto entre 2 caches premium sem passar por 1 certa cache
     * @param actionEvent
     */
    public void showQuickestRouteBetween2PremiumCachesWithoutCacheX(ActionEvent actionEvent) {
        int dontWant = Integer.parseInt(doesntWantPremiumCacheField.getText());
        int from = Integer.parseInt(premiumfromField.getText());
        int to = Integer.parseInt(premiumtoField.getText());

        BufferedReader br = null;
        Rede r = null;
        try {
            br = new BufferedReader(new FileReader(new File("data/network.txt")));
            int V = Integer.parseInt(br.readLine());
            r = new Rede(V);
            int E = Integer.parseInt(br.readLine());

            String line = br.readLine();
            while (line != null) {

                StringTokenizer st = new StringTokenizer(line);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                double c1 = Double.parseDouble(st.nextToken());
                if ((cacheST.get(v).tipo_cache).equals("Premium") && (cacheST.get(w).tipo_cache).equals("Premium")) {

                    if (v == dontWant || w == dontWant) {
                        r.addEdge(new LigacaoMap(v, w, 1000000));
                    } else {
                        r.addEdge(new LigacaoMap(v, w, c1));
                    }
                }
                line = br.readLine();
            }

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

        quickestRouteAreaWithoutPassingPremiumCacheArea.clear();

        DijkstraSP sp = new DijkstraSP(r, from);

        // print shortest path
        if (sp.hasPathTo(to)) {
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {
                    if (c.weight() > 100000) {

                    } else {
                        Entidade s1 = entidadesST.get(c.from());
                        int nome_local_s1 = s1.getNomeEntidade(s1, entidadeST);
                        Entidade s2 = entidadesST.get(c.to());
                        int nome_local_s2 = s2.getNomeEntidade(s2, entidadeST);
                        quickestRouteAreaWithoutPassingPremiumCacheArea.appendText(String.valueOf(nome_local_s1) + " > " + String.valueOf(nome_local_s2) + "\n");
                    }
                }
            }
            //quickestRouteAreaWithoutPassingCacheArea.appendText("\nTotal Distance: " + String.format("%.2f", sp.distTo(to)) + " metros \n");
        } else {
            quickestRouteAreaWithoutPassingPremiumCacheArea.appendText("There's no path");
        }
    }

    /**
     * metodo para mostrar na tab About a informacao dos criadores do projeto
     * @param actionEvent
     */
    public void handleAcercaEvent(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca!");
        alert.setHeaderText(null);
        alert.setContentText("Programa realizado por:\n\n\tDiogo Cruz\n\n\tPedro Ramalho\n\nUniversidade Fernando Pessoa\nEngenharia Informática\nLP2 & AED2");
        alert.showAndWait();
    }

}
