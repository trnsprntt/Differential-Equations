package sample;

import javafx.scene.chart.XYChart;

public class exactGraph extends Graph {
    @Override
    public XYChart.Series sketch(double x0, double y0, double x, int n, double c) {
        data = new double[n];
        XYChart.Series series = new XYChart.Series();
        data[0] = Method.exactSolution(x0,c);
        double step = (x-x0)/n;
        for (int i=0;i<n;i++){
            data[i] = Method.exactSolution(x0+step*i,c);
            series.getData().add(new XYChart.Data(x0+step*i, data[i]));
        }
        series.setName("Exact Solution");
        return series;
    }
}
