/**
 * File : Main.java
 */
package gui;

import dataprocessing.MigrationDataLoader;
import dataprocessing.MigrationDataUtils;
import dataprocessing.model.MigrationDataEntry;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String DATA_SET = "data/migrantsMissing08112021.csv";
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final List<MigrationDataEntry> dataList;

    static {
        dataList = MigrationDataLoader.loadData(DATA_SET);
    }

    private BubbleChart<Number, Number> createBubbleChart(List<MigrationDataEntry> dataList) {
        // Axes for the Bubble Chart
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Total Survivors");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Dead");

        BubbleChart<Number, Number> bubbleChart = new BubbleChart<>(xAxis, yAxis);
        bubbleChart.setTitle("Incident Casualties by Year (Bubble Size: Missing)");

        // Group data by year
        Map<Integer, List<MigrationDataEntry>> dataByYear = dataList.stream()
            .collect(Collectors.groupingBy(x -> x.getDate().getYear())); 
        
        // Create a series for each year
        for (Map.Entry<Integer, List<MigrationDataEntry>> yearEntry : dataByYear.entrySet()) {
            int year = yearEntry.getKey();
            List<MigrationDataEntry> yearlyData = yearEntry.getValue();

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(String.valueOf(year)); // Name the series by year

            for (MigrationDataEntry entry : yearlyData) {
                int totalDead = entry.getDead();
                int totalSurvivors = entry.getSurvivors();
                int totalMissing = entry.getMissing();

                if (totalDead > 0 || totalSurvivors > 0 || totalMissing > 0) {
                    XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(totalSurvivors, totalDead, totalMissing);
                    series.getData().add(dataPoint);

                    dataPoint.nodeProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                            // Add hover tooltip
                            Tooltip tooltip = new Tooltip(
                                "Year: " + year + "\n"
                                + "Survivors: " + totalSurvivors + "\n"
                                + "Deaths: " + totalDead + "\n"
                                + "Missing: " + totalMissing
                            );
                            Tooltip.install(newValue, tooltip);

                            // Add click event
                            newValue.setOnMouseClicked(event -> {
                                System.out.println("Clicked on bubble:");
                                System.out.println("Year: " + year);
                                System.out.println("Survivors: " + totalSurvivors);
                                System.out.println("Deaths: " + totalDead);
                                System.out.println("Missing: " + totalMissing);
                            });
                        }
                    });
                }
            }

            // Add the series to the chart
            bubbleChart.getData().add(series);
        }

        return bubbleChart;
    }

    private BarChart<Number, String> createBarChart(List<MigrationDataEntry> dataList) {
        // Create Category and Value axes
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Number of Deaths");

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Country");

        // Create the BarChart
        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Top 10 Countries with Most Deaths");

        // Prepare data series
        XYChart.Series<Number, String> series = new XYChart.Series<>();
        series.setName("Deaths");

        Map<String, List<MigrationDataEntry>> incidentsByCountry
            = MigrationDataUtils.groupByField(dataList, MigrationDataEntry::getIncidentCountry);

        Map<String, Integer> deathsByCountry = incidentsByCountry.keySet()
            .stream()
            .collect(Collectors.toMap(
                country -> country,
                country -> incidentsByCountry.get(country).stream()
                    .map(MigrationDataEntry::getDead)
                    .reduce(0, Integer::sum)
            ));
//        for (MigrationDataEntry entry : topDeaths) {
//            series.getData().add(new XYChart.Data<>(entry.getDead(), entry.getIncidentCountry()));
//        }

        List<Map.Entry<String, Integer>> top10Deaths = deathsByCountry.entrySet()
            .stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort in descending order
            .limit(10) // Take the top 10
            .collect(Collectors.toList());

        // Add data to the series
        for (Map.Entry<String, Integer> entry : top10Deaths) {
            series.getData().add(new XYChart.Data<>(entry.getValue(), entry.getKey()));
        }

        // Add the series to the BarChart
        barChart.getData().add(series);

        return barChart;
    }

    private BarChart<Number, String> createStackedBarChart(List<MigrationDataEntry> dataList) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Number of Deaths");

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Category");

        BarChart<Number, String> stackedBarChart = new BarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("Death Rates by Demographic");

        XYChart.Series<Number, String> womenSeries = new XYChart.Series<>();
        womenSeries.setName("Women");
        XYChart.Series<Number, String> menSeries = new XYChart.Series<>();
        menSeries.setName("Men");
        XYChart.Series<Number, String> minorsSeries = new XYChart.Series<>();
        minorsSeries.setName("Minors");

        // Aggregate data
        int totalWomenDeaths = dataList.stream().mapToInt(MigrationDataEntry::getWomen).sum();
        int totalMenDeaths = dataList.stream().mapToInt(MigrationDataEntry::getMen).sum();
        int totalMinorsDeaths = dataList.stream().mapToInt(MigrationDataEntry::getMinors).sum();

        // Add data to series
        womenSeries.getData().add(new XYChart.Data<>(totalWomenDeaths, "Women"));
        menSeries.getData().add(new XYChart.Data<>(totalMenDeaths, "Men"));
        minorsSeries.getData().add(new XYChart.Data<>(totalMinorsDeaths, "Minors"));

        // Add series to the chart
        stackedBarChart.getData().addAll(womenSeries, menSeries, minorsSeries);

        return stackedBarChart;
    }

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.getIcons().add(new javafx.scene.image.Image("/resources/images/icon.png"));
        
        // Fetch the data list size
        //String dataListSize = getDataListSize();

        // Create a label to display the data list size
        //Label label = new Label(dataListSize);
        //label.setFont(new Font("Arial", 24));
        // Apply the CSS style
        //label.getStyleClass().add("label-style");
        // Create a layout and add the label
        //StackPane centerPane = new StackPane();
        //centerPane.getChildren().add(label);
        BarChart<Number, String> barChart = createBarChart(dataList);
        BarChart<Number, String> stackedBarChart = createStackedBarChart(dataList);
        //BubbleChart<Number, Number> bubbleChart = createBubbleChart(dataList);

        //centerPane.getChildren().add(barChart);
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

        TabPane tabPane = new TabPane();
        Tab barChartTab = new Tab("Top 10 Deaths", barChart);
//        Tab pieChartTab = new Tab("Death Rates by Demographic", pieChart);
        Tab stackedBarChartTab = new Tab("Demographic Comparison", stackedBarChart);
        //Tab bubbleChartTab = new Tab("Bubble Chart", bubbleChart);

        //bubbleChartTab.setClosable(false);
        barChartTab.setClosable(false);
//        pieChartTab.setClosable(false);
        stackedBarChartTab.setClosable(false);

        tabPane.getTabs().addAll(barChartTab, stackedBarChartTab);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);      // Add the menu bar to the top
        root.setCenter(tabPane); // Add the main content to the center
        root.setBottom(statusBar); // Add the status bar to the bottom

        // Set the scene and apply a stylesheet
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
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
