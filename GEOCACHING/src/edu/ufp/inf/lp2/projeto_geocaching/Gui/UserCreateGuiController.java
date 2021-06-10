package edu.ufp.inf.lp2.projeto_geocaching.Gui;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.lp2.projeto_geocaching.*;
import edu.ufp.inf.lp2.projeto_geocaching.Gui.Controller;
import edu.ufp.inf.lp2.projeto_geocaching.Plataforma_geocaching;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller para dar load das STS quando se muda de Window e depois retornar ao menu inicial apos as alteracoes
 */
public class UserCreateGuiController extends Controller{

    public static BST_Aed2<Integer, Plataforma_geocaching> userST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Cache> cacheT = new BST_Aed2<>();
    public static BST_Aed2<Integer, Item> itemST = new BST_Aed2<>();
    public static BST_Aed2<Integer, TravelBug> tbST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Passagem> entidadeST = new BST_Aed2<>();
    private SeparateChainingHashST<Integer, Cache> aux_cacheST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Cache> tb_cacheST = new SeparateChainingHashST<>();

    String path1 = ".//data//users.txt";
    String path2 = ".//data//item.txt";
    String path3 = ".//data//tb.txt";
    String path4 = ".//data//caches.txt";
    String path5 = ".//data//passagem.txt";


    public Button InserirUserButton;
    public Button InserirItemButton;
    public Button InserirTBButton;
    public Button InserirCacheButton;

    public TextField nomeField;
    public TextField cacheidField;
    public TextField tipoField;
    public TextField tbField;
    public TextField numitensField;
    public TextField regiaoField;
    public TextField difField;
    public TextField typeField;
    public TextField xField;
    public TextField yField;

    /**
     * funçao que e chamada sempre que se muda de ecra para levar as STs para o novo ecra
     */
    public void iniciarCampos() {
        /*Plataforma_geocaching.loadUser(userST, path1);
        Utilizador.loadCaches(cacheT,userST,path4);
        Cache.loadItem(itemST,path2);
        Cache.loadTB(tbST,cacheT,path3);
        Passagem.loadPassagem(entidadeST,cacheST,path5);

         */
        itemST = Controller.loadParamsItens();
        userST = Controller.loadParamsUsers();
        cacheT = Controller.loadParamsCaches();
        entidadeST = Controller.loadParamsPassagens();
        tbST = Controller.loadParamsTB();
    }

    /**
     * funçao para guardar as alteraçoes relativas ao novo user na ST de users
     * @param actionEvent
     * @throws IOException
     */
    public void saveUserEvent(ActionEvent actionEvent) throws IOException {
        String name = nomeField.getText();
        String tipo = tipoField.getText();

        iniciarCampos();
        Plataforma_geocaching aux_user = new Plataforma_geocaching(userST.max() + 1, name, tipo);
        userST.put(userST.max() + 1, aux_user);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Adicionado");
        alert.setHeaderText(null);
        alert.setContentText("User Adicionado com sucesso!");
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.loadParams(userST, itemST, tbST, cacheT, entidadeST);

        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) InserirUserButton.getScene().getWindow();
        //primaryStage.setTitle("UFP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *  funçao para guardar as alteraçoes relativas ao novo item na ST de items
     *
     * @throws IOException
     */
    public void saveItemEvent(ActionEvent actionEvent) throws IOException {
        String name = nomeField.getText();
        String istravel = tbField.getText();

        iniciarCampos();

        Item aux_user = new Item(itemST.max() + 1, name, Boolean.parseBoolean(istravel));
        itemST.put(itemST.max() + 1, aux_user);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Item Adicionado");
        alert.setHeaderText(null);
        alert.setContentText("Item Adicionado com sucesso!");
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.loadParams(userST, itemST, tbST,cacheT, entidadeST);

        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) InserirItemButton.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *  funçao para guardar as alteraçoes relativas ao novo Travel bug na ST de travel bugs
     * @param actionEvent
     * @throws IOException
     */
    public void saveTBEvent(ActionEvent actionEvent) throws IOException {
        String name = nomeField.getText();
        String cache = cacheidField.getText();
        Boolean istb = true;


        iniciarCampos();
        Cache aux_cache = cacheST.get(Integer.parseInt(cache));
        tb_cacheST.put(0, aux_cache);
        TravelBug aux_user = new TravelBug(tbST.max() + 1, name, istb, tb_cacheST);
        tbST.put(tbST.max() + 1, aux_user);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Travel Bug Adicionado");
        alert.setHeaderText(null);
        alert.setContentText("Travel Bug Adicionado com sucesso!");
        alert.showAndWait();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.loadParams(userST, itemST, tbST, cacheT, entidadeST);

        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) InserirTBButton.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *  funçao para guardar as alteraçoes relativas a nova cache na ST de caches
     * @param actionEvent
     * @throws IOException
     */
    public void saveCacheEvent(ActionEvent actionEvent) throws IOException {
        String tipo = typeField.getText();
        String regiao = regiaoField.getText();
        String dif = difField.getText();
        String numitens = numitensField.getText();

        float coordX = Float.parseFloat(xField.getText());
        float coordy = Float.parseFloat(yField.getText());

        iniciarCampos();
        Cache aux_user = new Cache(cacheT.max() + 1, tipo,Integer.parseInt(dif),Integer.parseInt(numitens),regiao);
        cacheT.put(cacheT.max() + 1, aux_user);

        Passagem aux_passagem = new Passagem(entidadeST.max() + 1, cacheT.max(),coordX,coordy);
        entidadeST.put(entidadeST.max() + 1, aux_passagem);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cache Adicionada");
        alert.setHeaderText(null);
        alert.setContentText("Cache Adicionada com sucesso!");
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        //controller.setDatabase(this.database);
        controller.loadParams(userST, itemST, tbST, cacheT, entidadeST);

        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) InserirCacheButton.getScene().getWindow();
        //primaryStage.setTitle("UFP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
