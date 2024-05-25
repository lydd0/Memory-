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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Classifica implements Initializable {
    @FXML
    private Button btnCre;

    @FXML
    private Button btnDec;

    @FXML
    private Label lblClass;

    @FXML
    private Label lblEl;

     @FXML
     private Button btnMen;

    private ArrayList<Game> s1;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String s, nome;
        FileReader f;
        BufferedReader fIn;
        StringTokenizer st;
        double tempo;
        int tent;
        Game a;
        try {
            lblEl.setVisible(false);
            f = new FileReader("classifica.txt");
            fIn = new BufferedReader(f);
            s = fIn.readLine();
            s1 = new ArrayList<Game>();
            while(s != null)
            {
                st = new StringTokenizer(s,";");
                nome = st.nextToken();
                tempo = Double.parseDouble(st.nextToken());
                tent = Integer.parseInt(st.nextToken());
                a = new Game(nome,tempo,tent);
                s1.add(a);
                s = fIn.readLine();
            }
            f.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        btnCre.setOnAction(event -> cre());
        btnDec.setOnAction(event -> deCre());
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
    private void cre()
    {
        lblEl.setText("");
        lblEl.setVisible(true);
        Collections.sort(s1);
        for(int i = 0; i < s1.size(); i++)
        {
            lblEl.setText(lblEl.getText() + s1.get(i).toString());
        }
    }
    private void deCre()
    {
        lblEl.setText("");
        lblEl.setVisible(true);
        Collections.reverse(s1);
        for(int i = 0; i < s1.size(); i++)
        {
            lblEl.setText(lblEl.getText() + s1.get(i).toString());
        }
    }
}
