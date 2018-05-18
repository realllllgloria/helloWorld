import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleMenu extends JFrame implements ActionListener {
    JPanel firstpane, pp, p1, p2, p3, p4;
    JButton jb1, jb2, jb3;
    JLabel L2;

    public PuzzleMenu() {

        pp = (JPanel) getContentPane();
        pp.setBackground(Color.PINK);
        pp.setLayout(new GridLayout(4, 3));
        jb1 = new JButton("Puzzles");
        jb2 = new JButton("Options");
        jb3 = new JButton("Exit");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        pp.add(jb1);
        pp.add(jb2);
        pp.add(jb3);

    }

    public static void main(String[] args) {
        PuzzleMenu f = new PuzzleMenu();
        f.setTitle("Start");
        f.setSize(1280, 720);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) { //***********************************
        if (e.getSource() == jb1) {
            this.dispose();
            new second();
        }
        if (e.getSource() == jb3) {
            System.exit(0);
        }


    }
}


class second extends JFrame implements ActionListener {
    JPanel p;
    JButton b1, b2, b3;

    public second() {
        p = new JPanel();
        p.setBackground(Color.PINK);
        p.setLayout(new GridLayout(4, 3));
        b1 = new JButton("Easy");
        b2 = new JButton("Medium");
        b3 = new JButton("Difficult");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        p.add(b1);
        p.add(b2);
        p.add(b3);


        setSize(1280, 720);
        setLocation(0, 0);
        add(p);
        setVisible(true);

        setSize(1280, 720);
        setLocation(0, 0);
        add(p);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) { //***********************************
        if (e.getSource() == b1) {
            this.dispose();
            new Main("board1.txt");
        }
        if (e.getSource() == b2) {
            this.dispose();
            new Main("board2.txt");
        }
        if (e.getSource() == b3) {
            this.dispose();
            new Main("board3.txt");
        }
    }
}
