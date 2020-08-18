/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namegenerator;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;

/**
 *
 * @author Michael
 */
public class GraphicInterface extends Application {
    
    @Override
    public void start(Stage window) {
        window.setTitle("Name Generator 2.0");
        
        BorderPane grid = new BorderPane();
        grid.setPrefHeight(200);
        
        Label outputLabel = new Label();
        grid.setCenter(outputLabel);
        Label titleLabel = new Label("Click on a button to generate a name!");
        grid.setTop(titleLabel);
        
        HBox buttonsFirstRow = new HBox();
        buttonsFirstRow.setSpacing(10);
        
        Button hobbitName = new Button("Hobbit name");
        hobbitName.setOnAction((event) -> {
            outputLabel.setText(NameGenerator.generateBloodBowlHalflingName());
            titleLabel.setText("Hobbit name:");
        });
        buttonsFirstRow.getChildren().add(hobbitName);
        
        Button teenName = new Button("Finnish teen name");
        teenName.setOnAction((event) -> {
            outputLabel.setText(NameGenerator.generateFinnishTeenName());
            titleLabel.setText("Finnish teen name:");
        });
        buttonsFirstRow.getChildren().add(teenName);
        
        Button imperialName = new Button("40k male Imperial name");
        imperialName.setOnAction((event) -> {
           outputLabel.setText(NameGenerator.generateMaleImperialName());
           titleLabel.setText("40k male Imperial name:");
        });
        buttonsFirstRow.getChildren().add(imperialName);
        
  
        HBox buttonsSecondRow = new HBox();
        buttonsSecondRow.setSpacing(10);
        
        Button darkElfLinemanName = new Button("Dark Elf lineman name");
        darkElfLinemanName.setOnAction((event) -> {
           outputLabel.setText(NameGenerator.generateBloodBowlDarkElfLinemanName());
           titleLabel.setText("Dark Elf lineman name:");
        });
        buttonsSecondRow.getChildren().add(darkElfLinemanName);
        
        Button darkElfBlitzerName = new Button("Dark Elf blitzer name");
        darkElfBlitzerName.setOnAction((event) -> {
           outputLabel.setText(NameGenerator.generateBloodBowlDarkElfBlitzerName());
           titleLabel.setText("Dark Elf blitzer name:");
        });
        buttonsSecondRow.getChildren().add(darkElfBlitzerName);
        
        Button mamboName = new Button("Mambo!");
        mamboName.setOnAction((event) -> {
           titleLabel.setText("Mambo n:o 5");
           outputLabel.setText(NameGenerator.generateMamboName());
        });
        buttonsSecondRow.getChildren().add(mamboName);
        
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        
        buttons.getChildren().add(buttonsFirstRow);
        buttons.getChildren().add(buttonsSecondRow);
        
        grid.setBottom(buttons);
        
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
        
    }
    
}
