package com.example.actual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Main implements ActionListener {
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	private JButton signalButton;
		
	private final MorseMessageProducer producer;
	private final MorseMessageConsumer consumer;
	
	private final TransferQueue<Boolean> transferQueue;
	private final ExecutorService exService;
	
	public static void main(String[] args) throws InterruptedException {		
		new Main();
	}

	public Main() throws InterruptedException {
		initGUI();
		
		transferQueue = new LinkedTransferQueue<>();
		exService = Executors.newFixedThreadPool(1);
		
		producer = new MorseMessageProducer(transferQueue);
		consumer = new MorseMessageConsumer(transferQueue);
		
		runConsumer(transferQueue, exService);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		count++;		
//		label.setText(String.format("Number of clicks: %d", count));
		
		producer.sendSignal();
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
		
		signalButton = new JButton("");
		signalButton.addActionListener(this);
		
		label = new JLabel("Number of clicks: 0");
		
		panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(signalButton);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Transfer Queue - Morse Demo");
		frame.pack();
		frame.setVisible(true);
	}
}
