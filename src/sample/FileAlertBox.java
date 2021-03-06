package sample;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import DF.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileAlertBox {

    public boolean header;
    public String[] names;

    public Class<? extends Value>[] display(String filePath) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        ArrayList<ChoiceBox<String>> choiceBoxes = new ArrayList<>();
        for (int i = 0; i < howManyColumns(filePath); i++) {
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll("String", "Char", "Date", "Int", "Double", "Float");
            choiceBox.setValue("Double");
            choiceBoxes.add(choiceBox);
        }


        Button applyButton = new Button("Apply changes");
        applyButton.setOnAction(e -> window.close());




        VBox layout = new VBox(10);

        int counter = 1;
        for (ChoiceBox<String> i : choiceBoxes) {
            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(new Label("Column " + counter + " type:"), i);
            layout.getChildren().addAll(hBox);
            counter++;
        }

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("True");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("False");
        rb2.setToggleGroup(group);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("header: "), rb1, rb2);
        layout.getChildren().addAll(hBox);


        layout.getChildren().addAll(applyButton);


        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        counter=0;
        Class<? extends Value>[] types = (Class<? extends Value>[]) new Class<?>[choiceBoxes.size()];
        for (ChoiceBox<String> i : choiceBoxes) {
            if (i.getValue().equals("String")) {
                types[counter]=(StringValue.class);
            } else if (i.getValue().equals("Double")) {
                types[counter]=(DoubleValue.class);
            } else if (i.getValue().equals("Int")) {
                types[counter]=(IntegerValue.class);
            } else if (i.getValue().equals("Float")) {
                types[counter]=(FloatValue.class);
            } else if (i.getValue().equals("Date")) {
                types[counter]=(DateTimeValue.class);
            }
            counter=counter+1;
        }
        if (rb1.isSelected()) {
            header = true;
        } else {
            header = false;
            names = new NamesInputAlertBox().display(howManyColumns(filePath));
        }
        return types;
    }

    public int howManyColumns(String fileName) {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            line = br.readLine();
            String[] col = line.split(cvsSplitBy);
            return col.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


}