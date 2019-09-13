package redesFinal;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Imgreader extends JFrame {

	public Imgreader() {

	}

	public void read() {
		String arg = "\\Users\\nicol\\Desktop\\ImagemRetornada.png";
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setBackground(Color.darkGray);
		ImageIcon icon = new ImageIcon(arg);
		JLabel label = new JLabel();
		label.setIcon(icon);
		panel.add(label);
		this.pack();
	}
}
