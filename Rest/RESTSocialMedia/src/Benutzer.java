import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.Path;




public class Benutzer {
	
	String username;
	int id;
	String vorname;
	String nachname;
	ArrayList<Post> post = new ArrayList<>();
	Random rand = new Random();
	
	public Benutzer(int id ,String username, String vorname, String nachname) throws IOException{
		this.username = username;
		this.vorname = vorname;
		this.nachname = nachname;
		this.id = id;
		
		post.add(new Post("Hallo Welt", "10.10.2007"));
		post.add(new Post("Hallo Hallo", "10.10.2008"));
		post.add(new Post("Hallo Hallo Welt", "10.10.2009"));
		post.add(new Post("Neuer Post1", "10.10.2010"));
		
	}
	
	public String getSpecificPost(int i){
		return post.get(i).data+"\t"+post.get(i).datum;
	}

	public ArrayList<Post> getPost(){
		return post;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getNachname(){
		return nachname;
	}
	
	public String getVorname(){
		return vorname;
	}
	
	public void addPosting(String data){
		post.add(new Post("data", ""+(rand.nextInt(30)+1)+"."+(rand.nextInt(12)+1)+".2017"));
	}

	public void deletePost(int i){
		post.remove(i);
	}

	
}
