package gui;

import dataprocessing.MigrationDataLoader;
import dataprocessing.model.MigrationDataEntry;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String DATA_SET = "data/migrantsMissing08112021.csv";
    private static final List<MigrationDataEntry> dataList;
    
    static{
        dataList = MigrationDataLoader.loadData(DATA_SET);    
    }
    
    private String getDataListSize() {
        List<MigrationDataEntry> dataList = MigrationDataLoader.loadData(DATA_SET);
        return " [ MAIN ] : DataList Size "+dataList.size();
    }
    
        private BarChart<Number, String> createBarChart(List<MigrationDataEntry> dataList) {
        // Create Category and Value axes
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Count");

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Country");

        // Create the BarChart
        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Migration Data");

        // Prepare data series
        XYChart.Series<Number, String> series = new XYChart.Series<>();
        series.setName("Deaths");
        List<MigrationDataEntry> dr = dataList.stream().filter(x -> x.getDead() > 50).collect(Collectors.toList());
        for (MigrationDataEntry entry : dr) {
            series.getData().add(new XYChart.Data<>(entry.getDead(), entry.getIncidentCountry()));
        }

        // Add the series to the BarChart
        barChart.getData().add(series);

        return barChart;
    }
    

    @Override
    public void start(Stage primaryStage) {
        // Fetch the data list size
        //String dataListSize = getDataListSize();

        // Create a label to display the data list size
        //Label label = new Label(dataListSize);
        //label.setFont(new Font("Arial", 24));

        // Apply the CSS style
        //label.getStyleClass().add("label-style");

        // Create a layout and add the label
        StackPane centerPane = new StackPane();
        //centerPane.getChildren().add(label);
        
        BarChart<Number, String> barChart = createBarChart(dataList);
        centerPane.getChildren().add(barChart);

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menu-bar");
        
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        

        fileMenu.getStyleClass().add("menu");
        editMenu.getStyleClass().add("menu");
        helpMenu.getStyleClass().add("menu");
        
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        
        openItem.getStyleClass().add("menu-item");
        saveItem.getStyleClass().add("menu-item");
        exitItem.getStyleClass().add("menu-item");
        
        exitItem.setOnAction(e -> primaryStage.close());
        
        fileMenu.getItems().addAll(openItem, saveItem, exitItem);
        
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        
        // Status Bar
        Label statusBar = new Label("Ready");
        statusBar.getStyleClass().add("status-bar");
        
        statusBar.setMaxWidth(Double.MAX_VALUE);
        
        BorderPane root = new BorderPane();
        root.setTop(menuBar);      // Add the menu bar to the top
        root.setCenter(centerPane); // Add the main content to the center
        root.setBottom(statusBar); // Add the status bar to the bottom

        
        // Set the scene and apply a stylesheet
        Scene scene = new Scene(root, 600, 600);
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
