# MigrantCrossingData

This is a project in java to read and parse data from a csv containing reported death and dissapearances along migratory routes. This data set was obtained from the following source :

- **Dataset Title**: Incidents of Death and Missing People on Migratory Routes Around the World
- **Authors**: Pariente González, V., & Sierra Santa María, X.
- **Year**: 2021
- **Source**: [Zenodo](https://zenodo.org/record/5655062)
- **DOI**: [10.5281/zenodo.5655062](https://doi.org/10.5281/zenodo.5655062)

The data consists of numerous fields such as the date, region and country. Along with this is also more details pertaining to each incident such as amount dead, missing and a description of the event. The intent of this project was to analyze and identify patterns pertaining to the deaths and dissapearence rate among different demographics of migrants. 

## Table of Contents

1. [Project Structure](#project-structure)
2. [Installation and Usage](#installation-and-usage)
   1. [Prerequisites](#prerequisites)
   2. [Installation Steps](#installation-steps)
   3. [Compiling and Running the Project](#compiling-and-running-the-project)
   4. [Using JavaFX and JDK 22](#using-javafx-and-jdk-22)
3. [Project Overview](#project-overview)
   1. [Main Features](#main-features)
4. [Screenshots](#screenshots)

## Project Structure
- `src`: This directory contains the source code of the project.
- `data`: This directory contains the data set used
- 'resources' : This directory holds style sheets and images used in the project.

## Installation and Usage

### Prerequisites
- Java 22 or higher and JavaFX 23. You can check your Java version by running the following command:
  ```bash
  java --version
  ```
  
### Installation

1. **Clone the Repository**:
   - If you haven't already, clone this repository to your local machine using:
     ```
     git clone https://github.com/rf922/MigrantCrossingData.git
     ```
2. **Navigate to the Project Directory**:
   - Change into the project directory:
     ```
     cd MigrantCrossingData
     ```

### Compiling and Running the Project

1. **Compile the Java Files**:
   - From the root of the `src` directory, compile the Java files:
     ```bash
     javac dataprocessing/*.java
     ```
2. **Run the Application**:
   - After compilation, you can run the application with the following command:
     ```bash
     java --module-path /usr/lib/jvm/javafx-23/javafx-sdk-23/lib --add-modules javafx.controls,javafx.fxml dataprocessing.DataProcessing
     ```
### Main Features:

- **Data Loading**: The data is loaded from a CSV file and parsed into Java objects.
- **Data Visualization**: Graphs and charts are created to visualize patterns in the migrant data.
  - Bar charts to show top countries with the most deaths.
  - Stacked bar charts to compare death rates across different demographics (e.g., men, women, minors).
  - Bubble charts to visualize casualty data by year.
### Screen Shots


<p align="center">
  <img src="https://github.com/user-attachments/assets/7cc881b3-9575-43ca-9fc5-4111613b380d" alt="Bar Chart View" width="300"><br>
  <img src="https://github.com/user-attachments/assets/758b58d3-7a8e-4da0-ada1-d57d4b6c2c5c" alt="Stack Chart View" width="300">
</p>
