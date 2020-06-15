package com.rvpc.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label welcomeLabel;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public TextField principal;
    @FXML
    public TextField rate;
    @FXML
    public TextField year;
    @FXML
    public Button calculate;

    private static final String si = "Simple Interest";
    private static final String ci = "Compound Interest";

    private boolean issi = true;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        choiceBox.getItems().add(si);
        choiceBox.getItems().add(ci);

        choiceBox.setValue(si);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(si)) {
                issi=true;
            }
            else {
                issi=false;
            }
        });

        calculate.setOnAction(event -> {
            calculate.setDisable(true);
            convert();
        });
    }

    private void convert() {
        String p = principal.getText();
        String r = rate.getText();
        String t = year.getText();
        float temp1 = 0.0f;
        float temp2 = 0.0f;
        float temp3 = 0.0f;
        try {
            temp1 = Float.parseFloat(p);
            temp2 = Float.parseFloat(r);
            temp3 = Float.parseFloat(t);
        }
        catch (Exception ae){
            warnUser();
            return;
        }
        float result=0.0f;
        if(issi){
            result=(float) (temp1*temp2*temp3/100);
        }
        else {
            result= (float) (temp1*(Math.pow((1 + temp2 / 100), temp3)));
        }
        display(result);
    }

    private void warnUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occured");
        alert.setHeaderText("Invalid Values Entered");
        alert.setContentText("Please Enter a valid Values");
        alert.show();
    }

    private void display(float result) {
        String type = issi? "Simple Interest- " : "Compound Interest- ";
        System.out.println(type+result);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Results");
        alert.setHeaderText("Mini Tax Calculator v1.0");
        alert.setContentText(type+result);
        alert.show();
    }

    public void newtab(){
        if(calculate.isDisabled())
        {
            choiceBox.setValue(si);
            principal.clear();
            rate.clear();
            year.clear();
            calculate.setDisable(false);
        }
    }

    public void resettab(){
        if(calculate.isDisabled())
        {
            principal.clear();
            rate.clear();
            year.clear();
            calculate.setDisable(false);
        }
    }
}