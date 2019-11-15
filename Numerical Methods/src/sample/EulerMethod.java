package sample;

public class EulerMethod extends Method {

    @Override
    public double calculate(double x0, double y0, double step) { //numerical approximation by Euler's method
        return function(x0,y0);
    }

    @Override
    public String getName() {
        return "Euler's Method";
    }
}
