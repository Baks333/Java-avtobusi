package com.dao;

import com.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConcertDAO {
    @Autowired
    private TourDAO tourDAO;
    private JdbcTemplate jdbcTemplate;
    public ConcertDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Concert c){
        if(c.getId() > 0){
            String sql = "UPDATE Concert SET title=?, band=?, location=?, poster=?, price=?, date=?, details=? WHERE id='"+c.getId()+"';";
            jdbcTemplate.update(sql, c.getTitle(), c.getBand(), c.getLocation(), c.getPoster(), c.getPrice(), c.getDate(), c.getDetails());
        } else {
            String sql = "INSERT INTO Concert (title, band, location, poster, price, date, details) VALUES (?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, c.getTitle(), c.getBand(), c.getLocation(), c.getPoster(), c.getPrice(), c.getDate(), c.getDetails());
        }
    }

    public List<Concert> getConcertByTitle (final String title){
        String sql = "SELECT * FROM Concert WHERE title='" + title +"';";
        RowMapper<Concert> rowMapper = new RowMapper<Concert>() {
            @Override
            public Concert mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String location = resultSet.getString("location");
                String band = resultSet.getString("band");
                String poster = resultSet.getString("poster");
                double price = resultSet.getDouble("price");
                String date = resultSet.getString("date");
                String details = resultSet.getString("details");
                return new Concert(id, title, band, location, poster, price, date, details);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<String> getTitles (){
        String sql = "SELECT DISTINCT title FROM Concert";
        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String title = resultSet.getString("title");
                return title;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Concert> getConcertByLocation (final String location){
        String sql = "SELECT * FROM Concert WHERE location='" + location +"';";
        RowMapper<Concert> rowMapper = new RowMapper<Concert>() {
            @Override
            public Concert mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String band = resultSet.getString("band");
                String poster = resultSet.getString("poster");
                double price = resultSet.getDouble("price");
                String date = resultSet.getString("date");
                String details = resultSet.getString("details");
                return new Concert(id, title, band, location, poster, price, date, details);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<String> getLocations (){
        String sql = "SELECT DISTINCT location FROM Concert";
        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String location = resultSet.getString("location");
                return location;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    public List<Concert> getConcertByBand (final String band){
        String sql = "SELECT * FROM Concert WHERE band='" + band +"';";
        RowMapper<Concert> rowMapper = new RowMapper<Concert>() {
            @Override
            public Concert mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String location = resultSet.getString("location");
                String poster = resultSet.getString("poster");
                double price = resultSet.getDouble("price");
                String date = resultSet.getString("date");
                String details = resultSet.getString("details");
                return new Concert(id, title, band, location, poster, price, date, details);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<String> getBands (){
        String sql = "SELECT DISTINCT Band FROM Concert";
        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String band = resultSet.getString("band");
                return band;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Concert getConcertById (final Integer id){
        String sql = "SELECT * FROM Concert WHERE id=" + id;
        ResultSetExtractor<Concert> extractor = new ResultSetExtractor<Concert>() {
            @Override
            public Concert extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String title = resultSet.getString("title");
                    String band = resultSet.getString("band");
                    String location = resultSet.getString("location");
                    String poster = resultSet.getString("poster");
                    double price = resultSet.getDouble("price");
                    String details = resultSet.getString("details");
                    String date = resultSet.getString("date");
                    return new Concert(id, title, band, location, poster, price, details, date);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Concert WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public List<Concert> concertList (){
        String sql = "SELECT * FROM Concert";

        RowMapper<Concert> rowMapper = new RowMapper<Concert>() {
            @Override
            public Concert mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String band = resultSet.getString("band");
                String location = resultSet.getString("location");
                String poster = resultSet.getString("poster");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                String date = resultSet.getString("date");
                return new Concert(id, title, band, location, poster, price, details, date);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

}
