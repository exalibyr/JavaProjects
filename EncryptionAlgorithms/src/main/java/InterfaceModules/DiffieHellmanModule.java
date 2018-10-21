package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;

public class DiffieHellmanModule extends Module {

    private JTextField p;
    private JLabel g;
    private JLabel xa;
    private JLabel xb;
    private JLabel ya;
    private JLabel yb;
    private JLabel zab;
    private JLabel zba;

    public DiffieHellmanModule(JPanel panel){
        super(panel);

        p = new JTextField("simple number p");
        p.setPreferredSize(new Dimension(100, 25));
        panel.add(p);

        g = new JLabel("g=?");
        g.setPreferredSize(new Dimension(100, 25));
        panel.add(g);

        xa = new JLabel("xa=?");
        xa.setPreferredSize(new Dimension(100, 25));
        panel.add(xa);

        xb = new JLabel("xb=?");
        xb.setPreferredSize(new Dimension(100, 25));
        panel.add(xb);

        ya = new JLabel("ya=?");
        ya.setPreferredSize(new Dimension(100, 25));
        panel.add(ya);

        yb = new JLabel("yb=?");
        yb.setPreferredSize(new Dimension(100, 25));
        panel.add(yb);

        zab = new JLabel("zab=?");
        zab.setPreferredSize(new Dimension(100, 25));
        panel.add(zab);

        zba = new JLabel("zba=?");
        zba.setPreferredSize(new Dimension(100, 25));
        panel.add(zba);
    }


    protected void calculate(){
            int[] results = Logic.diffieHellman(Integer.parseInt(p.getText()));
            g.setText("g=" + String.valueOf(results[0]));
            xa.setText("xa=" + String.valueOf(results[1]));
            xb.setText("xb=" + String.valueOf(results[2]));
            ya.setText("ya=" + String.valueOf(results[3]));
            yb.setText("yb=" + String.valueOf(results[4]));
            zab.setText("zab=" + String.valueOf(results[5]));
            zba.setText("zba=" + String.valueOf(results[6]));
    }
}
