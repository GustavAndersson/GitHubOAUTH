/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Stateless
public class GitHubOAuthBean {

    public JsonObject githubAuth(String token) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.github.com/user?access_token=" + token);
        JsonObject response = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        return response;
    }

    public String getToken(String code) {

        String clientID = "8b5b6eb55beb4ece90fe";
        String clientSecret = "2f33eabf4a74183987727ba57ceb60ed1865b872";
        String url = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s"
                , clientID,clientSecret,code);
        Client client = ClientBuilder.newClient();
        final String result = client.target(url)
                .request().post(null,String.class);
        //access_token=<TOKEN>&scope=&token_type=bearer
        return result.substring(13, result.indexOf("&"));
    }

}
