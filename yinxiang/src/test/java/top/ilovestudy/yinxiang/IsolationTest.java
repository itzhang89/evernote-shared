package top.ilovestudy.yinxiang;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.ilovestudy.yinxiang.extension.ClearDatabaseExtension;

@ExtendWith({SpringExtension.class, ClearDatabaseExtension.class})
public class IsolationTest extends BaseSpringTest {
}
