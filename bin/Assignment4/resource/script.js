document.addEventListener("DOMContentLoaded", function() {
	
	var submitButton = document.getElementById("myButton");
	var createBtn = document.getElementById("createBtn");
	var deleteBtn = document.getElementById("deleteBtn");

	

   submitButton.addEventListener("click", function() {
      	var name = document.getElementById("username").value;
	var role = document.getElementById("role").value;
	    var requestData = {
	            name: name.toUpperCase(),
	            role: role.toUpperCase()
	        };
	        
	        if(name === "" && role === ""){
			alert("Username and role cannot be empty!");
			return;
		}
        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();

        // Configure the request
        xhr.open("POST", "http://localhost:8080/user", true)
        ;
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.send(JSON.stringify(requestData));
        console.log(JSON.stringify(requestData));
        // Define a callback function to handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var response = xhr.responseText;
                    console.log(response);
                    alert(response);
                } else {
                    console.error('Request failed:', xhr.status, xhr.statusText);
                }
            }
        };
    });
    
     createBtn.addEventListener("click", function() {
     console.log("clicked");
      var path = document.getElementById("path").value;
	var directoryName = document.getElementById("directoryName").value;
	    var requestData = {
	            directoryName: directoryName,
	            path: path
	        };
	        
        if(path === "" && directoryName === ""){
			alert("Directory name and path cannot be empty!");
			return;
		}
        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();

        // Configure the request
        xhr.open("POST", "http://localhost:8080/fileSystem/create", true)
        ;
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.send(JSON.stringify(requestData));
        console.log(JSON.stringify(requestData));
        // Define a callback function to handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var response = xhr.responseText;
                    console.log(response);
                    alert("Directory created successfully!");
                } else {
                    console.error('Request failed:', xhr.status, xhr.statusText);
                }
            }
        };

    });
    
       deleteBtn.addEventListener("click", function() {
          var path = document.getElementById("path").value;
	var directoryName = document.getElementById("directoryName").value;
	
		if(path === "" && directoryName === ""){
			alert("Directory name and path cannot be empty!");
			return;
		}
	
        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();
 var requestData = {
	            directoryName: directoryName,
	            path: path
	        };
	        
	       
	        
        // Configure the request
        xhr.open("POST", "http://localhost:8080/fileSystem/delete", true);
        xhr.setRequestHeader("Content-Type", "application/json");

       xhr.send(JSON.stringify(requestData));
       console.log(JSON.stringify(requestData));
        
        // Define a callback function to handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var response = xhr.responseText;
                    console.log(response);
                    alert(response);
                } else {
                    console.error('Request failed:', xhr.status, xhr.statusText);
                }
            }
        };

    });
    
});