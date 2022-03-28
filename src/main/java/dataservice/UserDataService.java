package dataservice;

import exceptions.SQLConnectionException;
import utils.JdbcUtils;
import pojo.AddressPOJO;
import pojo.CompanyPOJO;
import pojo.GeoPOJO;
import pojo.UserPOJO;
import utils.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class UserDataService {
    private final Logger logger = Log.getLogger();

    public void save(final List<UserPOJO> users) {
        logger.info("save users");
        final Set<Integer> existingID = getExistingID();
        final String sql = "INSERT INTO public.User(id, name, username, email, user_address_id, phone, website, user_company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql)) {
            for (final UserPOJO user : users) {
                if (!existingID.contains(user.getId())) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getUsername());
                    preparedStatement.setString(4, user.getEmail());
                    preparedStatement.setInt(5, saveAddress(user.getAddress()));
                    preparedStatement.setString(6, user.getPhone());
                    preparedStatement.setString(7, user.getWebsite());
                    preparedStatement.setInt(8, saveCompany(user.getCompany()));
                    preparedStatement.executeUpdate();
                }else{
                    logger.warning("Already exists in the database: "+user);
                }
            }
        } catch (final SQLException e) {
            logger.warning("save users -> " + e.getMessage());
            throw new SQLConnectionException(e);
        }
        logger.info("save users done");
    }

    private int saveAddress(final AddressPOJO address) {
        final String sql = "INSERT INTO public.Address(street, suite, city, zipcode, address_geo_id) VALUES (?, ?, ?, ?, ?)";

        try (final PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getSuite());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getZipcode());
            preparedStatement.setInt(5, saveGeo(address.getGeo()));
            preparedStatement.executeUpdate();

            try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt("address_id");
            }
        } catch (final SQLException e) {
            logger.warning("save address -> " + e.getMessage());
            throw new SQLConnectionException(e);
        }
    }

    private int saveCompany(final CompanyPOJO company) {
        final String sql = "INSERT INTO public.Company(company_name, catchPhrase, bs) VALUES (?, ?, ?)";

        try (final PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getCatchPhrase());
            preparedStatement.setString(3, company.getBs());
            preparedStatement.executeUpdate();

            try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt("company_id");
            }
        } catch (final SQLException e) {
            logger.warning("save company -> " + e.getMessage());
            throw new SQLConnectionException(e);
        }
    }

    private int saveGeo(final GeoPOJO geo) {
        final String sql = "INSERT INTO public.Geo(lat, lng) VALUES (?, ?)";

        try (final PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, geo.getLat());
            preparedStatement.setDouble(2, geo.getLng());
            preparedStatement.executeUpdate();

            try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt("geo_id");
            }
        } catch (final SQLException e) {
            logger.warning("save geo -> " + e.getMessage());
            throw new SQLConnectionException(e);
        }
    }

    public Set<Integer> getExistingID() {
        final String sql = "SELECT id FROM public.user";
        final Set<Integer> result = new HashSet<>();
        try (final PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(sql);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(resultSet.getInt("id"));
            }

        } catch (final SQLException e) {
            logger.warning("getExistingID -> " + e.getMessage());
            throw new SQLConnectionException(e);
        }
        return result;
    }
}
