package GUI;

import Model.VIAPet;
import Utils.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainGUI extends Application
{
    public static VIAPet viaPet;

    static
    {
        try
        {
            viaPet = new VIAPet();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Override public void start(Stage primaryStage) throws IOException
    {
        primaryStage.setTitle("VIAPets");
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("MainPane.fxml"));

        Scene scene = new Scene(loader.load());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
