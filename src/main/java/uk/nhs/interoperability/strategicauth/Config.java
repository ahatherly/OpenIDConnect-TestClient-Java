package uk.nhs.interoperability.strategicauth;

public class Config {
	
	//public static final String authorizationLocation =
	//		"http://localhost:8088/auth/realms/test/protocol/openid-connect/auth";
	public static final String authorizationLocation =
			"http://localhost:8081/openam/oauth2/realms/test/authorize";
	
	public static final String clientId =
			"java-test-client";
	
	public static final String redirectURI =
			"http://localhost:8080/strategicauthclient/redirect";
	
	//public static final String tokenLocation =
	//		"http://localhost:8088/auth/realms/test/protocol/openid-connect/token";
	//public static final String tokenLocation =
	//		"http://localhost:8081/openam/oauth2/realms/test/tokeninfo";
	public static final String tokenLocation =
			"http://localhost:8081/openam/oauth2/realms/test/access_token";
	
	public static final String clientSecret =
			"b0035f1e-e98b-4825-887e-789061c0b341";

}
