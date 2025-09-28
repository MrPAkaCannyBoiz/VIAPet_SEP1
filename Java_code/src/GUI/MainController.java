package GUI;

import Model.VIAPet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import parser.ParserException;

import java.io.IOException;

public class MainController
{
    @FXML
    private Button petButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button bookingButton;
    @FXML
    private Button saleButton;
    @FXML
    private Button saveButton;

    public void changeScene(ActionEvent event) throws IOException
    {
        Button pressed = (Button) event.getSource();
        Parent pane = FXMLLoader.load(MainGUI.class.getResource(pressed.getText() + ".fxml"));

        Scene scn = new Scene(pane);

        Stage currentStage = (Stage) pressed.getScene().getWindow();

        currentStage.setScene(scn);
    }

    public void saveToFiles(ActionEvent event)
        throws IOException, ParserException
    {
        MainGUI.viaPet.saveAllToFiles();
    }
}