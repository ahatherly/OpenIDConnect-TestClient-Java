package uk.nhs.interoperability.strategicauth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;

import static uk.nhs.interoperability.strategicauth.Config.*;

/**
 * This servlet simply redirects the user to the authentication endpoint to authenticate
 * themselves. After they have been authenticated, they will be redirected to the
 * redirect URL specified.
 * 
 * @see https://cwiki.apache.org/confluence/display/OLTU/OAuth+2.0+Client+Quickstart
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Build OAuth End User Authorization Request
		try {
			
			OAuthClientRequest auth_request = OAuthClientRequest
					   .authorizationLocation(authorizationLocation)
					   .setClientId(clientId)
					   .setRedirectURI(redirectURI)
					   .setResponseType(ResponseType.CODE.toString())
					   .buildQueryMessage();
			
			response.sendRedirect(auth_request.getLocationUri());
			
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}	
	}
}
