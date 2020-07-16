package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;

public class MaterialColorPane extends BorderPane {
    JFXButton button=new JFXButton();
    JFXButton moreBtn=new JFXButton("More");
    String text,code;
    public MaterialColorPane(String text, String code){
        this.text=text;
        this.code=code;
        this.setPrefWidth(290);
        this.setPrefHeight(200);
        this.setCenter(button);
        this.setBottom(moreBtn);

        button.setText(text);
        button.setPrefWidth(290);
        button.setPrefHeight(160);
        button.setStyle("-fx-background-color:"+code+";-fx-font: 24 Consolas;-fx-border-radius:0px;-fx-background-radius:0px;-fx-text-fill:#fff;");

        moreBtn.setPrefWidth(290);
        moreBtn.setPrefHeight(40);
        moreBtn.setStyle("-fx-background-color:"+code+"9f;-fx-font: 14 Consolas;-fx-border-radius:0px;-fx-background-radius:0px;-fx-text-fill:#fff;");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StringSelection stringSelection = new StringSelection(code);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
                JFXSnackbar bar = new JFXSnackbar(MaterialColorPane.this);
                bar.show("Copied",1000);
            }
        });

        moreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FlowPane subFlowPane=new FlowPane();
                ScrollPane scrollPane=new ScrollPane();
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(true);
                scrollPane.setContent(subFlowPane);
                Scene scene=new Scene(scrollPane,600,600);
                scene.getStylesheets().add("sample/style.css");
                Stage subStage=new Stage();
                subStage.setTitle(text+" (Developed by LogicUnion)");
                subStage.setScene(scene);
                subStage.setY(150);
                subStage.show();
                subMaterialConnect(subFlowPane,text);
            }
        });

    }
    public void subMaterialConnect(FlowPane subFlowPane,String text){
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("jSonFile/submaterialui.json"));
            for (Object o:a){
                JSONObject flatUI=(JSONObject)o;

                String parent=(String)flatUI.get("parent");
                String name=(String)flatUI.get("name");
                String colorCode=(String)flatUI.get("code");

                if (parent.equals(text)) {
                    FlatColorPane colorButton = new FlatColorPane(name, colorCode);

                    subFlowPane.getChildren().add(colorButton);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
