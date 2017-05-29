import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;


public class Main {
	
	private final static int port= 9998;
	private final static String host="http://localhost/";
	
	public static void main(String[]args) throws IOException{
		
		URI baseUri= UriBuilder.fromUri(host).port(port).build();
		
		ResourceConfig config = new ResourceConfig(SocialNetwork.class);
		HttpServer server= JdkHttpServerFactory.createHttpServer(baseUri, config);
		SocialNetwork socialNetwork = new SocialNetwork();
		
	}
	
	
}
