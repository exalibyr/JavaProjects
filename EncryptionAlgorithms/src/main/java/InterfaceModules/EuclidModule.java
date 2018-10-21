package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;


public class EuclidModule extends Module {
    private JTextField a;
    private JTextField b;
    private JLabel greatestCommonDivider;
    private JLabel x;
    private JLabel y;

    public EuclidModule(JPanel panel){
        super(panel);

        a = new JTextField("a");
        a.setPreferredSize(new Dimension(50, 25));
        panel.add(a);

        b = new JTextField("b");
        b.setPreferredSize(new Dimension(50, 25));
        panel.add(b);

        greatestCommonDivider  = new JLabel("GCD=?");
        greatestCommonDivider.setPreferredSize(new Dimension(100, 25));
        panel.add(greatestCommonDivider);

        x = new JLabel("x=?");
        x.setPreferredSize(new Dimension(100, 25));
        panel.add(x);

        y = new JLabel("y=?");
        y.setPreferredSize(new Dimension(100, 25));
        panel.add(y);
    }

    protected void calculate(){
        int ans[] = Logic.euclid(Integer.parseInt(this.a.getText()), Integer.parseInt(this.b.getText()));
        greatestCommonDivider.setText("GCD=" + String.valueOf(ans[0]));
        this.x.setText(String.valueOf("x=" + ans[1]));
        this.y.setText(String.valueOf("y=" + ans[2]));
    }
}
