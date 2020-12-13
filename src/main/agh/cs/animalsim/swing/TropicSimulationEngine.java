package agh.cs.animalsim.swing;

import agh.cs.animalsim.IWorldMap;
import agh.cs.animalsim.TropicMap;

import javax.swing.*;
import java.awt.*;

public class TropicSimulationEngine  {
    private final TropicPainter painter;
    private JMenuBar menuBar;
    private IWorldMap map;

    public TropicSimulationEngine(TropicMap map, int numberOfHerbivores, int numberOfCarnivores, int amountOfGrass){
        painter = new TropicPainter(map, numberOfHerbivores, numberOfCarnivores, amountOfGrass);
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Simulation");
        JMenuItem startMenuItemStop = new JMenuItem("Pause");
        JMenuItem startMenuItemGo = new JMenuItem("Run");
        menuBar.add(fileMenu);
        fileMenu.add(startMenuItemStop);
        startMenuItemStop.setActionCommand("stop");
        startMenuItemStop.addActionListener(painter);
        fileMenu.add(startMenuItemGo);
        startMenuItemGo.setActionCommand("start");
        startMenuItemGo.addActionListener(painter);
        this.map = map;
    }

    public void start(){
        painter.setLowerLeft(map.lowerLeftCorner());
        painter.setUpperRight(map.upperRightCorner());
        JFrame f=new JFrame("Evolution");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setSize(map.upperRightCorner().x-map.lowerLeftCorner().x + 130,
                map.upperRightCorner().y-map.lowerLeftCorner().y + 150 + 20);
        f.add(painter);

        f.setJMenuBar(menuBar);
        f.setLocationRelativeTo(null);

        f.setVisible(true);
    }



}