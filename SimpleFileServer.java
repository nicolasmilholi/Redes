package redesFinal;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleFileServer {

	public final static int SOCKET_PORT = 13267; // you may change this
	public final static String FILE_TO_SEND = "\\Users\\nicol\\Desktop\\ImagemSerialize.ser"; // you may change this

	public static void main() throws IOException {
		new Thread(t1).start();
		new Thread(t2).start();
	}

	private static Runnable t1 = new Runnable() {
		public void run() {
			final String SERVER = "192.168.0.111";

			BufferedInputStream bis = null;
			OutputStream os = null;
			ServerSocket servsock = null;
			Socket sock = null;

			try {
				servsock = new ServerSocket(SOCKET_PORT);
			} catch (IOException e) {

				e.printStackTrace();
			}
			while (true) {

				try {
					try {
						sock = servsock.accept();
						File myFile = new File(FILE_TO_SEND);
						byte[] mybytearray = new byte[(int) myFile.length()];
						FileInputStream fis = new FileInputStream(myFile);
						bis = new BufferedInputStream(fis);
						bis.read(mybytearray, 0, mybytearray.length);
						os = sock.getOutputStream();

						os.write(mybytearray, 0, mybytearray.length);
						os.flush();

					} catch (IOException e) {

						e.printStackTrace();
					}
				} finally {
					try {

						if (bis != null)
							bis.close();
						if (os != null)
							os.close();
						if (sock != null)
							sock.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block -- git teste
						e.printStackTrace();

					}
				}
			}
		}
	};
	private static Runnable t2 = new Runnable() {
		public void run() {

			final int SOCKET_PORT = 4200;
			final String SERVER = "localhost";

			String hostName = SERVER;
			int portNumber = SOCKET_PORT;

			System.out.println("Conex�o iniciada...");
			try (Socket echoSocket = new Socket(hostName, portNumber);

			) {

				System.out.println("Aguardando resposta do outro jogador...");

				while (echoSocket.isConnected()) {

					BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
					BufferedReader inTeclado = new BufferedReader(new InputStreamReader(System.in));
					PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

					String userInput;
					userInput = in.readLine();
					if (userInput != null) {
						System.out.println("\n\nResposta recebida: " + userInput);
					}
					
					
					
					String inputLine;
					System.out.println("\n\nEnvie sua mensagem...\nMensagem:");
					
					inputLine = inTeclado.readLine();
					if(inputLine != null) {
						System.out.println("Mensagem enviada: " + inputLine);
						out.println(inputLine);
					}

				}

			} catch (UnknownHostException e) {
				System.err.println("Ocorreu um erro ao tentar conectar ao servidor " + hostName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("N�o foi poss�vel conectar ao Servidor " + hostName);
				System.exit(1);
			}
		}
	};

}
