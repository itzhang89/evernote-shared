package top.ilovestudy.yinxiang;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.ilovestudy.yinxiang.extension.ClearDatabaseExtension;

@ExtendWith({SpringExtension.class, ClearDatabaseExtension.class})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IsolationTest {
}
