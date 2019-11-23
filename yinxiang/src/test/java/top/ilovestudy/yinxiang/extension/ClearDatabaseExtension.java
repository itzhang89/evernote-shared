package top.ilovestudy.yinxiang.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClearDatabaseExtension implements AfterEachCallback {

  private static final String ALL_TABLE_NAME_QUERY = "SELECT TABLENAME FROM PG_TABLES WHERE SCHEMANAME='public'";

  @Override
  public void afterEach(ExtensionContext context) {
    EntityManager entityManager = SpringExtension.getApplicationContext(context).getBean(EntityManagerFactory.class).createEntityManager();
    List<String> tableNames = getTableNamesFromDatabase(entityManager);
    cleanTablesData(entityManager, tableNames);
    entityManager.close();
  }

  @SuppressWarnings("unchecked")
  private List<String> getTableNamesFromDatabase(EntityManager entityManager) {
    return ((List<String>) entityManager.createNativeQuery(ALL_TABLE_NAME_QUERY).getResultList())
        .stream().collect(toList());
  }


  private void cleanTablesData(EntityManager entityManager, List<String> tableNames) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    tableNames.forEach(tableName -> entityManager.createNativeQuery(String.format("ALTER TABLE %s DISABLE TRIGGER ALL;", tableName)).executeUpdate());
    tableNames.forEach(tableName -> entityManager.createNativeQuery(String.format("DELETE FROM %s WHERE 1=1", tableName)).executeUpdate());
    tableNames.forEach(tableName -> entityManager.createNativeQuery(String.format("ALTER TABLE %s ENABLE TRIGGER ALL;", tableName)).executeUpdate());
    entityManager.createNativeQuery("SET CONSTRAINTS ALL IMMEDIATE").executeUpdate();
    transaction.commit();
  }
}
