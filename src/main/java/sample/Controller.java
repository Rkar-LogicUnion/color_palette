package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private FlowPane flatFlowPane;

    @FXML
    private FlowPane materialFlowPane;

    @FXML
    private FlowPane gradientFlowPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialConnect();
        flatConnect();
        gradientConnect();
    }

    private void gradientConnect() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("jSonFile/gradientsui.json"));
            for (Object o:a){
                JSONObject flatUI=(JSONObject)o;
                String name=(String)flatUI.get("name");
                String colorCode=(String)flatUI.get("code");

                GradientColorPane colorButton=new GradientColorPane(name,colorCode);

                gradientFlowPane.setVgap(10);
                gradientFlowPane.setHgap(10);
                gradientFlowPane.getChildren().add(colorButton);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void materialConnect() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("jSonFile/materialui.json"));
            for (Object o:a){
                JSONObject flatUI=(JSONObject)o;
                String id=(String)flatUI.get("id");
                String name=(String)flatUI.get("name");
                String colorCode=(String)flatUI.get("code");

                MaterialColorPane colorButton=new MaterialColorPane(name,colorCode);

                materialFlowPane.getChildren().add(colorButton);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void flatConnect() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("jSonFile/flatui.json"));
            for (Object o:a){
                JSONObject flatUI=(JSONObject)o;
                String id=(String)flatUI.get("id");
                String name=(String)flatUI.get("name");
                String colorCode=(String)flatUI.get("code");

                FlatColorPane colorButton=new FlatColorPane(name,colorCode);

                flatFlowPane.getChildren().add(colorButton);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
