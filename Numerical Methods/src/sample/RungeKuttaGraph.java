package sample;

import javafx.scene.chart.XYChart;

public class RungeKuttaGraph extends Graph{
    RungeKuttaMethod method = new RungeKuttaMethod();
    public XYChart.Series localError = new XYChart.Series<Double,Double>();;
    public XYChart.Series globalError = new XYChart.Series<Double,Double>();;

    @Override
    public XYChart.Series sketch(double x0, double y0, double x, int n, double c) {
        data = new double[n];
        XYChart.Series series = new XYChart.Series<Double,Double>();
        localError.getData().add(new XYChart.Data(x0,0));
        globalError.getData().add(new XYChart.Data(x0,0));
        data[0] = Method.exactSolution(x0,c);
        series.getData().add(new XYChart.Data(x0,data[0]));
        double step = (x-x0)/n;
        for (int i=1;i<n;i++){
            data[i] = data [i-1] + step*method.calculate(x0+step*(i-1),data[i-1],step); //calculating value obtained by Runge-Kutta method
            series.getData().add(new XYChart.Data(x0+step*i, data[i]));
            //globalError.getData().add(new XYChart.Data(x0+step*i,Method.exactSolution(x0+step*i,c)-data[i])); //calculating global error
            localError.getData().add(new XYChart.Data(x0+step*i,Math.abs(Method.exactSolution(x0+step*i,c)-
                    Method.exactSolution(x0+step*(i-1),c)-step*method.calculate(x0+step*(i-1),data[i-1],step)))); //calculating local error
        }
        for(int i = 10; i <= 30; i++)
        {
            double Y;
            Y=y0;
            double maxError = 0;
            double step_current = (x-x0) / i;
            for (double j=1; j <= i; j++) {
                Y = Y + step_current*method.calculate(x0+step_current*(j-1),Y,step_current);
                double error = Math.abs(Y - Method.exactSolution((x0 + step_current*j),c));
                if(maxError < error) maxError = error;
            }

            globalError.getData().add(new XYChart.Data<>((double)i,maxError));
        }

        series.setName("Runge-Kutta method");
        localError.setName("Runge-Kutta method local error");
        globalError.setName("Runge-Kutta method global error");
        return series;
    }
}
