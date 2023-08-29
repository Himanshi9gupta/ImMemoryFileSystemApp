# InMemoryFileSystemApp
****************** START APPLICATION *********************

1. Open project folder in eclipse. 

2. Open application.java and run the application, open the console in eclipse and you should see
   "Server started at http://localhost:8080/".
   
3. Backend server is running now, then click on index.html file and it will open in browser tab.


************************ APP OVERVIEW ************************

1. This application is not using any db to store users or files list. For now HashMaps has been used for 
   mapping data. 
   
2. "Add" btn: use this button to add user in the hashmap. It will populate an alert message saying that, 
   the user has been added successfully.
 
3. After adding user, now you can create or delete a directory based on user permissions. 

4. you can validate the functionality in eclipse console. 
   (Delete functionality is being called twice, I will fix that issue later)
 
 
 ***************************** DB connection prerequisites ******************************
 Rubric: 
 
 Add jar file to the classpath and add org.sqlite.JDBC variable with path as jar file 
 
 jar file has been added in the sqlite --> Java folder

I am working on storing data in db, currently connection is closing by itself after establishing connection with db and I am looking into the issue. 

Update: the issue is now resolved and it is now able to store user and directory in the db tables. To create directory, please add the user input in the firstName and lastName to pass username to create directory.
