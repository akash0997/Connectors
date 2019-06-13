import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Scanner;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;




public class CreatePage 
{
	public int createPageConfluence(String pageTitle, String confluenceSpace) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("https://papunpikun.atlassian.net/wiki/rest/api/content");
    
    
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("papunpikun@gmail.com", "ZjbyFpQa2rDWTYyS0FTID592");
    
    String json;
    json = "{\"type\":\"page\", \"title\":\""+pageTitle+"\", \"space\":{\"key\":\""+confluenceSpace+"\"}}";
    StringEntity entity = new StringEntity(json);
   
    httpPost.addHeader(new BasicScheme().authenticate(credentials, httpPost, null));
    
    httpPost.setEntity(entity);
    
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPost);
    
    int responseCode = response.getStatusLine().getStatusCode();
    //System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK) 
    {
        client.close();      
    }
    else 
    {
        client.close();
        
    }
    return responseCode;
	}
	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Page Name:");
		String name=sc.next();
		int result=new CreatePage().createPageConfluence(name, "DIV");
		if(result==200 || result==201)
		{
			System.out.println("Page Created!!");
		}
		else
		{
			System.out.println("Error!!");
		}
	}
}
