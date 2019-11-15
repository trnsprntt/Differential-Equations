package sample;

import javafx.scene.chart.*;


public abstract class Graph {

    double[] data; // to store values on each step of calculations
    public abstract XYChart.Series sketch (double x0, double y0, double x, int n, double c); //sketch the graph

}
