import java.util.*;
import java.io.*;
import java.net.*;

public class ApiFuzzer {
   public static void main(String[] args) {
      List<String> domains = Arrays.asList("http");
       
      List<String> subdomains = Arrays.asList("google", "api", "assets", "beta", "citrix", "dev", "login", "nas", "newsletter", 
         "owa", "pilot", "pop", "portal", "press", "staging", "support", "view", "vpn");
        
      List<String> paths = Arrays.asList(".net", "/", "/account-login", "/account/signin", "/admin", "/admin/login", "/admin/ping", "/api", "/api-docs", "/api/api-docs", 
         "/api/apidocs", "/api/auth/method", "/api/auth/privacy", "/api/docs/swagger/swagger.json", "/api/graphql", "/api/health", "/api/swagger-ui",
         "/api/swagger-ui.html", "/api/swagger/", "/api/swagger_doc.json", "/api/system/health", "/api/v1/", "/api/v1/api-docs", "/api/v1/apidocs",
         "/api/v1/auth", "/api/v1/authentication", "/api/v1/ping", "/api/v1/swagger", "/api/v1/swagger-ui", "/api/v1/swagger-ui.html", "/api/v1/swagger.json", "/api/v1/swagger/", "/api/v1/userInfo", 
         "/api/v2", "/api/v2/api-docs", "/api/v2/apidocs", "/api/v2/swagger", "/api/version", "/apidocs", "/apidocs/swagger.json", "/apis/", "/auth/check/", "/auth/info", "/auth/logout",
         "/customer/account/login", "/graph", "/graphql", "/graphql-devtools", "/graphql-explorer", "/graphql/schema.json", "/graphql/schema.xml", 
         "/graphql/schema.yaml", "/graphql/v1", "/health", "/health/alive", "/health/ready", "/healthcheck", "/login", "/member/login",
         "/metrics/prometheus", "/openapi/v2", "/swagger", "/swagger-ui", "/swagger-ui.json", "/swagger.json", "/swagger.yml", "/swagger/index.html",
         "/swagger/static/index.html", "/swagger/swagger.json", "/swagger/ui/index", "/swagger/v1/swagger.json");
   
      for (String domain : domains) {
         for (String subdomain : subdomains) {
            for (String path : paths) {
               String url = domain + "://" + subdomain + path;
               try {
                // Create URL object
                  URL apiUrl = new URL(url);
               
                  // Open connection
                  HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                                 
                        
                 // Set request method
                  connection.setRequestMethod("GET");
                        
                  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  String responseBody = "";
                  String inputLine;
                  while ((inputLine = in.readLine()) != null) {
                     responseBody += inputLine;
                  }
                  in.close();
                        
                  // Output results
                  System.out.println("Endpoint: " + url);
                  int responseCode = connection.getResponseCode(); // Get response code
                  System.out.println("Response Code: " + responseCode); 
                  System.out.println("Response Body: " + responseBody);                        
                 
                  // Close connection
                  connection.disconnect();
               
               } catch (IOException e){
               //Exception handeling
                  System.out.println("Error testing endpoint: " + url);
               }  
            }
         }              
      }
   }
}
