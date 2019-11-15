package sample;

public class ImprovedEulerMethod extends Method {

    @Override
    public double calculate(double x0, double y0, double step) {
        double k1 = Method.function(x0,y0);
        double k2 = Method.function(x0+step,y0+step*k1);
        return (k1+k2)/2;
    }

    @Override
    public String getName() {
        return "Improved Euler's Method";
    }
}

