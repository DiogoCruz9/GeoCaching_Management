package edu.ufp.inf.lp2.projeto_geocaching.Gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * carrega a primeira window da interface grafica
 */
public class MainController extends Application {
  //  private static Database database;

    public static void start_gui(String[] args) {
        //database = db;
        launch(args);
    }

    /**
     * Faz load Da primeira window da interface grafica
     * @param primaryStage
     * @throws IOException
     */
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Geocaching.fxml"));
        Parent root = loader.load();
        Controller guiController = loader.getController();
        guiController.handleImageLoadInitialScreen();

        Scene scene=new Scene(root);
        primaryStage.setTitle("Geocaching APP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
