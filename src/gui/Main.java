package gui;

import dataprocessing.MigrationDataLoader;
import dataprocessing.model.MigrationDataEntry;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String DATA_SET = "data/migrantsMissing08112021.csv";
    
    private String getDataListSize() {
        List<MigrationDataEntry> dataList = MigrationDataLoader.loadData(DATA_SET);
        return " [ MAIN ] : DataList Size "+dataList.size();
    }

    @Override
    public void start(Stage primaryStage) {
        // Fetch the data list size
        String dataListSize = getDataListSize();

        // Create a label to display the data list size
        Label label = new Label(dataListSize);
        label.setFont(new Font("Arial", 24));

        // Apply the CSS style
        label.getStyleClass().add("label-style");

        // Create a layout and add the label
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Set the scene and apply a stylesheet
        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(getClass().getResource("/resources/css/style.css").toExternalForm());

        // Set the stage
        primaryStage.setTitle("Data Processing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
