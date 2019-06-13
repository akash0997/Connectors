package Repo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Scanner;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;




public class CreateRepository
{
	public boolean createRepoJfrog(String repoKey) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut("https://dev9.jfrog.io/dev9/api/repositories/"+repoKey);
    String json;
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "AKCp5dK4h1kb4s4p2jNp8rERSeAT3Uv3Un3NV5zfb1TNUDxdpmdGZ7r2NvbWWxeYHmuv8To4R");
    json = "{\"rclass\" : \"local\"}";
    StringEntity entity = new StringEntity(json);
    httpPut.addHeader(new BasicScheme().authenticate(credentials, httpPut, null));
    httpPut.setEntity(entity);
    httpPut.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPut);
    int responseCode = response.getStatusLine().getStatusCode();
    System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_OK) {
        client.close();
        System.out.println("Repository created");
        return true;
    }
    else {
        client.close();
        return false;
    }
	}
	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException 
	{
		System.out.println("Enter name of repository to be created");
		Scanner sc = new Scanner(System.in);
		String name=sc.nextLine();
		System.out.println(new CreateRepository().createRepoJfrog(name));
		sc.close();
	}
}

