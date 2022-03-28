package com.example.actual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main implements KeyListener {
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	private JButton signalButton;
		
	private final MorseMessageProducer producer;
	private final MorseMessageConsumer consumer;
	
	private final TransferQueue<Boolean> transferQueue;
	private final ExecutorService exService;
	
	private boolean keyPressed;
	
	public static void main(String[] args) throws InterruptedException {		
		new Main();
	}

	public Main() throws InterruptedException {
		initGUI();
		
		transferQueue = new LinkedTransferQueue<>();
		exService = Executors.newFixedThreadPool(1);
		
		producer = new MorseMessageProducer(transferQueue);
		consumer = new MorseMessageConsumer(transferQueue);
		
		keyPressed = false;
		
		runConsumer(transferQueue, exService);
	}
	
	public void runConsumer(TransferQueue<Boolean> transferQueue, ExecutorService exService)
			throws InterruptedException {
		// when
		exService.execute(consumer);

		// then
		exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
		exService.shutdown();
	}
	
	private void initGUI() {
		frame = new JFrame();
		//frame.addKeyListener(this);
		
		panel = new JPanel();			
				
		signalButton = new JButton();
		//signalButton.addActionListener(this);
		//signalButton.addChangeListener(this);
		
		label = new JLabel("Number of clicks: 0");
		
		panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(signalButton);
		panel.add(label);
		
		//panel.setBackground(Color.PINK);
		
		panel.addKeyListener(this);
		panel.setFocusable(true);		
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Transfer Queue - Morse Demo");
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {	
		System.out.println("keyTyped");		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 12 && !keyPressed) {
			producer.sendSignal(true);
			keyPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 12 && keyPressed) {
			producer.sendSignal(false);
			keyPressed = false;
		}	
	}
}
