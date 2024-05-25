package com.example.memory_seva1;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class x3 implements Initializable {

    @FXML
    private GridPane Grid2x3;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;
    private ArrayList<Cell> celle;

    private boolean card2 = false;

    private int win = 0, index1, pos1;

    private String perc, nome;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private long startTime;

    private int tentativi = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTime = System.nanoTime();
        celle = new ArrayList<>();
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/int.jpg")));
        img1.setImage(img);
        img2.setImage(img);
        img3.setImage(img);
        img4.setImage(img);
        img5.setImage(img);
        img6.setImage(img);
        for(int i = 0; i < 2;i++)
        {
            for(int z = 0; z < 3; z++) {
                celle.add(new Cell("boh",i,z));
            }
        }
        insPerc();
        addClickHandler(img1);
        addClickHandler(img2);
        addClickHandler(img3);
        addClickHandler(img4);
        addClickHandler(img5);
        addClickHandler(img6);
    }
    public void displayName(String username) {
        nome = username;
    }
    private void addClickHandler(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            Integer colIndex = Grid2x3.getColumnIndex(imageView);
            Integer rowIndex = Grid2x3.getRowIndex(imageView);
            if (colIndex != null && rowIndex != null) {
                try {
                    click(colIndex, rowIndex, event);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("L'ImageView non è stato aggiunto correttamente alla GridPane");
                System.out.println(Grid2x3.getColumnIndex(imageView));
                System.out.println(Grid2x3.getRowIndex(imageView));
            }
        });
    }
    private void click(int col, int row, EventObject event) throws InterruptedException //RITARDO DI 3 SECONDI DA METTERE
    {
        int index = 0, pos = -1;
        if(!card2) {
            for (int i = 0; i < 6 && pos == -1; i++) {
                if (celle.get(i).compareRowandCol(col, row)) {
                    pos = i;
                }
            }
            index = ( row * 2 + col);
            if (!celle.get(pos).isGirata()) {
                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(celle.get(index).getPercorso())));
                perc = celle.get(index).getPercorso();
                index1 = index;
                pos1 = pos;
                switchCase(index,img);
                card2 = true;
            }
        }
        else
        {
            tentativi ++;
            for (int i = 0; i < 6 && pos == -1; i++) {
                if (celle.get(i).compareRowandCol(col, row)) {
                    pos = i;
                }
            }
            index = (row * 2 + col);
            if (!celle.get(pos).isGirata()) {
                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(celle.get(index).getPercorso())));
                switchCase(index, img);
                if (!verify(celle.get(index).getPercorso())) {
                    Image imgBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/int.jpg")));
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    int finalIndex = index;
                    pause.setOnFinished(event1 -> {
                        switchCase(finalIndex, imgBack);
                        switchCase(index1, imgBack);
                    });
                    pause.play();
                }
                else
                {
                    celle.get(pos).setGirata(true);
                    celle.get(pos1).setGirata(true);
                    if(win == 3)
                    {
                        long endTime = System.nanoTime();
                        double time = (double) (endTime - startTime) / 1_000_000_000.0;
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.setOnFinished(event1 -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("fine.fxml"));
                                root = loader.load();
                                Fine scene2Controller = loader.getController();
                                scene2Controller.End(nome,time,tentativi);
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }catch (Exception e)
                            {
                                System.out.println("errore");
                            }
                        });
                        pause.play();
                    }
                }
            }
            card2 = false;
        }
    }
    void switchCase(int index,Image img)
    {
        switch (index) {
            case 0:
                img1.setImage(img);
                break;

            case 1:
                img2.setImage(img);
                break;

            case 2:
                img3.setImage(img);
                break;

            case 3:
                img4.setImage(img);
                break;

            case 4:
                img5.setImage(img);
                break;

            case 5:
                img6.setImage(img);
                break;
        }
    }
    private boolean verify(String percorso)
    {
        if(percorso.equals(perc))
        {
            win ++;
            return true;
        }
        else
        {
            return false;
        }
    }
    private void insPerc()
    {
        celle.get(0).setPercorso("/juve.png");
        celle.get(1).setPercorso("/juve.png");
        celle.get(2).setPercorso("/brescia.png");
        celle.get(3).setPercorso("/brescia.png");
        celle.get(4).setPercorso("/fiorentina.jpg");
        celle.get(5).setPercorso("/fiorentina.jpg");
        Collections.shuffle(celle);
    }
}
