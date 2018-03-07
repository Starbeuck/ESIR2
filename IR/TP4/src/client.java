import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class client {
	public static void main(String[] argv) throws Exception {

		// Creer un objet Charset pour gérer l'encodage et le decodage du texte
		Charset charset = Charset.forName("UTF-8");

		// Initialisation du socket UDP
		SocketChannel sc = null;
		InetSocketAddress adress = null;

		// l'utilisateur rentre une string qui est une URL
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une URL");
		String str = scan.nextLine();
		
		// la string est "convertie" en URL
		URL myUrl = new URL(str);

		//l'adresse du futur serveur est l'host de l'URL rentree
		adress = new InetSocketAddress(myUrl.getHost(), 80);

		//encodage de la requete avec le path de l'URL
		ByteBuffer buff = charset.encode("GET " + myUrl.getPath().toString()
				+ " HTTP/1.0\r\n\r\n");
		
		//connection du socket au serveur
		sc = SocketChannel.open();
		sc.connect(adress);

		 //Initialisation du second socket au port 8080
        SocketChannel serverSocket = null;
        InetSocketAddress target = null;
        target = new InetSocketAddress(8080);
        
        
		while(true) {
			//envoi de la requete
			sc.write(buff);
			buff.flip();
			
			//on recupere la reponse de la requete en allouant plus de place au bytebuffer
			buff=ByteBuffer.allocate(999999999);
			sc.read(buff);
			buff.flip();
			
			
			//on cree un second socket pour se connecter a un second serveur
	        int portNumber = 8080;
	        System.out.println("Creating socket to localhost on port " + portNumber);

	        //connection au nouveau serveur
	        serverSocket = SocketChannel.open();
	        serverSocket.connect(target);
	        
	        //on envoit la reponse de la requete a ce nouveau serveur
	        serverSocket.write(buff);
	        
		}
		
	}
}
