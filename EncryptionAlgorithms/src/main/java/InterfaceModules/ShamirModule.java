package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;


public class ShamirModule extends Module {

    private JTextField message;
    private JTextField p;
    private JLabel ca;
    private JLabel cb;
    private JLabel da;
    private JLabel db;
    private JLabel x1;
    private JLabel x2;
    private JLabel x3;
    private JLabel receivedMessage;

    public ShamirModule(JPanel panel){
        super(panel);

        message = new JTextField("message");
        message.setPreferredSize(new Dimension(100, 25));
        panel.add(message);

        p = new JTextField("simple number p");
        p.setPreferredSize(new Dimension(100, 25));
        panel.add(p);

        ca = new JLabel("ca=?");
        ca.setPreferredSize(new Dimension(100, 25));
        panel.add(ca);

        cb = new JLabel("cb=?");
        cb.setPreferredSize(new Dimension(100, 25));
        panel.add(cb);

        da = new JLabel("da=?");
        da.setPreferredSize(new Dimension(100, 25));
        panel.add(da);

        db = new JLabel("db=?");
        db.setPreferredSize(new Dimension(100, 25));
        panel.add(db);

        x1 = new JLabel("x1=?");
        x1.setPreferredSize(new Dimension(100, 25));
        panel.add(x1);

        x2 = new JLabel("x2=?");
        x2.setPreferredSize(new Dimension(100, 25));
        panel.add(x2);

        x3 = new JLabel("x3=?");
        x3.setPreferredSize(new Dimension(100, 25));
        panel.add(x3);

        receivedMessage = new JLabel("received message=?");
        receivedMessage.setPreferredSize(new Dimension(300, 25));
        panel.add(receivedMessage);
    }

    protected void calculate(){
            int[] results = Logic.shamir(Integer.parseInt(message.getText()),
                    Integer.parseInt(p.getText()));
            ca.setText("ca=" + String.valueOf(results[0]));
            cb.setText("cb=" + String.valueOf(results[1]));
            da.setText("da=" + String.valueOf(results[2]));
            db.setText("db=" + String.valueOf(results[3]));
            x1.setText("x1=" + String.valueOf(results[4]));
            x2.setText("x2=" + String.valueOf(results[5]));
            x3.setText("x3=" + String.valueOf(results[6]));
            receivedMessage.setText("received message=" + String.valueOf(results[7]));

    }
}
