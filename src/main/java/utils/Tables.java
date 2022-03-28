package utils;

import exceptions.SQLConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Tables {
    private final Logger logger = Log.getLogger();

    public void createTables() {
        userTable();
        addressTable();
        companyTable();
        geoTable();
    }

    private void userTable() {
        final String sql = "  CREATE TABLE IF NOT EXISTS public.User \n" +
                "                (\n" +
                "                id                 SERIAL PRIMARY KEY NOT NULL,\n" +
                "                name               VARCHAR(100),\n" +
                "                username           VARCHAR(100),\n" +
                "                email              VARCHAR(100),\n" +
                "                user_address_id    INTEGER NOT NULL,\n" +
                "                phone              VARCHAR(100),\n" +
                "                website            VARCHAR(100),\n" +
                "                user_company_id    INTEGER NOT NULL\n" +
                "                )";
        executeUpdate(sql);
        logger.info("User table create");
    }

    private void addressTable() {
        final String sql = "  CREATE TABLE IF NOT EXISTS public.Address\n" +
                "                (\n" +
                "                address_id         SERIAL PRIMARY KEY NOT NULL,\n" +
                "                street             VARCHAR(100),\n" +
                "                suite              VARCHAR(100),\n" +
                "                city               VARCHAR(100),\n" +
                "                zipcode            VARCHAR(10),\n" +
                "                address_geo_id     INTEGER NOT NULL\n" +
                "                )";
        executeUpdate(sql);
        logger.info("Address table create");
    }

    private void companyTable() {
        final String sql = "  CREATE TABLE IF NOT EXISTS public.Company\n" +
                "                (\n" +
                "                company_id          SERIAL PRIMARY KEY NOT NULL,\n" +
                "                company_name        VARCHAR(100),\n" +
                "                catchPhrase         VARCHAR(100),\n" +
                "                bs                  VARCHAR(100)\n" +
                "                )";
        executeUpdate(sql);
        logger.info("Company table create");
    }

    private void geoTable() {
        final String sql = "  CREATE TABLE IF NOT EXISTS public.Geo\n" +
                "                (\n" +
                "                geo_id         SERIAL PRIMARY KEY NOT NULL,\n" +
                "                lat            FLOAT,\n" +
                "                lng            FLOAT\n" +
                "                )";
        executeUpdate(sql);
        logger.info("Geo table create");
    }


    private void executeUpdate(final String sql) {
        try (final Connection connection = JdbcUtils.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (final SQLException e) {
            logger.warning(e.getMessage());
            throw new SQLConnectionException(e);
        }
    }
}
