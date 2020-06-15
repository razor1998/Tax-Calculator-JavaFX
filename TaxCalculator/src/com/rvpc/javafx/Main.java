package com.rvpc.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        VBox rootNode = loader.load();

        controller = loader.getController();
        MenuBar menubar = createMenu();
        rootNode.getChildren().add(0,menubar);

        Scene scene = new Scene(rootNode);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Tool");
        // primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar createMenu() {
        Menu filemenu = new Menu("File");
        MenuItem newmenu = new MenuItem("New");

        newmenu.setOnAction(event -> {
            controller.newtab();
        });
        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem reset = new MenuItem("Reset");
        reset.setOnAction(event -> {
            controller.resettab();
        });
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem quitmenu = new MenuItem("Quit");

        quitmenu.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        filemenu.getItems().addAll(newmenu, sep, reset, separatorMenuItem, quitmenu);

        Menu helpmenu = new Menu("Help");
        MenuItem aboutmenu = new MenuItem("Info");

        aboutmenu.setOnAction(event -> aboutapp());

        helpmenu.getItems().addAll(aboutmenu);

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(filemenu, helpmenu);

        return menubar;
    }

    private void aboutapp() {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("JavaFX Application");
        alertDialog.setHeaderText("Mini Tax Calculator v1.0");
        alertDialog.setContentText("This is a tool to calculate \nSimple Interest & Compound Interest.\nAuthor: rvpc\nMail: rvpcsite@gmail.com");
        alertDialog.show();
    }
}
