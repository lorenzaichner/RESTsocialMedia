import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("network")
public class SocialNetwork {
	
	ArrayList<Benutzer> benutzer = new ArrayList<>();
	public SocialNetwork() throws IOException{
		/*benutzer.add(new Benutzer(1,"testUser1", "Lorenz", "Aichner"));
		benutzer.add(new Benutzer(2,"Junge", "jakob", "dellago"));
		benutzer.add(new Benutzer(3,"testUser3", "Mak", "Ke"));
		benutzer.add(new Benutzer(4,"testUser4", "Dam", "Ian"));
		*/
		benutzerAuslesen();
	}
	
	//Läuft
	@Path("user/{id}")
	@GET
	@Produces("text/plain")
	public String getSpecificBenutzer(@PathParam("id")int i){
		String user;
		user = benutzer.get(i).getUsername() +"\t"+ benutzer.get(i).getVorname() +"\t"+benutzer.get(i).getNachname();
		return user;
	}
	
	//Läuft
	@Path("adduser/{username}/{vname}/{nname}")
	@GET
	@Produces("text/plain")
	public String addBenutzer(@PathParam("username")String username,@PathParam("vname") String vname, @PathParam("nname")String nname) throws IOException{
		benutzer.add(new Benutzer(benutzer.size()+1,username,vname,nname));
		 FileWriter fw = new FileWriter("users.txt",true);
		 BufferedWriter bw = new BufferedWriter(fw);
		 bw.write("\n");
		 bw.write(benutzer.size()+","+username+","+vname+","+nname);
		 bw.close();
		 return "hinzugefuegt";
	}
	
	//Läuft mit /userRemove/{id von user}
	@Path("userRemove/{user}")
	@GET
	@Produces("text/plain")
	public void deleteBenutzer(@PathParam("user")int i){
		benutzer.remove(i);
	}
	
	//Läuft mit /user/{id von user}/post/{id von Post}
	@Path("user/{user}/post/{post}")
	@GET
	@Produces("text/plain")
	public String getPostFromUser(@PathParam("user") int x,@PathParam("post") int xpost){
		String post = null;
		post = benutzer.get(x).getSpecificPost(xpost);
		return post;
	}
	
	//Läuft mit userPosts/{id von Post}
	@Path("userPosts/{user}")
	@GET
	@Produces("text/plain")
	public String getAllPostFromUser(@PathParam("userPosts") int x){
		String posts = "\t"+benutzer.get(x).getUsername();
		ArrayList<Post> allPosts = benutzer.get(x).getPost();
		for(int i = 0; i < allPosts.size(); i++){
			posts = posts + "\n" + allPosts.get(i).getData()+ "\t"+allPosts.get(i).getDatum();
		}
		return posts;
	}
	
	//Läuft mit /posts
	@Path("posts")
	@GET
	@Produces("text/plain")
	public String getAllPostFromNetwork(){
		String posts = null;
		for(int j = 0; j < benutzer.size(); j++){
			ArrayList<Post> allPosts = benutzer.get(j).getPost();
			posts = posts + "\n\t"+benutzer.get(j).getUsername();
			for(int i = 0; i < allPosts.size(); i++){
				posts = posts + "\n" + allPosts.get(i).getData()+ "\t"+allPosts.get(i).getDatum();
			}	
		}
		return posts;
	}
	//Läuft mit /users
	@Path("users")
	@GET
	@Produces("text/plain")
	public String getAllUsers() throws IOException{
		String users = benutzer.get(0).getUsername();
		for(int j = 1; j < benutzer.size(); j++){
			users = users + "\n" +benutzer.get(j).getUsername();
		}
		return users;
	}

	public void addPost(String data, int x){
		benutzer.get(x).addPosting(data);
	}
	
	public void benutzerAuslesen() throws IOException{
		  FileReader fr = new FileReader("users.txt");
		  BufferedReader br = new BufferedReader(fr);
		  String x;
		  int y = 0;

		   while((x = br.readLine())!=null){
			   String array[] = x.split(",");
			   for (int i=0; i<array.length; i++){
				   array[i].trim();
			   }
			   benutzer.add(new Benutzer(y, array[1], array[2], array[3]));
			   y++;
		   }

		    br.close();
		
	}
	
	
}
