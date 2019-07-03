package org.xtremebiker.keycloakuserretrieval;

import java.net.URI;
import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private KeycloakRestTemplate restTemplate;

	private String keycloakServerUrl;

	public UserController(KeycloakRestTemplate restTemplate,
			@Value("${keycloak.auth-server-url}") String keycloakServerUrl) {
		this.restTemplate = restTemplate;
		this.keycloakServerUrl = keycloakServerUrl;
	}

	@GetMapping("/api/users")
	public List users() {
		return restTemplate.getForEntity(URI.create(keycloakServerUrl + "/admin/realms/myrealm/users"), List.class)
				.getBody();
	}

}
