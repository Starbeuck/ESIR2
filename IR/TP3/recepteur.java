import java.nio.channels.*;
import java.nio.*;
import java.net.*;
import java.nio.charset.*;

class recepteur {

public static void main(String[] argv) throws Exception
{
	// Créez un objet Charset pour gérer l'encodage et le décodage du texte
	// ...
	Charset charset = Charset.forName("UTF-8");

	// Créez votre socket UDP (DatagramChannel)
	// ...
	DatagramChannel cd = null;
	
	// Attachez votre socket à un port UDP arbitraire sur votre système
	// (par exemple 12345)
	// ...
	InetSocketAddress adress = null;
	adress = new InetSocketAddress("127.0.0.1", 8080);

	// Créez un buffer pour stocker temporairement les données reçues
	// ...
	ByteBuffer buff = ByteBuffer.allocate(256);;
	cd = DatagramChannel.open();	
	cd.bind(adress);
	while(true)
	{
		// recevez un message sur votre socket
		// ...


				
		cd.receive(buff);
		// décodez le message et affichez-le sur la sortie standard
		// ...
		buff.flip();
		CharBuffer tmp = charset.decode(buff);
		System.out.println(tmp.toString());
		buff.clear();
	}
}

}
