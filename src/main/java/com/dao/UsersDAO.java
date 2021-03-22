package com.dao;

import com.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO {
    private JdbcTemplate jdbcTemplate;

    public UsersDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> usersList (){
        String sql = "SELECT * FROM User";

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                return new User(id, login, password, role, firstName, lastName);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<User> getUsersByRole(final String role){
        String sql = "SELECT * FROM User WHERE role='"+role+"';";

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                return new User(id, login, password, role, firstName, lastName);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User getUserById (final Integer id){
        String sql = "SELECT * FROM User WHERE id=" + id;
        ResultSetExtractor<User> extractor = new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    return new User(id, login, password, role, firstName, lastName);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public void saveOrUpdate (User u){
        if(u.getId() > 0){
            String sql = "UPDATE User SET login=?, password=?, role=?, firstName=?, lastName=? WHERE id='"+u.getId()+"';";
            jdbcTemplate.update(sql, u.getLogin(), u.getPassword(), u.getRole(), u.getFirstName(), u.getLastName());
        } else {
            String sql = "INSERT INTO User (login, password, role, firstName, lastName) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, u.getLogin(), u.getPassword(), u.getRole(), u.getFirstName(), u.getLastName());
        }
    }
    public int delete (Integer id){
        String sql = "DELETE FROM User WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public User findByUserName(final String login){
        String sql = "SELECT * FROM User WHERE login='" + login + "';";
        ResultSetExtractor<User> extractor = new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    return new User(id, login, password, role, firstName, lastName);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public List<String> getUsersRoles(){
        String sql = "SELECT DISTINCT Role FROM User";

        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String role = resultSet.getString("role");
                return role;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

}
