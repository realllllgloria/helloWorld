import project.Generator;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

public class Menu {

	private JFrame frame;
	private Clip clip;
//	private JPanel pnlMain;
//	private JPanel pnlOption;
//	private JPanel pnlDifficulty;
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// play music
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("music.wav"))) {
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.loop(LOOP_CONTINUOUSLY);
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
//		Media media = new Media(getClass().getResource("music.mp3").toExternalForm());
//		MediaPlayer player = new MediaPlayer(media);
//		player.play();

		
		frame = new JFrame();
		frame.getLayeredPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
		frame.setTitle("\u5403\u9E21666");
		frame.setBounds(100, 100, 600, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel pnlMain = new JPanel();
		frame.getContentPane().add(pnlMain, "name_48851763736285");
		pnlMain.setLayout(null);
		pnlMain.setVisible(true);

		final JPanel pnlDifficulty = new JPanel();
		frame.getContentPane().add(pnlDifficulty, "name_48859887106064");
		pnlDifficulty.setLayout(null);
		pnlDifficulty.setVisible(false);

		final JPanel pnlOption = new JPanel();
		frame.getContentPane().add(pnlOption, "name_48861624405630");
		pnlOption.setLayout(null);
		pnlOption.setVisible(false);

		final JPanel pnlBackgrounds = new JPanel();
		frame.getContentPane().add(pnlBackgrounds, "name_48861624405631");
        pnlBackgrounds.setLayout(new GridLayout(3,2));
        pnlBackgrounds.setVisible(false);

		JButton btnOpt = new JButton("Options");
		btnOpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlMain.setVisible(false);
				pnlOption.setVisible(true);
			}
		});
		btnOpt.setBounds(134, 89, 97, 25);
		pnlMain.add(btnOpt);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(134, 150, 97, 25);
		pnlMain.add(btnExit);

		JButton btnDdiff = new JButton("Difficulty");
		btnDdiff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlDifficulty.setVisible(true);
				pnlMain.setVisible(false);
			}
		});
		btnDdiff.setBounds(134, 38, 97, 25);
		pnlMain.add(btnDdiff);


		JButton btnHard = new JButton("Hard");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FOR GLORIA
				//frame.dispose();
				frame.setVisible(false);
//				new Main("src/board3.txt");
				try {
					new Generator("Hard");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Main("src/project/boards/Hard.txt", frame);
			}
		});
		btnHard.setBounds(159, 146, 97, 25);
		pnlDifficulty.add(btnHard);

		JButton btnMedium = new JButton("Normal");
		btnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FOR GLORIA
				//frame.dispose();
				frame.setVisible(false);
//				new Main("src/board2.txt");
				try {
					new Generator("Normal");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Main("src/project/boards/Normal.txt", frame);
			}
		});
		btnMedium.setBounds(159, 91, 97, 25);
		pnlDifficulty.add(btnMedium);

		JButton btnEasy = new JButton("Easy");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FOR GLORIA
				//frame.dispose();
				frame.setVisible(false);
				try {
					new Generator("Easy");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Main("src/project/boards/Easy.txt", frame);
			}
		});
		btnEasy.setBounds(159, 41, 97, 25);
		pnlDifficulty.add(btnEasy);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDifficulty.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnHome.setBounds(159, 200, 97, 25);
		pnlDifficulty.add(btnHome);

		JButton btnMute = new JButton("Music off");
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clip.isRunning()) {
					clip.stop();
					btnMute.setText("Music On");
				} else {
					clip.start();
					btnMute.setText("Music Off");
				}
			}
		});

		btnMute.setBounds(152, 34, 97, 25);
		pnlOption.add(btnMute);
		
		//Zheng's bg 1 button start
		JButton btnBackground1 = new JButton();
		final String name1 = "1.jpg";
		try {
			btnBackground1.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(name1)).getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnBackground1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Board.backgroundImage = "1.jpg";
                pnlBackgrounds.setVisible(false);
                pnlOption.setVisible(true);
            }
		});
		pnlBackgrounds.add(btnBackground1);
		//Zheng's bg 1 button ends
		
		
		//Zheng's bg 2 button start
		JButton btnBackground2 = new JButton();
		final String name2 = "2.jpg";
		try {
			btnBackground2.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(name2)).getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnBackground2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Board.backgroundImage = "2.jpg";
                pnlBackgrounds.setVisible(false);
                pnlOption.setVisible(true);
            }
		});
		pnlBackgrounds.add(btnBackground2);
		//Zheng's bg 2 button ends

		//Zheng's bg 3 button start
		JButton btnBackground3 = new JButton();
		final String name3 = "3.jpg";
		try {
			btnBackground3.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(name3)).getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnBackground3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Board.backgroundImage = "3.jpg";
                pnlBackgrounds.setVisible(false);
                pnlOption.setVisible(true);
            }
		});
		pnlBackgrounds.add(btnBackground3);
		//Zheng's bg 3 button ends
		
		//Zheng's bg 3 button start
		JButton btnBackground4 = new JButton();
		final String name4 = "4.jpg";
		try {
			btnBackground4.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(name4)).getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnBackground4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Board.backgroundImage = "4.jpg";
                pnlBackgrounds.setVisible(false);
                pnlOption.setVisible(true);
            }
		});
		pnlBackgrounds.add(btnBackground4);
		//Zheng's bg 3 button ends
		


		JButton btnHome_3 = new JButton("Back");
		btnHome_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlBackgrounds.setVisible(false);
				pnlOption.setVisible(true);
			}
		});
		pnlBackgrounds.add(btnHome_3); 
		

		JButton btnOption2 = new JButton("Background");
		btnOption2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlOption.setVisible(false);
				pnlBackgrounds.setVisible(true);
			}
		});

		btnOption2.setBounds(152, 80, 97, 25);
		pnlOption.add(btnOption2);

		JButton btnHome_1 = new JButton("Home");
		btnHome_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlOption.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnHome_1.setBounds(152, 140, 97, 25);
		pnlOption.add(btnHome_1);
	}
}