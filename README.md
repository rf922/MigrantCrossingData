# MigrantCrossingData

This is a project in java to read and parse data from a csv containing reported death and dissapearances along migratory routes. This data set was obtained from the following source :

- **Dataset Title**: Incidents of Death and Missing People on Migratory Routes Around the World
- **Authors**: Pariente González, V., & Sierra Santa María, X.
- **Year**: 2021
- **Source**: [Zenodo](https://zenodo.org/record/5655062)
- **DOI**: [10.5281/zenodo.5655062](https://doi.org/10.5281/zenodo.5655062)

The data consists of numerous fields such as the date, region and country. Along with this is also more details pertaining to each incident such as amount dead, missing and a description of the event. The intent of this project was to analyze and identify patterns pertaining to the deaths and dissapearence rate among different demographics of migrants. 


## Project Structure
- `src`: This directory contains the source code of the project.
- `data`: This directory contains the data set used

## Installation and Usage

## Prerequisites
- Java 17 or higher. You can check your Java version by running `java --version` in your terminal.

## Installation

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

## Compiling and Running the Project

- From the root of the source directory compile the Java files :
```
javac dataprocessing/*.java
```
- After compilation you can run the application with :
```
  java dataprocessing.DataProcessing
```
