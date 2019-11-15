package sample;

public class RungeKuttaMethod extends Method {

    @Override
    public double calculate(double x0, double y0, double step) {
        double k1 = Method.function(x0,y0);
        double k2 = Method.function(x0+step/2,y0+step/2*k1);
        double k3 = Method.function(x0+step/2,y0+step/2*k2);
        double k4 = Method.function(x0+step,y0+step*k3);
        return 1/6.0*(k1+2*k2+2*k3+k4);
    }

    @Override
    public String getName() {
        return "RungeKutta";
    }
}
