package sample;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;




public class Controller implements Initializable {

    public LineChart<Number, Number> lineChartGraph;
    public LineChart<Number, Number> lineChartErrors;

    public XYChart.Series exact, euler,eulerImproved, RK;

    public CheckBox checkBoxExact;
    public CheckBox checkBoxEuler;
    public CheckBox checkBoxImprovedEuler;
    public CheckBox checkBoxRK;
    public CheckBox checkBoxLocalError;
    public CheckBox checkBoxGlobalError;

    public TextField textFieldx0, textFieldy0,textFieldx,textFieldn;

    double x0,y0,x,c;
    int n;

    public Button btnSolve;

    public Label label;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnSolve.setOnAction(e -> {

            label.setVisible(false);
            lineChartGraph.getData().clear();
            lineChartErrors.getData().clear();


            ReadValues();


            if (checkPointsOfDiscontinuity(x0,x)!=0){
                label.setText("Interval has "+String.valueOf(checkPointsOfDiscontinuity(x0,x))+" points of discontinuity");
                label.setTextFill(Paint.valueOf("ff0000"));
                label.setVisible(true);
                checkBoxRK.setSelected(false);
                checkBoxEuler.setSelected(false);
                checkBoxImprovedEuler.setSelected(false);
            }
            checkBoxes();
            lineChartGraph.autosize();
            lineChartErrors.autosize();
        });

    }


    public int checkPointsOfDiscontinuity(double x0, double x){
        int l,r;
        l = (int)Math.floor((x0+Math.PI/2)/Math.PI);
        r = (int)Math.ceil((x+Math.PI/2)/Math.PI);
        return (Math.abs(r-l-1));
    }


    public boolean isDouble(TextField field){
        try{
            double data = Double.parseDouble(field.getText());
            return true;
        }catch (NumberFormatException e){
            System.out.println("Error: "+field.getText()+" is not a number\n");
            return false;
        }
    }


    public boolean isInteger(TextField field){
        try{
            int data = Integer.parseInt(field.getText());
            if (data>1&&data<200) {
                return true;
            }
            else{
                data = Math.max(1,data);
                data = Math.min(200,data);
                return true;
            }
        }catch (NumberFormatException e){
            System.out.println("Error: "+field.getText()+" is not an integer or in invalid range\n");
            return false;
        }
    }



    private void ReadValues() {
        try {
            if (textFieldx0.getText().isEmpty() || textFieldy0.getText().isEmpty() || textFieldx.getText().isEmpty()|| textFieldn.getText().isEmpty()){
                label.setText("These fields shouldn't be empty");
                label.setVisible(true);
            }
            else if (!(isDouble(textFieldx0) && isDouble(textFieldy0) && isDouble(textFieldx) && isInteger(textFieldn))){
                label.setText("Inappropriate input format");
                label.setVisible(true);
            }
            else {
                x0 = Double.parseDouble(textFieldx0.getText().replace(',', '.'));
                y0 = Double.parseDouble(textFieldy0.getText().replace(',', '.'));
                x = Double.parseDouble(textFieldx.getText().replace(',', '.'));
                n = Integer.parseInt(textFieldn.getText());
            }
        } catch (Exception exception) {
            x0 = 0;
            y0 = 1;
            x = 7;
            n = 50;
        }
        c = (y0 - Math.sin(x0))/Math.cos(x0);
    }



    public void checkBoxes(){

        if (this.checkBoxEuler.isSelected()){

            EulerGraph graph = new EulerGraph();
            euler = graph.sketch(x0,y0,x,n,c);
            lineChartGraph.getData().add(euler);

            if (this.checkBoxLocalError.isSelected()) {
                lineChartErrors.getData().add(graph.localError);
            }

            if (this.checkBoxGlobalError.isSelected()){
                lineChartErrors.getData().add(graph.globalError);
            }
        }


        if (this.checkBoxExact.isSelected()){

            exactGraph graph = new exactGraph();
            exact = graph.sketch(x0,y0,x,n,c);
            lineChartGraph.getData().add(exact);
        }


        if (this.checkBoxImprovedEuler.isSelected()){

            ImprovedEulerGraph graph = new ImprovedEulerGraph();
            eulerImproved = graph.sketch(x0,y0,x,n,c);
            lineChartGraph.getData().add(eulerImproved);

            if (this.checkBoxLocalError.isSelected()) {
                lineChartErrors.getData().add(graph.localError);
            }

            if (this.checkBoxGlobalError.isSelected()){
                lineChartErrors.getData().add(graph.globalError);
            }
        }


        if (this.checkBoxRK.isSelected()){
            RungeKuttaGraph graph = new RungeKuttaGraph();
            RK = graph.sketch(x0,y0,x,n,c);
            lineChartGraph.getData().add(RK);

            if (this.checkBoxLocalError.isSelected()) {
                lineChartErrors.getData().add(graph.localError);
            }

            if (this.checkBoxGlobalError.isSelected()){
                lineChartErrors.getData().add(graph.globalError);
            }
        }
    }
}
