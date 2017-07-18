
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Servidor extends Thread{
	
	private Player player;

	private static ServerSocket serverSocket;

	private static Socket socket;

	private static ObjectOutputStream objectOutPS = null;

	private static ObjectInputStream objectInPS = null;

	private int port;

	private static boolean conectado = false;

	private static Loop loop;
	
	public Server(int port) {
		this.port = port;
	}

	public void run() {
		try {

			serverSocket = new ServerSocket(port);

			Jogo.trocaVisibilidade();

			conectado = true;

			Jogo.setAreaInfo("Server Criado");
			Jogo.setAreaInfo("Esperando Adiversário");

		} catch (IOException e) {
			e.printStackTrace();
			conectado = false;
			JOptionPane.showMessageDialog(null, "Impossivel Conectar a Port: "+port+"\nTente outra");
		}

		try {

			socket = serverSocket.accept();

			Jogo.setAreaInfo("Conectando com: " +socket.getLocalAddress().getHostAddress());
			Jogo.setAreaInfo("Adversario Conectado com sucesso;");

			new Tela(true);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			objectInPS = new ObjectInputStream(socket.getInputStream());
			objectOutPS = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

		loop = new Loop();
		loop.start();

		super.run();
	}

	public class Loop extends Thread{
		@Override
		public void run() {
			while(true){
				try {
					if(socket.isConnected()){
						Player p = (Player) objectInPS.readObject();
						if(p!=null){
							Jogo.player = p;
							
						}else {System.out.println("PLAYER 2 NÃO RECEBIDO\n\n");}
					}else{
						Main.telaInfo.setAreaInfo("Adversario Desconectado");
						JOptionPane.showMessageDialog(null, "Adversario Desconectado");
						System.exit(0);
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void  enviarPersonagem(){

		Personagem personagemEnviar = Main.personagem;
		

		try {
			objectOutPS.writeObject(personagemEnviar);
			objectOutPS.flush();
			objectOutPS.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void parar(){
		loop.stop();
	}
	
	public static void fecharConexao(){
		try {
			objectOutPS.close();
			objectInPS.close();
			socket.close();
			serverSocket.close();
			System.out.println("Server Fechado");
			Main.telaInfo.setAreaInfo("Server Fechado.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Imposivel Fechar o Server");
		}
	}

	public ObjectOutputStream getObjectOutPS() {
		return objectOutPS;
	}

	public ObjectInputStream getObjectInPS() {
		return objectInPS;
	}

	public static boolean isConectado() {
		return conectado;
	}

}
