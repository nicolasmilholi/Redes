package redesFinal;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Paint {

	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame("Paint It");
		

		Container content = frame.getContentPane();
		
		content.setLayout(new BorderLayout());
		

		final PadDraw drawPad = new PadDraw();
		

		content.add(drawPad, BorderLayout.CENTER);
		

		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(32, 68));
		panel.setMinimumSize(new Dimension(32, 68));
		panel.setMaximumSize(new Dimension(32, 68));
		
		JButton clearButton = new JButton("Clear");
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.clear();

			}
		});

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.send();

			}
		});

		panel.add(clearButton);
		panel.add(sendButton);

		

		content.add(panel, BorderLayout.SOUTH);
		

		frame.setSize(500, 500);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
	}

}
