package util;
import java.io.IOException;

import jira.api.JiraApi;

import org.json.simple.parser.ParseException;

import github.api.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

//		Repositories.getRepositories();
		
//		Repositories.getReposJSON();
		
//		Repositories.gerRepoJackson();
		
//		Repositories.getJSONJacksonParser();
//		Repositories.getCommiters();
//		
//		
//		//JIRA Anbindung
//		JiraApi.getJira();
		
		Repositories.getCommitsJsonObjekt();
	}

}
