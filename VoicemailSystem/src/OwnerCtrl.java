import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class OwnerCtrl {
	private DataHandler dataHandler;
	private String mailboxNum;
	private String passcode;
	private String greeting;
	private String currentMes;
	private ArrayList<String> mesList;
	final JTextArea mesArea = new JTextArea(15, 15);
	
	public OwnerCtrl(){
		dataHandler = new DataHandler();
		frameSetup();
	}
	
	public void frameSetup(){
		final JFrame owner = new JFrame("Log In");
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		owner.setSize(550, 300);
		owner.setLocationRelativeTo(null);
		owner.setLayout(new GridLayout(1,2));
		owner.setVisible(true);
		
		JPanel mesPane = new JPanel();
		
		mesArea.setLineWrap(true);
		mesArea.setWrapStyleWord(true);
		mesPane.add(mesArea);
		owner.add(mesPane);
		
		JPanel ctrlPane = new JPanel();
		ctrlPane.setLayout(new GridLayout(2,1));
		JPanel numberPane = new JPanel();
		numberPane.setLayout(new GridLayout(4,3));
		final JButton bt1 = createBt("1");
		final JButton bt2 = createBt("2");
		JButton bt3 = createBt("3");
		JButton bt4 = createBt("4");
		JButton bt5 = createBt("5");
		JButton bt6 = createBt("6");
		JButton bt7 = createBt("7");
		JButton bt8 = createBt("8");
		JButton bt9 = createBt("9");
		final JButton bta = createBt("*");
		JButton bt0 = createBt("0");
		final JButton bts = createBt("#");
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
		buttonPane.setLayout(new GridLayout(3,2));
		
		JButton dial = new JButton("Dial");
		final JButton login = new JButton("Log In");
		login.setEnabled(false);
		final JButton changePass = new JButton("Change Passcode");
		changePass.setEnabled(false);
		final JButton changeGreet = new JButton("Change Greeting");
		changeGreet.setEnabled(false);
		final JButton retrieve = new JButton("Ritrieve Message");
		retrieve.setEnabled(false);
		JButton quit = new JButton("QUIT");
		greeting = dataHandler.getData("Greeting.txt", greeting);
		
		dial.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mailboxNum = mesArea.getText();
				mesArea.setText(null);
				if(dataHandler.numberFound("MailboxNumber.txt", mailboxNum)){
					JOptionPane.showMessageDialog(owner.getComponent(0),"Enter the passcode");
					login.setEnabled(true);
				}
				else
					JOptionPane.showMessageDialog(owner.getComponent(0),"You dialed a wrong number");
			}
		});
		
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				passcode = mesArea.getText();
				mesArea.setText(null);
				if(dataHandler.numberFound("Passcode.txt", passcode)){
					owner.setTitle("Mailbox " + mailboxNum);
					JOptionPane.showMessageDialog(owner.getComponent(0), greeting);
					retrieve.setEnabled(true);
					changePass.setEnabled(true);
					changeGreet.setEnabled(true);
				}
				else
					JOptionPane.showMessageDialog(owner.getComponent(0),"Passcode was incorrect, try again");
			}
		});
		
		changePass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setTitle("Change Passcode");
				mesArea.setText(null);
				JOptionPane.showMessageDialog(owner.getComponent(0),"Enter new passcode, and press �#� key to save");
				bts.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						passcode = mesArea.getText();
						mesArea.setText(null);
						dataHandler.saveData("Passcode.txt", passcode);
					}	
				});
			}
		});
		
		changeGreet.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setTitle("Change Greeting Message");
				mesArea.setText(null);
				JOptionPane.showMessageDialog(owner.getComponent(0),"Enter new greeting message, and press �*� key to save");
				bta.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						greeting = mesArea.getText();
						mesArea.setText(null);
						dataHandler.saveData("Greeting.txt", greeting);
					}	
				});
			}
		});
		
		retrieve.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setTitle("Messages");
				JOptionPane.showMessageDialog(owner.getComponent(0),"Press 1 to read message (or the next message)\n"
						+ "Press 2 to delete the current message");
				mesList = new ArrayList<String>();
				dataHandler.getMes("Message.txt", mesList);
				final Iterator<String> it = mesList.iterator();
				bt1.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(it.hasNext()){
							mesArea.setText(null);
							mesArea.append(it.next());
						}
						else JOptionPane.showMessageDialog(owner.getComponent(0),"No more messages");
					}
				});
				bt2.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						it.remove();
						dataHandler.saveMes("Message.txt", mesList);
					}
				});
			}
		});
		
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		buttonPane.add(dial);
		buttonPane.add(login);
		buttonPane.add(changePass);
		buttonPane.add(changeGreet);
		buttonPane.add(retrieve);
		buttonPane.add(quit);
		
		
		ctrlPane.add(numberPane);
		ctrlPane.add(buttonPane);
		owner.add(ctrlPane);
		owner.pack();
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
