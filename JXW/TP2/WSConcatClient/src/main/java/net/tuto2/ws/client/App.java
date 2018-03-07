package net.tuto2.ws.client;

import MyConcatAgent.java;
import net.tuto2.ws.client.WSConcat;

/**
 * Hello world!
 *
 */
public class App 
{  

    public static void main( String[] args )
    {
        MyConcatAgent toto = new MyConcatAgent();
        WSConcat test = toto.getWSConcatImplPort();
        test.concat("test", "test");
        System.out.println( "Hello World!" );
    }
}
