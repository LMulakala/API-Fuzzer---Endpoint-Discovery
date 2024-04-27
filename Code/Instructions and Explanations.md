# How to Run
1. Copy and paste the code into any IDE
2. I highly recommend searching for other subdomains and paths to fuzz in order to thoroughly understand how endpoint discovery works
3. Put your chosen subdomains and paths in the List
4. Run the code and see the different response codes returned

# Explanation of Code
 
## URL Class 

```java
URL apiUrl = new URL(url);
HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
```
The java.net.URL class in Java represents a Uniform Resource Locator, which is a pointer to a resource on the World Wide Web. It provides methods for creating, parsing, and working with URLs.

In my code, I create a URL object (apiUrl) and then use the openConnection() method from the URL class. This method returns an object of the URLConnection class, representing a connection to the remote resource specified by the URL.
## 
## HttpURLConnection Class
java
connection.setRequestMethod("GET");
The HttpURLConnection class in Java is used to send and receive data over the HTTP protocol. It provides methods for performing HTTP-specific operations, such as setting request methods, headers, and handling responses.

The setRequestMethod("GET") method is used to define the type of action to be performed when making a request to the specified URL. By setting the request method to "GET", we indicate that we're making a simple request to retrieve data from the server, which is one of the most common HTTP methods.
## 
## Response Codes and Their Messages

```java
int responseCode = connection.getResponseCode();
System.out.println("Response Code: " + responseCode);
```

Response codes are messages sent by a server to a client (in this case, our program) to indicate the status of a request. They provide information about whether the request was successful, encountered an error, or needs further action.

The getResponseCode() method retrieves the response code from the server, which we then print to the console to indicate the status of the request.

```java
String responseMessage = connection.getResponseMessage();
System.out.println("Response Message: " + responseMessage);
```

In order to understand what the response code means, we also retrieve the response message using the getResponseMessage() method. This message provides additional context about the response code.

## 
## Response Body
```java
BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
String responseBody = "";
String inputLine;
while ((inputLine = in.readLine()) != null) {
    responseBody += inputLine;
}
in.close();
System.out.println("Response Body: " + responseBody);
```

The responseBody variable stores the contents of the website returned by the server in response to our HTTP request. It contains the data of the webpage we requested. However, it's important to note that this content can be empty, especially for certain types of requests or if the server does not return any content.

In the context of the API fuzzer, the responseBody allows us to inspect the data sent back by the server and analyze it for any potential issues or anomalies.


