package example.junit5.extpoint;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * 会在每个测试之前运行判断该测试是否通过
 */
public class EnvironmentExtension implements ExecutionCondition {

  @Override
  public ConditionEvaluationResult evaluateExecutionCondition(
      ExtensionContext context) {

    String env = System.getProperty("env");

    if ("qa".equalsIgnoreCase(env)) {
      System.out.println("not in the qa environment");
      return ConditionEvaluationResult
          .disabled("Test disabled on QA environment");
    }

    System.out.println("in the qa environment");
    return ConditionEvaluationResult.enabled(
        "Test enabled on QA environment");
  }
}