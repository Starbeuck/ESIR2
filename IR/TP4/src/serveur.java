import java.io.File;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class serveur {
	
	public static void main(String[] argv) throws Exception {

		// Creer un objet Charset pour gérer l'encodage et le decodage du texte
		Charset charset = Charset.forName("UTF-8");

		// Initialisation du socket UDP
		ServerSocketChannel sc = null;
		InetSocketAddress adress = null;
	
		//Initialisation du futur port de la socket
		adress = new InetSocketAddress(8080);

		//On alloue de la memoire au buffer afin de recevoir la reponse 
		ByteBuffer buff = ByteBuffer.allocate(65536);

		//on ouvre la connexion
		sc = ServerSocketChannel.open();
		sc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		//la connexion se fait a l'adresse demandee
		sc.bind(adress);
		
		//on accepte la connexion par le socketchannel correspondant
		SocketChannel client = sc.accept();

		//initialisation du nouveau fichier cree a la racine du disque D
		File file = new File ("D:\\test.html");
		
		while (true) {
			
			//le socketchannel lit le buff et le flip
			client.read(buff);
			buff.flip();
			
			//le printwiter encode le fichier avec le bon encodage
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			
			//il ecrit le resultat de la requete dans le fichier et on le close
			writer.print(charset.decode(buff));
			writer.close();
			
		}

	}
}
