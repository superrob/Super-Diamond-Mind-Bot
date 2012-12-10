package com.robinkaja.sdm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
	
	private Game game = new Game();
	private JFrame frame;
	private ImagePanel imagePanel;
	private JButton playButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 307, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnFindGamePosition = new JButton("Find game position");
		btnFindGamePosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (game.findGamePosition()) {
					System.out.println("Spillet blev fundet! X: " + game.rootX + "  Y: " + game.rootY);
					playButton.setEnabled(true);
					imagePanel.image = game.getField(true);
					imagePanel.repaint();
					/*
					int[][] field = game.parseField();
					for (int y=0;y<8;y++) {
						System.out.print("Linje " + y +": ");
						for (int x=0;x<8;x++) {
							System.out.print(field[y][x] + " ");							
						}
						System.out.println("");
					}
					*/
				} else {
					System.out.println("Spillet blev IKKE fundet!");
				}
			}
		});
		btnFindGamePosition.setBounds(10, 11, 155, 23);
		frame.getContentPane().add(btnFindGamePosition);
		
		imagePanel = new ImagePanel();
		imagePanel.setBounds(10, 45, 272, 272);
		frame.getContentPane().add(imagePanel);
		
		playButton = new JButton("Make move");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imagePanel.image = game.getField(true);
				imagePanel.repaint();
				while (!game.isGameOver()) {
					int[][] field = game.parseField();
					for (int y=0;y<8;y++) {
						System.out.print("Linje " + y +": ");
						for (int x=0;x<8;x++) {
							System.out.print(field[y][x] + " ");							
						}
						System.out.println("");
					}
					while (game.makeGameMove());
				}
				game.restartGame();
				System.out.println("Kunne ikke finde et move.");
			}
		});
		playButton.setEnabled(false);
		playButton.setBounds(175, 11, 106, 23);
		frame.getContentPane().add(playButton);
	}
}
