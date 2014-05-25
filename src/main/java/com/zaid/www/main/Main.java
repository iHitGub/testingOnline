package com.zaid.www.main;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.zaid.www.cl.CList;




public class Main {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setBackground(new Color(255, 229, 204));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel dataStructuresTitle = new JLabel("Data Structures");
		dataStructuresTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		dataStructuresTitle.setBackground(Color.YELLOW);
		dataStructuresTitle.setHorizontalAlignment(SwingConstants.CENTER);
		dataStructuresTitle.setBounds(0, 11, 434, 14);
		frame.getContentPane().add(dataStructuresTitle);
		
		System.out.print("");
		CList<Integer> l = new CList<Integer>();
		l.insert(1);
		l.insert(5);
		l.insert(9);
		
		JLabel intro = new JLabel("This is a basic circular list demostration!");
		intro.setBounds(35, 46, 700, 14);
		frame.getContentPane().add(intro);
		
		JLabel cList = new JLabel("The list contains the following numbers: " + l.toString());
		cList.setBounds(35, 89, 700, 14);
		frame.getContentPane().add(cList);	

	}
}
