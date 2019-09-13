package redesFinal;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleFileClient {

	public static void main(String[] args) {
		
		//JOGAR T1 PARA DENTRO DA T2...........................................
		
		
		
		new Thread(t2).start();

	}

	private static Runnable t1 = new Runnable() {
		public void run() {
			
			final int SOCKET_PORT = 13267; 
			final String SERVER = "localhost"; 
			final String FILE_TO_RECEIVED = "\\Users\\nicol\\Desktop\\ImagemSerialize.ser"; // you
			final int FILE_SIZE = 6022386; 
			
			int bytesRead;
			int current = 0;
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			Socket sock = null;
			try {
				sock = new Socket(SERVER, SOCKET_PORT);

				

				
				byte[] mybytearray = new byte[FILE_SIZE];
				InputStream is = sock.getInputStream();
				fos = new FileOutputStream(FILE_TO_RECEIVED);
				bos = new BufferedOutputStream(fos);
				bytesRead = is.read(mybytearray, 0, mybytearray.length);
				current = bytesRead;

				do {
					bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
					if (bytesRead >= 0)
						current += bytesRead;
				} while (bytesRead > -1);

				bos.write(mybytearray, 0, current);
				bos.flush();
			//	System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + current + " bytes read)");
				HandlerImage.readObject();
				

			} catch (UnknownHostException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} finally

			{
				try {
					if (fos != null)
						fos.close();

					if (bos != null)
						bos.close();

					if (sock != null)
						sock.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
	};
	private static Runnable t2 = new Runnable() {
		public void run() {
			final int SOCKET_PORT = 4200; // you may change this

			int portNumber = SOCKET_PORT;

			System.out.println("Aguardando o outro jogador terminar o desenho.");
			System.out.println("Envie sua mensagem assim que identificar o desenho.");

			try (ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
					Socket clientSocket = serverSocket.accept();
					
					
					

			) {
				new Thread(t1).start();
				while (clientSocket.isConnected()){
				
					BufferedReader inputSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					BufferedReader inTeclado = new BufferedReader(new InputStreamReader(System.in));
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					
					
					
					
					String inputLine;
					System.out.println("Envie sua mensagem: ");
					inputLine = inTeclado.readLine();
					if( inputLine != null)  {
						System.out.println("Mensagem enviada: " + inputLine);
						out.println(inputLine);
						
					}
					
					
					String userInput;
					userInput = inputSocket.readLine();
					if( userInput != null) {
						System.out.println("\n\nResposta recebida: " + userInput);
						
					}


				}
			} catch (IOException e) {
				System.out.println("Erro detectado ao tentar ouvir a porta " + portNumber + " ou escutar a conexão.");
				System.out.println(e.getMessage());
			}

		}

	};

}

