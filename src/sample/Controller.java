package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import DF.*;


public class Controller {
    public ListView listView;
    private DataFrame df = null;
    private DataFrame chartDf;
    private String[] groupby;


    public void onMaxClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {

            String result = df.groupby(groupby).max().asString();
            Label label = new Label(result);
            listView.getItems().addAll(label);

        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }

    public void onMinClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {

            String result = df.groupby(groupby).min().asString();
            Label label = new Label(result);
            listView.getItems().addAll(label);

        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }

    public void onVarClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {

            String result = df.groupby(groupby).var().asString();
            Label label = new Label(result);
            listView.getItems().addAll(label);

        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }

    public void onSumClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {

            String result = df.groupby(groupby).sum().asString();
            Label label = new Label(result);
            listView.getItems().addAll(label);

        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }

    public void onStdClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {

            String result = df.groupby(groupby).std().asString();
            Label label = new Label(result);
            listView.getItems().addAll(label);

        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }

    public void onMeanClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {

            String result = df.groupby(groupby).mean().asString();
            Label label = new Label(result);
            listView.getItems().addAll(label);

        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }

    public void onFileClick() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            FileAlertBox fileAlertBox = new FileAlertBox();
            Class<? extends Value>[] types = fileAlertBox.display(selectedFile.getAbsolutePath());
            boolean header = fileAlertBox.header;
            System.out.println(header);
            if (header) {
                df = new DataFrame(selectedFile.getAbsolutePath(), types);
                chartDf = new DataFrame(selectedFile.getAbsolutePath(), types);
            } else {
                listView.getItems().addAll(new Label("You have to choose a file to proceed"));
            }
        }
    }

    public void onChartClick(){
        if (chartDf != null) {
            if (chartDf.columns.size() >= 2) {
                    listView.getItems().clear();
                    String[] names = new ChartAlertBox().display(chartDf);
                    listView.getItems().addAll(new DrawChart().display(names, chartDf));
            } else {
                    listView.getItems().addAll(new Label("DataFrame too small"));
            }
        } else {
                listView.getItems().addAll(new Label("You have to generate DataFrame first"));
        }
    }

    public void onGroupbyClick() {
        listView.getItems().clear();
        if (df != null) {
            groupby = new GroupbyAlertBox().display(df);
        } else {
            listView.getItems().addAll(new Label("You have to create DataFrame first"));
        }
    }



}
