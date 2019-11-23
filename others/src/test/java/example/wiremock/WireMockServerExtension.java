package example.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class WireMockServerExtension implements AfterAllCallback, BeforeAllCallback {
  public WireMockRule wireMockRule = new WireMockRule();

  @Override
  public void afterAll(ExtensionContext context) {
    wireMockRule.stop();
  }

  @Override
  public void beforeAll(ExtensionContext context) {
    wireMockRule.start();
  }
}
