package com.example.sportstournament.config;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * Runs after Hibernate has created the tables from the entity classes.
 *  1. db/routines.sql    : creates the FUNCTION, PROCEDURE and TRIGGER (every start)
 *  2. db/sample-data.sql : inserts sample data only when the teams table is empty
 */
@Component
public class DatabaseSetup implements ApplicationRunner {

    private final DataSource dataSource;

    public DatabaseSetup(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection c = dataSource.getConnection(); Statement st = c.createStatement()) {
            runScript(st, "db/routines.sql");
            System.out.println("Function, procedure and trigger are ready.");

            long teams;
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM teams")) {
                rs.next();
                teams = rs.getLong(1);
            }
            if (teams == 0) {
                runScript(st, "db/sample-data.sql");
                System.out.println("Sample data inserted.");
            }
        }
    }

    private void runScript(Statement st, String path) throws Exception {
        String sql = new String(new ClassPathResource(path).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        for (String stmt : split(sql)) {
            st.execute(stmt);
        }
    }

    /** Splits a SQL script and understands DELIMITER lines (needed for routines and triggers). */
    static List<String> split(String sql) {
        List<String> out = new ArrayList<>();
        String delim = ";";
        StringBuilder buf = new StringBuilder();
        for (String line : sql.split("\\r?\\n")) {
            String t = line.trim();
            if (t.toUpperCase().startsWith("DELIMITER ")) {
                delim = t.substring(10).trim();
                continue;
            }
            if (t.startsWith("--")) continue;
            buf.append(line).append('\n');
            String cur = buf.toString().trim();
            if (cur.endsWith(delim)) {
                String stmt = cur.substring(0, cur.length() - delim.length()).trim();
                if (!stmt.isEmpty()) out.add(stmt);
                buf.setLength(0);
            }
        }
        if (!buf.toString().trim().isEmpty()) out.add(buf.toString().trim());
        return out;
    }
}
