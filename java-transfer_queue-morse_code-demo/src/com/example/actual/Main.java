package com.example.actual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Main implements KeyListener {	
	private JFrame frame;
	private JPanel panel;
	private JLabel rawMessageLabel;
	private JLabel parsedMessageLabel;
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

		while (true) {
			rawMessageLabel.setText("Raw Data: " + consumer.getRawMessage());
			parsedMessageLabel.setText("Parsed Data: " + consumer.getParsedMessage());
			Thread.sleep(100);
		}
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
		panel = new JPanel();
		signalButton = new JButton();

		rawMessageLabel = new JLabel("Raw Data: ");
		//rawMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		//rawMessageLabel.setBounds(200, 400, 200, 400);

		rawMessageLabel.setPreferredSize(new Dimension(800, 40));
		rawMessageLabel.setFont(new Font(rawMessageLabel.getFont().getName(), rawMessageLabel.getFont().getStyle(), 16));
		rawMessageLabel.setBorder(new LineBorder(Color.BLACK));
		rawMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		//parsedMessageLabel = rawMessageLabel;
		parsedMessageLabel = new JLabel("Parsed Data: ");
		parsedMessageLabel.setPreferredSize(rawMessageLabel.getPreferredSize());
		parsedMessageLabel.setFont(rawMessageLabel.getFont());
		parsedMessageLabel.setBorder(rawMessageLabel.getBorder());
		parsedMessageLabel.setVerticalAlignment(SwingConstants.CENTER);

		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 300));
		panel.setLayout(new GridLayout(6, 5));
		// panel.add(signalButton);
		//panel.add(frame, consumer);
		panel.add(rawMessageLabel);
		panel.add(parsedMessageLabel);

		panel.addKeyListener(this);
		panel.setFocusable(true);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Transfer Queue - Morse Demo");
		frame.pack();
		frame.setVisible(true);
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (isNum5OOrEnterKeyCode(e.getKeyCode()) && !keyPressed) {
			producer.sendSignal(true);
			keyPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (isNum5OOrEnterKeyCode(e.getKeyCode()) && keyPressed) {
			producer.sendSignal(false);
			keyPressed = false;
		}
	}

	private boolean isNum5OOrEnterKeyCode(int keyCode) {
		// num lock activation changes the key's code
		return (keyCode == 12 || keyCode == 101 || keyCode == 10);
	}
}
