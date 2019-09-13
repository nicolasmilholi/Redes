package redesFinal;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.awt.*; 
import javax.swing.*;
import javax.imageio.ImageIO;

public class HandlerImage {
	public static void writeObject(byte[] bs) {
		try{
			FileOutputStream fout = new FileOutputStream("\\Users\\nicol\\Desktop\\ImagemSerialize.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			
			oos.writeObject(bs);
			oos.close();
		}catch(Exception ex){
			ex.printStackTrace();
		} 
	}


		public static void readObject(){
			try{
				 //Responsável por carregar o arquivo ImagemSerialize.ser
				
				FileInputStream fin = new FileInputStream("\\Users\\nicol\\Desktop\\ImagemSerialize.ser");

				String dirName="\\Users\\nicol\\Desktop"; 
				
				 //Responsável por ler o objeto referente ao arquivo
				 
				ObjectInputStream ois = new ObjectInputStream(fin);

				/*
				 * Aqui a mágica é feita, onde os bytes presentes no arquivo ImagemSerialize.ser
				 * são convertidos em uma instância de Image.
				 * */
				byte[] teste = (byte[]) ois.readObject();
				BufferedImage imag = ImageIO.read(new ByteArrayInputStream(teste));
				ImageIO.write(imag, "PNG", new File(dirName,"ImagemRetornada.png"));
				//System.out.println("Imagem salva em " +dirName);
				Imgreader imagem = new Imgreader();
				imagem.read();
				imagem.setVisible(true);

			}catch(Exception ex){
				ex.printStackTrace(); 
			} 

	}

	public static byte[] writeImage(BufferedImage imagem, String formato) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(imagem, formato, bos);
		bos.flush();
		return bos.toByteArray();
	}

}


