# SqlStatementLogger-warn-reproduce

- This project is to reproduce the warning log of hibernate `SqlStatementLogger`
- You can reproduce the warning log by starting this application `com.me.sqlstatementloggerwarnreproduce.Application.main`

## Occurrence environment
- spring-boot:3.1.0
- hibernate-core:6.2.2.Final
- in `application.yaml`: `spring.jpa.properties.hibernate.format_sql: true`
- When using `UNION` and `ORDER BY` together with native query.
    ```sql
    (SELECT * FROM a_entity a1)
    UNION
    (SELECT * FROM a_entity a2)
    ORDER BY id ASC
    ```

## Warning log

- The waring log occurs in the `org.hibernate.engine.jdbc.spi.SqlStatementLogger#logStatement(java.lang.String, org.hibernate.engine.jdbc.internal.Formatter)` method.
  ```sql
  if ( format ) {
    statement = formatter.format( statement );
  }
  ```
- The following warning log has occurred.
  ```shell
  yyyy-MM-ddTHH:mm:ss.SSS+09:00  WARN 63191 --- [           main] org.hibernate.SQL                        : Couldn't format statement
  
  java.lang.IllegalArgumentException: count is negative: -1
      at java.base/java.lang.String.repeat(String.java:4415) ~[na:na]
      at org.hibernate.engine.jdbc.internal.BasicFormatterImpl$FormatProcess.newline(BasicFormatterImpl.java:405) ~[hibernate-core-6.2.2.Final.jar:6.2.2.Final]
      at org.hibernate.engine.jdbc.internal.BasicFormatterImpl$FormatProcess.beginNewClause(BasicFormatterImpl.java:329) ~[hibernate-core-6.2.2.Final.jar:6.2.2.Final]
      at org.hibernate.engine.jdbc.internal.BasicFormatterImpl$FormatProcess.perform(BasicFormatterImpl.java:157) ~[hibernate-core-6.2.2.Final.jar:6.2.2.Final]
      at org.hibernate.engine.jdbc.internal.BasicFormatterImpl.format(BasicFormatterImpl.java:33) ~[hibernate-core-6.2.2.Final.jar:6.2.2.Final]
      at org.hibernate.engine.jdbc.spi.SqlStatementLogger.logStatement(SqlStatementLogger.java:123) ~[hibernate-core-6.2.2.Final.jar:6.2.2.Final]
      at org.hibernate.engine.jdbc.spi.SqlStatementLogger.logStatement(SqlStatementLogger.java:106) ~[hibernate-core-6.2.2.Final.jar:6.2.2.Final]
  ```
