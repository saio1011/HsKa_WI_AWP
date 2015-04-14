package github.domain;

import java.util.Date;

public class Commit {
	private String CommiterName;
	private String Date;
	
	public Commit(String CommiterName, String date){
		CommiterName = CommiterName;
		Date = date;
	}
	
	public String getCommiterName() {
		return CommiterName;
	}

	public void setCommiterName(String commiterName) {
		CommiterName = commiterName;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	
	@Override
	public String toString() {
		return "Commit [CommiterName=" + CommiterName + ", Date=" + Date + "]";
	}

	
	

}
