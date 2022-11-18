package io.test.money_transfer.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PostgresDbCleaner {

    private static final List<TableData> TABLES_TO_IGNORE = List.of(new TableData("databasechangelog", "public"), new TableData("databasechangeloglock", "public"));

    private final DataSource dataSource;

    public void cleanDatabase() {
        try (var connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            var tablesToClean = loadTablesToClean(connection);
            cleanTablesData(tablesToClean, connection);
            connection.commit();
        } catch (SQLException e) {
            log.error(String.format("Failed to clean database due to error: \"%s\"", e.getMessage()));
            e.printStackTrace();
        }
    }

    private List<TableData> loadTablesToClean(final Connection connection) throws SQLException {
        var databaseMetaData = connection.getMetaData();
        var resultSet = databaseMetaData
                .getTables(connection.getCatalog(), null, null, new String[]{"TABLE"});

        var tablesToClean = new ArrayList<TableData>();
        while (resultSet.next()) {
            var table = new TableData(
                    resultSet.getString("TABLE_NAME"),
                    resultSet.getString("TABLE_SCHEM")
            );
            if (!TABLES_TO_IGNORE.contains(table)) {
                tablesToClean.add(table);
            }
        }

        return tablesToClean;
    }

    private void cleanTablesData(final List<TableData> tablesNames, final Connection connection) throws SQLException {
        if (tablesNames.isEmpty()) {
            return;
        }
        var stringBuilder = new StringBuilder("TRUNCATE ");
        for (int i = 0; i < tablesNames.size(); i++) {
            if (i == 0) {
                stringBuilder.append(tablesNames.get(i).getFullyQualifiedTableName());
            } else {
                stringBuilder
                        .append(", ")
                        .append(tablesNames.get(i).getFullyQualifiedTableName());
            }
        }
        connection.prepareStatement(stringBuilder.toString()).execute();
    }

    @Data
    private static class TableData {
        private final String name;
        private final String schema;

        public String getFullyQualifiedTableName() {
            return schema + "." + name;
        }
    }
}