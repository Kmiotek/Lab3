package agh.cs.animalsim.swing;

import agh.cs.animalsim.TropicMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPainter extends JPanel implements ActionListener {

    protected JButton start;
    protected JSpinner energySpinner;
    protected JSpinner sizeSpinner;
    protected JSpinner speedSpinner;
    protected JSpinner meatQualitySpinner;

    protected JSpinner numberOfAnimalsSpinner;
    protected JSpinner grassPerTickSpinner;

    protected JSpinner sizeXSpinner;
    protected JSpinner sizeYSpinner;

    public ClientPainter(){

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        SpinnerModel model7 =
                new SpinnerNumberModel(800, 0, 10000, 10);
        sizeXSpinner = addLabeledSpinner("Map Width", model7);
        sizeXSpinner.setEditor(new JSpinner.NumberEditor(sizeXSpinner, "#"));

        SpinnerModel model8 =
                new SpinnerNumberModel(900, 0, 10000, 10);
        sizeYSpinner = addLabeledSpinner("Map Height", model8);
        sizeYSpinner.setEditor(new JSpinner.NumberEditor(sizeYSpinner, "#"));

        SpinnerModel model5 =
                new SpinnerNumberModel(10, 0, 1000, 1);
        numberOfAnimalsSpinner = addLabeledSpinner("Number of Herbivores", model5);
        numberOfAnimalsSpinner.setEditor(new JSpinner.NumberEditor(numberOfAnimalsSpinner, "#"));

        SpinnerModel model6 =
                new SpinnerNumberModel(0.1, 0, 100, 0.01);
        grassPerTickSpinner = addLabeledSpinner("Added Grass per Tick", model6);
        grassPerTickSpinner.setEditor(new JSpinner.NumberEditor(grassPerTickSpinner, "#.##"));

        SpinnerModel model =
                new SpinnerNumberModel(50000, 0, 1000000, 10);
        energySpinner = addLabeledSpinner("Initial Energy", model);
        energySpinner.setEditor(new JSpinner.NumberEditor(energySpinner, "#"));

        SpinnerModel model2 =
                new SpinnerNumberModel(10, 0, 1000, 1);
        sizeSpinner = addLabeledSpinner("Initial Size", model2);
        sizeSpinner.setEditor(new JSpinner.NumberEditor(sizeSpinner, "#"));

        SpinnerModel model3 =
                new SpinnerNumberModel(10, 0, 1000, 1);
        speedSpinner = addLabeledSpinner("Initial Speed", model3);
        speedSpinner.setEditor(new JSpinner.NumberEditor(speedSpinner, "#"));

        SpinnerModel model4 =
                new SpinnerNumberModel(3000, 0, 100000, 10);
        meatQualitySpinner = addLabeledSpinner("Meat Quality", model4);
        meatQualitySpinner.setEditor(new JSpinner.NumberEditor(meatQualitySpinner, "#"));

        start = new JButton("Start new simulation");
        start.setActionCommand("start");
        start.addActionListener(this);
        add(start);

    }

    protected JSpinner addLabeledSpinner(String label, SpinnerModel model) {
        JLabel l = new JLabel(label);
        this.add(l);

        JSpinner spinner = new JSpinner(model);
        l.setLabelFor(spinner);
        this.add(spinner);

        return spinner;
    }



    public void actionPerformed(ActionEvent event) {
        if ("start".equals(event.getActionCommand())) {
            try {
                energySpinner.commitEdit();
                sizeSpinner.commitEdit();
                speedSpinner.commitEdit();
                meatQualitySpinner.commitEdit();
                numberOfAnimalsSpinner.commitEdit();
                grassPerTickSpinner.commitEdit();
                sizeXSpinner.commitEdit();
            } catch (java.text.ParseException e) {
                System.out.println("Couldn't update value of spinner");
                System.out.println(e.getLocalizedMessage());
            }
            TropicMap map = new TropicMap((Integer) sizeXSpinner.getValue(), (Integer) sizeXSpinner.getValue(), 400, 450);
            TropicSimulationEngine engine = new TropicSimulationEngine(map, (Integer) numberOfAnimalsSpinner.getValue(),
                    0, (Double) grassPerTickSpinner.getValue(),
                    (Integer) sizeSpinner.getValue(), (Integer) speedSpinner.getValue(), (Integer) energySpinner.getValue(),
                    (Integer) meatQualitySpinner.getValue());
            engine.start();
        }
    }

}
