package example.wiremock;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(WireMockServerExtension.class)
public class GetStartTest {

  private RestTemplate restTemplate = new RestTemplate();

  @Test
  public void exactUrlOnly() {
    // when
    stubFor(get(urlEqualTo("/some/thing"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "text/plain")
            .withBody("Hello world!")));

    // given
    String body = getRequest("/some/thing").getBody();

    // then
    Assert.assertEquals("Hello world!", body);

  }

  private ResponseEntity<String> getRequest(final String path) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<>(headers);

    return restTemplate.exchange("http://localhost:8080" + path, HttpMethod.GET, entity, String.class);
  }
}
