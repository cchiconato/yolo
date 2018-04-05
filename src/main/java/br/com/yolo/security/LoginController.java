package br.com.yolo.security;

import static br.com.yolo.utils.UrlController.URL_DOMAIN;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
* @author  Chrisitan Chiconato
* @since   2018-02-18
*/

@RestController
@CrossOrigin
public class LoginController {
	
	@PostMapping(value = "/api/public/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autentica(@RequestBody LoginDTO loginDTO) {
		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<LoginDTO> request = RequestEntity
				.post(URI.create(URL_DOMAIN + "/api/login"))
				.contentType(MediaType.APPLICATION_JSON).body(loginDTO);
		try {
			ResponseEntity<String> response = restTemplate.exchange(request, String.class);
			return ResponseEntity.ok(response.getHeaders().get(TokenAuthenticationService.AUTH_HEADER_NAME).get(0));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
}
