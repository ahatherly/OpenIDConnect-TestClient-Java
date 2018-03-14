package uk.nhs.interoperability.strategicauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import static uk.nhs.interoperability.strategicauth.Config.*;

/**
 * This servlet is called once the user is authenticated, and calls the token endpoint to
 * request an Access token (i.e. Authorise access to specific scopes).
 * 
 * @see https://cwiki.apache.org/confluence/display/OLTU/OAuth+2.0+Client+Quickstart
 */
public class AuthenticationRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationRedirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get Authorization Code from redirect URI
		try {
			OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
			String code = oar.getCode();
			
			// Exchange OAuth ID token for an Access token:
			OAuthClientRequest authz_request = OAuthClientRequest
	                .tokenLocation(tokenLocation)
	                .setGrantType(GrantType.AUTHORIZATION_CODE)
	                .setClientId(clientId)
	                .setClientSecret(clientSecret)
	                .setRedirectURI(redirectURI)
	                .setCode(code)
	                .buildBodyMessage();
			
			//create OAuth client that uses custom http client under the hood
	        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
	
	        OAuthJSONAccessTokenResponse oAuthResponse = oAuthClient.accessToken(authz_request);
	
			response.getWriter().append("Token: ").append(oAuthResponse.getAccessToken());
		} catch (OAuthProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
