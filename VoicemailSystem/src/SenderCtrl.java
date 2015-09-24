import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class SenderCtrl {
	private DataHandler dataHandler;
	private String number;
	private String mes;
	private ArrayList<String> mesList;
	final JTextArea mesArea = new JTextArea(15, 15);
	
	public SenderCtrl(){
		dataHandler = new DataHandler();
		frameSetup();
	}
	
	public void frameSetup(){
		final JFrame sender = new JFrame("Leave A Voicemail");
		sender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sender.setSize(450, 300);
		sender.setLocationRelativeTo(null);
		sender.setLayout(new GridLayout(1,2));
		sender.setVisible(true);
		
		JPanel mesPane = new JPanel();
		
		mesArea.setLineWrap(true);
		mesArea.setWrapStyleWord(true);
		mesPane.add(mesArea);
		sender.add(mesPane);
		
		JPanel ctrlPane = new JPanel();
		ctrlPane.setLayout(new GridLayout(2,1));
		JPanel numberPane = new JPanel();
		numberPane.setLayout(new GridLayout(4,3));
		JButton bt1 = createBt("1");
		JButton bt2 = createBt("2");
		JButton bt3 = createBt("3");
		JButton bt4 = createBt("4");
		JButton bt5 = createBt("5");
		JButton bt6 = createBt("6");
		JButton bt7 = createBt("7");
		JButton bt8 = createBt("8");
		JButton bt9 = createBt("9");
		JButton bta = createBt("*");
		JButton bt0 = createBt("0");
		JButton bts = createBt("#");
		numberPane.add(bt1);
		numberPane.add(bt2);
		numberPane.add(bt3);
		numberPane.add(bt4);
		numberPane.add(bt5);
		numberPane.add(bt6);
		numberPane.add(bt7);
		numberPane.add(bt8);
		numberPane.add(bt9);
		numberPane.add(bta);
		numberPane.add(bt0);
		numberPane.add(bts);
		
		JPanel buttonPane = new JPanel();
		JButton dial = new JButton("Dial");
		final JButton save = new JButton("Save");
		save.setEnabled(false);
		mesList = new ArrayList<String>();
		
		dial.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				number = mesArea.getText();
				mesArea.setText(null);
				if(dataHandler.numberFound("PhoneNumber.txt", number)){
					JOptionPane.showMessageDialog(sender.getComponent(0), "You have reached mailbox " 
							+ number.substring(6) + 
							", please leave a message; when done, push button “Save” to save the message");
					save.setEnabled(true);
				}
				else
					JOptionPane.showMessageDialog(sender.getComponent(0), "You dialed a wrong number");
			}
		});
		
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mes = mesArea.getText();
				dataHandler.getMes("Message.txt", mesList);
				mesList.add(mes);
				dataHandler.saveMes("Message.txt", mesList);
				mesArea.setText(null);
				mesArea.append("Your message has been saved");
			}
		});
		
		buttonPane.add(dial);
		buttonPane.add(save);
		
		ctrlPane.add(numberPane);
		ctrlPane.add(buttonPane);
		sender.add(ctrlPane);
		sender.pack();
	}
	
	public JButton createBt(String digit){
		JButton button = new JButton(digit);
		final String toAdd = digit;
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mesArea.append(toAdd);
			}
			
		});
		return button;
	}

}
