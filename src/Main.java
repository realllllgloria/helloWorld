import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main extends JFrame {

    //* CHANGED
    private String boardfile;
    private JPanel gameBoard;
    //* CHANGED end

    public Main(String boardfile, JFrame prevFrame) {
        this.boardfile= boardfile;
        setTitle("Gridlock");
        setSize(500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(new BorderLayout());
        BufferedImage myImage = null;

        try {
//            myImage = ImageIO.read(Board.backgroundImage);
            if (Board.backgroundImage == null) {
            	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            myImage = ImageIO.read(Main.class.getResourceAsStream(Board.backgroundImage));
        } catch (IllegalArgumentException  ex1) {
            System.out.println("Stream is null");
        } catch (IOException ex) {
            System.out.println("Could not find the file");
        }

        	
        if (myImage == null) {
        	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        setContentPane(new JLabel(new ImageIcon(myImage)));
        getContentPane().setLayout(null);

        gameBoard = new JPanel();
        gameBoard.setSize(new java.awt.Dimension(Board.W * Unit.W, Board.H * Unit.H));
        gameBoard.setLocation(15, 0);
        gameBoard.setOpaque(false);
        getContentPane().add(gameBoard);
        gameBoard.setLayout(null);

        //* CHANGED
        JPanel infoBoard = new JPanel();
        infoBoard.setSize(new java.awt.Dimension(Board.W * Unit.W, 200));
        infoBoard.setLocation(15, 600);
        infoBoard.setOpaque(false);
        getContentPane().add(infoBoard);
        JButton btnRestart = new JButton("Restart");
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        infoBoard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        infoBoard.add(btnRestart);
        
        JButton btmHome = new JButton("Home");
        btmHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
//        		Menu newMenu = new Menu();
//        		newMenu.getFrame().setVisible(true);
        		prevFrame.setVisible(true);
        	}
        });
        infoBoard.add(btmHome);
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        infoBoard.add(btnSave);
        //* CHANGED end

        setSize(Board.W * Unit.W + 30, Board.H * Unit.H + 220);
        setResizable(false);

        restart();
    }

    /**
     * This restarts the game
     */
    private void restart() {
        for (Component c : gameBoard.getComponents())
            gameBoard.remove(c);
        BlockList.loadBoard(boardfile);
        for (Block block : BlockList.blocks) {
            gameBoard.add(block);
            block.setVisible(true);
        }
        BlockList.rebuildOccupancy();
        gameBoard.updateUI();
    }

//    public static void main(String args[]) {
//        new Main("board1.txt");
//    }
}
