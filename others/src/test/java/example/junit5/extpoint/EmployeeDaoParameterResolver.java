package example.junit5.extpoint;

import example.junit5.entity.EmployeeJdbcDao;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * 自定义参数解析方法，注解会对构造函数和测试类传递的参数产生效果。该插件的效果是在 AfterEach 注解之后。
 * <p>
 * 在类的注释中，如果构造函数和方法参数中，都引用了注释，则生效顺序如下：<br/>
 * beforeAll -> <br/>
 * method1.constructor(Ext) ->  method1.beforeEach -> method1.Ext -> method1.running -> method1.afterEach <br/>
 * method2.constructor(Ext) ->  method2.beforeEach -> method2.Ext -> method2.running -> method2.afterEach <br/>
 * afterAll -> <br/>
 * </p>
 * <p>
 * Ext 的参数解析会在构造和方法中都会尝试调用
 */
public class EmployeeDaoParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
    System.err.println("verify the type of the parameter");
    return parameterContext.getParameter().getType()
        .equals(EmployeeJdbcDao.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext) throws ParameterResolutionException {
    System.err.println("defines the logic to obtain a parameter instance.");
    EmployeeJdbcDao employeeJdbcDao = new EmployeeJdbcDao();
    employeeJdbcDao.setConName("parseName");
    return employeeJdbcDao;
  }
}