package jira.api;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;


public class JiraApi {
	
	//jira using jersey
	public static void getJira() throws JsonParseException, IOException{
		
		String auth = new String(Base64.encode("saio1011:Rbsramada234"));
		
		//use &maxResults=-1 parameter to get all issues from the project
		Client client = Client.create();
		WebResource webResource = client.resource("http://www.iwi.hs-karlsruhe.de/awpjira/rest/api/2/search?jql=project=HWB&maxResults=-1");
		
		ClientResponse response = webResource.header("Authorization", "Basic " + auth)
								.type("application/json").accept("application/json")
								.get(ClientResponse.class);
		
		String body = response.getEntity(String.class);
		
		int statusCode = response.getStatus();
		System.out.println("Status Code: "+statusCode);
//		System.out.println("Body: "+body);
		
		JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(body);
        
//        ArrayList<String> places = new ArrayList<String>();
        
     // continue parsing the token till the end of input is reached
        while (!parser.isClosed()) {
            // get the token
            JsonToken token = parser.nextToken();
            // if its the last token then we are done
            if (token == null)
                break;
            // we want to look for a field that says name
 
            if (JsonToken.FIELD_NAME.equals(token) && "total".equals(parser.getCurrentName())) {
                // we are entering the datasets now. The first token should be
                // start of array
            	token = parser.nextToken();
                System.out.println("Es gibt : " + parser.getText() + " Issues in dem HWB - JIRA Projekt");
                break;
                
               
//                places.add(parser.getText());
            	
            	
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
//        System.out.println("array list");
//        for (String x :places ){
//        	System.out.println("Repo Name: "+ x.toString());
//        	
//        }
	}
	

}
