package agh.cs.animalsim.swing;

import agh.cs.animalsim.ILifeObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChartPanel extends JPanel implements ActionListener {
    Chart numberChart;
    Chart emptyChart;

    JButton chart1;
    JButton chart2;

    JPanel charts;

    int current = 1;

    public ChartPanel(int numberOfHerbivores, int numberOfCarnivores, TropicSimulationEngine engine){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        numberChart = new QuantityChart(engine, "Herbivores and carnivores", new double[]{numberOfHerbivores},
                new double[]{numberOfCarnivores});
        emptyChart = new SizeSpeedChart(engine, "Average speed and size", new double[][]{new double[]{10}, new double[]{10}},
                numberOfHerbivores+numberOfCarnivores);

        JPanel buttons = new JPanel();
        chart1 = new JButton("Quantities");
        chart1.setActionCommand("1");
        chart1.addActionListener(this);
        buttons.add(chart1);

        chart2 = new JButton("Traits");
        chart2.setActionCommand("2");
        chart2.addActionListener(this);
        buttons.add(chart2);

        charts = new JPanel();
        charts.add(numberChart);
        add(buttons);
        add(charts);

    }

    public ArrayList<ILifeObserver> getChartsAsObservers(){
        ArrayList<ILifeObserver> ret = new ArrayList<>();
        ret.add(numberChart);
        ret.add(emptyChart);
        return ret;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("1".equals(e.getActionCommand())){
            if (current != 1){
                current = 1;
                charts.removeAll();
                charts.add(numberChart);
                revalidate();
                repaint();
            }
        } else if ("2".equals(e.getActionCommand())){
            if (current != 2){
                current = 2;
                charts.removeAll();
                charts.add(emptyChart);
                revalidate();
                repaint();
            }
        }
    }
}
