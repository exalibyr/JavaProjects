package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;


public class FermatFactorizationModule extends Module {

    private JTextField n;
    private JLabel ans;

    public FermatFactorizationModule(JPanel panel){
        super(panel);

        n = new JTextField("Odd number n>2");
        n.setPreferredSize(new Dimension(120, 25));
        panel.add(n);

        ans = new JLabel("answer");
        ans.setPreferredSize(new Dimension(250, 25));
        panel.add(ans);
    }

    protected void calculate(){
            int[] results = Logic.fermatFactorization(Integer.parseInt(n.getText()));
            ans.setText(String.valueOf(results[0]) + "*" + String.valueOf(results[1]));
    }
}
