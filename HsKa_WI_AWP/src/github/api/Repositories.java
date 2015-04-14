package github.api;

import github.domain.Commit;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kohsuke.github.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;




public class Repositories {
	public static void getRepositories() throws IOException{
		//org.eclipse.egit.github.core
		
		//Basic authentication
		GitHubClient client = new GitHubClient();
		client.setCredentials("saio1011", "Rbsramada234");
		
		RepositoryService service = new RepositoryService();
		
		//get only one repository
//		Repository repo = service.getRepository("saio1011", "arcsolution");
//		System.out.println(repo.getName() + " Watchers: " + repo.getWatchers() + "" + repo.getHtmlUrl());
		
		//get all repositories
		try {
			for (Repository repo : service.getRepositories("saio1011"))
			  System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void getCommits() throws IOException{
////		//org.eclipse.egit.github.core
////		
////		//Basic authentication
////		GitHubClient client = new GitHubClient();
////		client.setCredentials("saio1011", "Rbsramada234");
////		
////		RepositoryService service = new RepositoryService();
////		final long id = service.getRepository("saio1011","arcsolution").getId();
////		
////		CommitService serviceCommit = new CommitService();
////		
////		IRepositoryIdProvider repository;
////		List<RepositoryCommit> commits = serviceCommit.getCommits(id);
////		for (RepositoryCommit repositoryCommit : commits) {
////			System.out.println(repositoryCommit.getAuthor().getName());
////		}
//	}
	
	
	
	public static void getRepoHttpGetRequest(){
		
	}
	
	//get JSON
	public static void getReposJSON() throws ParseException, IOException{
		URL url = new URL("https://api.github.com/users/saio1011/repos");
		RepositoryService service = new RepositoryService();
		final long id = service.getRepository("saio1011","arcsolution").getId();
		
		Client client = Client.create();
		WebResource webResource = 
				client.resource("https://api.github.com/users/saio1011/repos");
		String response = webResource.get(String.class);
		
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(response);
		JSONArray array=(JSONArray)obj;
		
		System.out.println("======the 2nd element of array======");
		  System.out.println(array.get(1));
		  System.out.println();
		
//		try(InputStream is = url.openStream();
//			JsonReader rdr = Json.createReader(is)){
//			System.out.println(is.toString());
//			System.out.println("RDR"+rdr.toString());
//			
////			
//			JsonObject obj = rdr.readObject();
//			JsonArray results = obj.getJsonArray("full_name");
////			
//			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//				 System.out.print(result.getJsonObject("full_name").getString("full_name"));
//				 System.out.print(": ");
//				 System.out.println(result.getString("message", ""));
//				 System.out.println("-----------");
//			}
////			
//		}catch(Exception ex){
//			
//		}
	}
	
	public static void gerRepoJackson() throws JsonProcessingException, IOException{
		
		Client client = Client.create();
		WebResource webResource = 
				client.resource("https://api.github.com/users/saio1011/repos");
		String response = webResource.get(String.class);
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//read JSON like DOM Parser
		JsonNode rootNode = objectMapper.readTree(response);
		JsonNode idNode = rootNode.path("id");
		System.out.println("id = "+idNode.asInt());
	}
	
	//Jackson parsing 
	public static void getJSONJacksonParser() throws JsonParseException, MalformedURLException, IOException{
		String url = "https://api.github.com/users/saio1011/repos";
		
		JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(new URL(url));
        
        ArrayList<String> places = new ArrayList<String>();
        
     // continue parsing the token till the end of input is reached
        while (!parser.isClosed()) {
            // get the token
            JsonToken token = parser.nextToken();
            // if its the last token then we are done
            if (token == null)
                break;
            // we want to look for a field that says name
 
            if (JsonToken.FIELD_NAME.equals(token) && "name".equals(parser.getCurrentName())) {
                // we are entering the datasets now. The first token should be
                // start of array
            	token = parser.nextToken();
                System.out.println(parser.getText());
                
               
                places.add(parser.getText());
            	
            	
//                token = parser.nextToken();
//                if (!JsonToken.START_ARRAY.equals(token)) {
//                    // bail out
//                    break;
//                }
//                // each element of the array is an album so the next token
//                // should be {
//                token = parser.nextToken();
//                if (!JsonToken.START_OBJECT.equals(token)) {
//                    break;
//                }
//                // we are now looking for a field that says "album_title". We
//                // continue looking till we find all such fields. This is
//                // probably not a best way to parse this json, but this will
//                // suffice for this example.
//                while (true) {
//                    token = parser.nextToken();
//                    if (token == null)
//                        break;
//                    if (JsonToken.FIELD_NAME.equals(token) && "owner".equals(parser.getCurrentName())) {
//                        token = parser.nextToken();
//                        System.out.println(parser.getText());
//                    }
// 
//                }
 
            }
             
        }
        System.out.println("array list");
        for (String x :places ){
        	System.out.println("Repo Name: "+ x.toString());
        	
        }
	}
	
	//Jackson get Commiters from Commits 
	public static void getCommiters() throws JsonParseException, MalformedURLException, IOException{
		String url = "https://api.github.com/repos/saio1011/arcsolution/commits";
		
		JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(new URL(url));
        
        ArrayList<Commit> commits = new ArrayList<Commit>();
        
     // continue parsing the token till the end of input is reached
        while (!parser.isClosed()) {
            // get the token
            JsonToken token = parser.nextToken();
            // if its the last token then we are done
            if (token == null)
                break;
            // we want to look for a field that says name
 
            if (JsonToken.FIELD_NAME.equals(token) && "committer".equals(parser.getCurrentName())) {
                // we are entering the commit now. The first token should be
                // start of array
                 
            	
            	
                token = parser.nextToken();
//                if (!JsonToken.START_ARRAY.equals(token)) {
//                    // bail out
//                    break;
//                }
                // each element of the array is an album so the next token
                // should be {
//                token = parser.nextToken();
//                if (!JsonToken.START_OBJECT.equals(token)) {
//                    break;
//                }
                // we are now looking for a field that says "name". We
                // continue looking till we find all such fields. This is
                // probably not a best way to parse this json, but this will
                // suffice for this example.
                while (true) {
                	
                    token = parser.nextToken();
                    if (token == null)
                        break;
                    if (JsonToken.FIELD_NAME.equals(token) && "name".equals(parser.getCurrentName())) {
                    	Commit commit = new  Commit("", "");
                       token = parser.nextToken();
                       commit.setCommiterName(parser.getText());
                       token = parser.nextToken();
                       token = parser.nextToken();
                       token = parser.nextToken();
                       
                       if (JsonToken.FIELD_NAME.equals(token) && "date".equals(parser.getCurrentName())) {
                    	   token = parser.nextToken();
                    	   commit.setDate(parser.getText());
                       }
                       commits.add(commit);
                    }
          
       
                    
                }
            }
        }
        parser.close();
        int counter = 0;
        System.out.println("They are "+ commits.size() +" commits in this repository");
        for (Commit commit : commits){
        	counter ++;
        	System.out.println(commit.getCommiterName() + " am "+commit.getDate());
        }
        System.out.println("Counter: "+counter);
	}
	
	
	public static void getCommitsJsonObjekt(){
		String url = "https://api.github.com/repos/saio1011/arcsolution/commits?per_page=100";
		String auth = new String(Base64.encode("saio1011:Rbsramada234"));
		
		Client client = Client.create();
		WebResource webResource = client.resource(url);
				
		ClientResponse response = webResource.header("Authorization", "Basic " + auth)
									.type("application/json").accept("application/json")
									.get(ClientResponse.class);
				
		String body = response.getEntity(String.class);
		JSONArray commits = new JSONArray(body);
		System.out.println("Länge: "+ commits.length());
//		JSONObject obj = new JSONObject();
		for (int zl = 0; zl < commits.length(); zl++){
			System.out.println("Position: "+zl+" Commit Message: "+ commits.getJSONObject(zl).getJSONObject("commit").getString("message"));
		}
		
	}

}
