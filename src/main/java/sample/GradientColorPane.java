package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GradientColorPane extends BorderPane {
    JFXButton button=new JFXButton();
    String text,code;
    public GradientColorPane(String text, String code){
        this.text=text;
        this.code=code;
        this.setPrefWidth(580);
        this.setPrefHeight(200);
        this.setCenter(button);

        button.setText(text);
        button.setPrefWidth(580);
        button.setPrefHeight(200);
        button.setStyle("-fx-background-color:linear-gradient(to left, "+code+");-fx-font: 24 Consolas;-fx-border-radius:0px;-fx-background-radius:0px;-fx-text-fill:#fff;");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StringSelection stringSelection = new StringSelection(code);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
                JFXSnackbar bar = new JFXSnackbar(GradientColorPane.this);
                bar.show("Copied",1000);
            }
        });
    }
}
