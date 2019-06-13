import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ApacheGitLab 
{
	public boolean createPageGitlab(String pageTitle) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("https://gitlab.com/api/v4/projects?private_token=samLNyP-KJm_SKqdkkEp");
    
    
    //UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("papunpikun@gmail.com", "ZjbyFpQa2rDWTYyS0FTID592");
    
		
    String urlParameters = "name="+pageTitle;
    StringEntity entity = new StringEntity(urlParameters);
   
    //httpPost.addHeader(new BasicScheme().authenticate(credentials, httpPost, null));
    
    httpPost.setEntity(entity);
    
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
    httpPost.setHeader("charset", "utf-8");
    httpPost.setHeader("PRIVATE-TOKEN", "samLNyP-KJm_SKqdkkEp");
    
    CloseableHttpResponse response = client.execute(httpPost);
    
    int responseCode = response.getStatusLine().getStatusCode();
    System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_CREATED) 
    {
        client.close();
        return true;
    }
    else 
    {
        client.close();
        return false;
    	}
	}
	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException 
	{
		System.out.println(new ApacheGitLab().createPageGitlab("New Page"));
	}
}
