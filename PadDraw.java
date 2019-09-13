package redesFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;

class PadDraw extends JComponent {
	Image image;

	Graphics2D graphics2D;
	
	int currentX, currentY, oldX, oldY;
	
	public PadDraw() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				if (graphics2D != null)
					graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}

		});
	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}

	public void send() {
		try {

			BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D bGr = bimage.createGraphics();
			bGr.drawImage(image, 0, 0, null);
			String path = "\\Users\\nicol\\Desktop\\ImagemEnviada.png";
			javax.imageio.ImageIO.write(bimage, "PNG", new File(path));
			bGr.dispose();
			HandlerImage.writeObject(HandlerImage.writeImage(bimage, "PNG"));
			//System.out.println("salvo em " + path);
			SimpleFileServer.main();

		} catch (Exception e) {
			Logger.getLogger(PadDraw.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

	public void black() {
		graphics2D.setPaint(Color.black);
		repaint();
	}

}
