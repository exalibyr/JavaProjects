package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;


public class ElgamalModule extends Module {

    private JTextField message;
    private JTextField p;
    private JLabel db;
    private JLabel g;
    private JLabel cb;
    private JLabel k;
    private JLabel r;
    private JLabel e;
    private JLabel receivedMessage;

    public ElgamalModule(JPanel panel){
        super(panel);

        message = new JTextField("message");
        message.setPreferredSize(new Dimension(100, 25));
        panel.add(message);

        p = new JTextField("simple number p");
        p.setPreferredSize(new Dimension(100, 25));
        panel.add(p);

        g = new JLabel("g=?");
        g.setPreferredSize(new Dimension(100, 25));
        panel.add(g);

        cb = new JLabel("cb=?");
        cb.setPreferredSize(new Dimension(100, 25));
        panel.add(cb);

        db = new JLabel("db=?");
        db.setPreferredSize(new Dimension(100, 25));
        panel.add(db);

        k = new JLabel("k=?");
        k.setPreferredSize(new Dimension(100, 25));
        panel.add(k);

        r = new JLabel("r=?");
        r.setPreferredSize(new Dimension(100, 25));
        panel.add(r);

        e = new JLabel("e=?");
        e.setPreferredSize(new Dimension(100, 25));
        panel.add(e);

        receivedMessage = new JLabel("received message=?");
        receivedMessage.setPreferredSize(new Dimension(300, 25));
        panel.add(receivedMessage);
    }

    protected void calculate(){
            int[] results = Logic.elgamal(Integer.parseInt(message.getText()),
                                            Integer.parseInt(p.getText()));
            g.setText("g=" + String.valueOf(results[0]));
            cb.setText("cb=" + String.valueOf(results[1]));
            db.setText("db=" + String.valueOf(results[2]));
            k.setText("k=" + String.valueOf(results[3]));
            r.setText("r=" + String.valueOf(results[4]));
            e.setText("e=" + String.valueOf(results[5]));
            receivedMessage.setText("received message=" + String.valueOf(results[6]));

    }
}
