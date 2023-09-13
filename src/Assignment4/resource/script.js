document.addEventListener("DOMContentLoaded", function() {
	
	var submitButton = document.getElementById("myButton");
	var createBtn = document.getElementById("createBtn");
	var deleteBtn = document.getElementById("deleteBtn");
    var fileCreateBtn = document.getElementById("fileCreateBtn");
    var fileDeleteBtn = document.getElementById("fileDeleteBtn");
    var updateBtn = document.getElementById("updateBtn");
    var login = document.getElementById("login");
    var updateFileBtn = document.getElementById("updateFileBtn");

    login.addEventListener("click", function() {
        var name = document.getElementById("username1").value;
        var role = document.getElementById("role1").value;
        //var lastName = document.getElementById("lastName").value;
        var requestData = {
            name: name.toUpperCase(),
            role: role.toUpperCase()
        };

        if(name === "" && role === ""){
            alert("Username and role cannot be empty!");
            return;
        }

        localStorage.setItem(name, role);

        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();

        // Configure the request
        xhr.open("POST", "http://localhost:8080/user", true);
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



    submitButton.addEventListener("click", function() {
      	var name = document.getElementById("username").value;
		var role = document.getElementById("role").value;
		//var lastName = document.getElementById("lastName").value;
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
        xhr.open("POST", "http://localhost:8080/user", true);
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
        xhr.open("POST", "http://localhost:8080/fileSystem/create", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(requestData));
        console.log(JSON.stringify(requestData));
        // Define a callback function to handle the response
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var responseString = xhr.responseText;
                    console.log(responseString);

                    var content =  document.getElementById("tablearea");
                    var keyValuePairs = responseString
                        .slice(1, -1) // Remove curly braces
                        .split(', ')   // Split into key-value pairs

                    // Create an object to store the key-value pairs
                    var response = {};

                    // Iterate over the key-value pairs
                    for (var i = 0; i < keyValuePairs.length; i++) {
                        var pair = keyValuePairs[i].split('=');
                        var key = pair[0].trim();
                        var value = pair[1].trim();
                        response[key] = value;
                    }
                    var outputDiv = document.getElementById("output");
                    var header = document.createElement("header");
                    header.textContent = "Directory Table data:"
                    outputDiv.appendChild(header);
                    // Now you can iterate over the response object
                    for (var key in response) {
                        if (response.hasOwnProperty(key)) {
                            var keyValueText = key + ': ' + response[key];
                            var paragraph = document.createElement("p");
                            paragraph.textContent = keyValueText;
                            outputDiv.appendChild(paragraph);
                        }
                    }

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

    fileCreateBtn.addEventListener("click", function() {
        console.log("clicked");
        var filepath = document.getElementById("filepath").value;
        var fileName = document.getElementById("fileName").value;
        var requestData = {
            fileName: fileName,
            filepath: filepath
        };

        if(filepath === "" && fileName === ""){
            alert("Directory name and path cannot be empty!");
            return;
        }
        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();

        // Configure the request
        xhr.open("POST", "http://localhost:8080/fileSystem/directory/createFile", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(requestData));
        console.log(JSON.stringify(requestData));
        // Define a callback function to handle the response

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var response = xhr.responseText;
                    console.log(response);
                    //var content =  document.getElementById("tablearea");
                    //content.innerHTML(response);
                    alert("File created successfully!");
                } else {
                    console.error('Request failed:', xhr.status, xhr.statusText);
                }
            }
        };
    });

    fileDeleteBtn.addEventListener("click", function() {
        console.log("clicked");
        const filepath = document.getElementById("filepath").value;
        const fileName = document.getElementById("fileName").value;

        if(filepath === "" && fileName === ""){
            alert("Files name and path cannot be empty!");
            return;
        }
        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();
        var requestData = {
            fileName: fileName,
            filepath: filepath
        };

        // Configure the request
        xhr.open("POST", "http://localhost:8080/fileSystem/directory/deleteFile", true);
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

    updateBtn.addEventListener("click", function() {
        console.log("clicked");
        const filepath = document.getElementById("path").value;
        const fileName = document.getElementById("directoryName").value;

        if(filepath === "" && fileName === ""){
            alert("Directory name and path cannot be empty!");
            return;
        }

        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();
        var requestData = {
            fileName: fileName,
            filepath: filepath
        };

        // Configure the request
        xhr.open("POST", "http://localhost:8080/fileSystem/updateFile", true);
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

    updateFileBtn.addEventListener("click", function() {
        console.log("clicked");
        const filepath = document.getElementById("filepath").value;
        const fileName = document.getElementById("fileName").value;

        if(filepath === "" && fileName === ""){
            alert("File name and path cannot be empty!");
            return;
        }

        // Create an XMLHttpRequest object
        var xhr = new XMLHttpRequest();
        var requestData = {
            fileName: fileName,
            filepath: filepath
        };

        // Configure the request
        xhr.open("POST", "http://localhost:8080/fileSystem/directory/updateFile", true);
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
