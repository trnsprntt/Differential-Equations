package sample;
public abstract class Method {

    //numerical method
    public abstract double calculate(double x0, double y0,double step);

    //get name of method
    public abstract String getName();

    static double function(double x, double y) {
        return 1/Math.cos(x) - y*Math.tan(x); // given function of first derivative
    }

    static double exactSolution(double x, double c) {
        return Math.sin(x)+c*Math.cos(x); // partial solution with given C
    }
}

