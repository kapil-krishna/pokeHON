package services;

import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

public class DatabaseService {

    public Jdbi createJdbiConnection() {
            String hostname = System.getenv("HOST_NAME");
            String databaseName = System.getenv("DB_NAME");
            String port = "5432";

            String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + databaseName;
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "8xExhIZnASYEgXpdYa5G");

            return Jdbi.create(url, props);
    }
}
