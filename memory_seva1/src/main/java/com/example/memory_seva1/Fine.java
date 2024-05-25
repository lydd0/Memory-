package com.example.memory_seva1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Fine implements Initializable {
    @FXML
    private Button btnSalva;

    @FXML
    private Label lblFine;

    @FXML
    private Button btnMen;

    @FXML
    private Label lblTemp;

    @FXML
    private Label lblTent;

    private String nome;
    private double time;
    private int tentativi;
    private boolean salvato = false;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void End(String username, double time, int tent) {
        nome = username;
        this.time = time;
        tentativi = tent;
    }

    private void Salva() {
        if (!salvato) {
            lblTemp.setText("Il tuo tempo è: " + time);
            lblTent.setText("I tuoi tentativi sono: " + tentativi);
            lblTemp.setVisible(true);
            lblTent.setVisible(true);
            FileWriter f;
            PrintWriter fOut;
            try {
                f = new FileWriter("classifica.txt", true);
                fOut = new PrintWriter(f);
                fOut.println(nome + ";" + time + ";" + tentativi + ";");
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            salvato = true;
        }
    }

    public void ret(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        root = loader.load();
        Start scene2Controller = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSalva.setOnAction(event -> Salva());
        lblTemp.setVisible(false);
        lblTent.setVisible(false);
    }
}
