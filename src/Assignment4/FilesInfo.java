package Assignment4;

import java.util.Date;

public interface FilesInfo{

	String getFileName(); 
	
	String getUser();
	
	Integer getSize();
	
	String getpath();
	
	Date getModifiedDate();
	
	Date getCreatedDate();
	
}
