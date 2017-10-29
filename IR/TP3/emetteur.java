import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.Scanner;

class emetteur {

	@SuppressWarnings("null")
	public static void main(String[] argv) throws Exception {
		// Créez un objet Charset pour gérer l'encodage et le décodage du texte
		// ...
		Charset charset = Charset.forName("UTF-8");

		// Créez votre socket UDP (DatagramChannel)
		// ...
		DatagramChannel cd = null;
		InetSocketAddress adress = null;
		adress = new InetSocketAddress("127.0.0.1", 8080);

		while (true) {
			// Lisez un ligne au clavier
			// ...
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("Veuillez écrire une phrase, svp");

			String str = scan.nextLine();

			ByteBuffer buff = charset.encode(str);
			// Envoyez la chaîne à votre correspondant
			// ...
			cd = DatagramChannel.open();
			cd.send(buff, adress);

		}
	}

}
