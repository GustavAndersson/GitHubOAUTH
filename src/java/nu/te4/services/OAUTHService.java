package nu.te4.services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.beans.GitHubOAuthBean;

@Path("/")
public class OAUTHService {

    @EJB
    GitHubOAuthBean gitHubOAuthBean;
    
    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents(@QueryParam("token") String token) {
        return Response.ok(gitHubOAuthBean.githubAuth(token)).build();
    }    
        
    @GET
    @Path("token")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getToken(@QueryParam("code") String code) {
        return Response.ok(gitHubOAuthBean.getToken(code)).build();
    }   
    
}
