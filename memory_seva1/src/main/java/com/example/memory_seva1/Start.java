package com.example.memory_seva1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Start implements Initializable{

    @FXML
    private Button BtnStart;
    @FXML
    private Label LblStart;

    @FXML
    private TextField TxtName;

    @FXML
    private Button btnClass;

    @FXML
    private Label lblError;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView img1;

    @FXML
    private ChoiceBox<String> ChcGame;

    private String[] scelte = {"       3 X 4","       2 X 3","       5 X 4"};

    private String s;

    @FXML
    private Label lblErrorGame;
    public void Login(ActionEvent event) throws IOException {
        lblErrorGame.setText("");
        lblError.setText("");
        if(TxtName.getText().compareTo("") == 0)
        {
            lblError.setTextFill(Color.rgb(255,0,0));
            lblError.setText(" errore inserire il nome");
        }
        else {
            if(s == null)
            {
                lblErrorGame.setTextFill(Color.rgb(255,0,0));
                lblErrorGame.setText("errore inserire una modalità");
            }
            else {
                if (s.compareTo("       3 X 4") == 0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    root = loader.load();
                    HelloController scene2Controller = loader.getController();
                    scene2Controller.displayName(TxtName.getText());
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    if (s.compareTo("       2 X 3") == 0) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("2x3.fxml"));
                        root = loader.load();
                        x3 scene2Controller = loader.getController();
                        scene2Controller.displayName(TxtName.getText());
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("4x5.fxml"));
                        root = loader.load();
                        x4 scene2Controller = loader.getController();
                        scene2Controller.displayName(TxtName.getText());
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
        }
    }
    public void Classifica(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("classifica.fxml"));
            root = loader.load();
            Classifica scene2Controller = loader.getController();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChcGame.getItems().addAll(scelte);
        ChcGame.setOnAction(this::getGame);
        ChcGame.setValue("       3 X 4");

    }

    public void getGame(ActionEvent event)
    {
         s = ChcGame.getValue();
    }
}
