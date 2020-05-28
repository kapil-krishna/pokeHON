package com.pokehon;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class DatabaseService {

    public Jdbi createJdbiConnection() {
            String hostname = System.getenv("HOST_NAME");
            String databaseName = System.getenv("DB_NAME");
            String port = "5432";

            String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + databaseName;
            Properties props = new Properties();
            props.setProperty("user", System.getenv("USERNAME"));
            props.setProperty("password", System.getenv("PASSWORD"));

            return Jdbi.create(url, props);
    }
}
