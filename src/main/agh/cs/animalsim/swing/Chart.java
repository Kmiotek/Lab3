package agh.cs.animalsim.swing;

import agh.cs.animalsim.ILifeObserver;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Chart extends JPanel implements ILifeObserver{
    protected XYChart chart;
    protected TropicSimulationEngine engine;

    protected ArrayList<String> seriesNames;
    protected ArrayList< ArrayList<Double> > axisX;
    protected ArrayList< ArrayList<Double> > axisY;

    XChartPanel<XYChart> chartPane;

    String title;


    public Chart(TropicSimulationEngine engine, String title, String[] seriesNames, double[][] initialDataX, double[][] initialDataY) {
        axisX = new ArrayList<>();
        axisY = new ArrayList<>();
        this.seriesNames = new ArrayList<>();
        this.title = title;
        for (int s = 0;s<seriesNames.length; s++) {
            ArrayList<Double> listX = new ArrayList<>();
            ArrayList<Double> listY = new ArrayList<>();
            for (int i =0;i<initialDataX[s].length;i++){
                listX.add(initialDataX[s][i]);
                listY.add(initialDataY[s][i]);
            }
            axisX.add(listX);
            axisY.add(listY);
            this.seriesNames.add(seriesNames[s]);
        }

        this.engine = engine;

        chart = new XYChartBuilder().width(600).height(725).title(title).build();
        addToChart();
        chartPane = new XChartPanel<>(chart);
        add(chartPane);

    }


    protected void addToChart(){
        for (int i = 0; i < seriesNames.size(); i++) {
            chart.addSeries(seriesNames.get(i), toPrimitive(axisX.get(i).toArray(new Double[0])),
                    toPrimitive(axisY.get(i).toArray(new Double[0])));
        }
    }

    protected void updateChart() {
        for (int i = 0; i < seriesNames.size(); i++) {
            chart.updateXYSeries(seriesNames.get(i), toPrimitive(axisX.get(i).toArray(new Double[0])),
                    toPrimitive(axisY.get(i).toArray(new Double[0])), null);
        }
        repaint();
    }

    private double[] toPrimitive(Double[] array){
        double[] result = new double[array.length];
        for (int i =0;i<array.length;i++){
            result[i] = array[i];
        }
        return result;
    }


}
