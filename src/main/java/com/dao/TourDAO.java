package com.dao;

import com.model.Hotel;
import com.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TourDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private  ConcertDAO concertDAO;
    public TourDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Tour t){
        if(t.getId() > 0){
            String sql = "UPDATE Tour SET licensePlate=?, model=?, type=?, date=?, price=?, details=?, concertID=? WHERE id='"+t.getId()+"';";
            jdbcTemplate.update(sql, t.getLicensePlate(), t.getModel(), t.getType(), t.getDate(), t.getPrice(), t.getDetails(), t.getConcert().getId());
        } else {
            String sql = "INSERT INTO Tour (licensePlate, model, type, date, price, details, concertID) VALUES (?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, t.getLicensePlate(), t.getModel(), t.getType(), t.getDate(), t.getPrice(), t.getDetails(), t.getConcert().getId());
        }
    }



    public List<Tour> getTourByType (final String type){
        String sql = "SELECT * FROM Tour WHERE type='" + type +"';";
        RowMapper<Tour> rowMapper = new RowMapper<Tour>() {
            @Override
            public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String licensePlate = resultSet.getString("licensePlate");
                String model = resultSet.getString("model");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                int concertID = resultSet.getInt("concertID");
                return new Tour(id, licensePlate, model, type, date, price, details, concertDAO.getConcertById(concertID) );
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    public List<Tour> getTourByDate (final String date){
        String sql = "SELECT * FROM Tour WHERE date='" + date +"';";
        RowMapper<Tour> rowMapper = new RowMapper<Tour>() {
            @Override
            public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String licensePlate = resultSet.getString("licensePlate");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                int concertID = resultSet.getInt("concertID");
                return new Tour(id, licensePlate, model, type, date, price, details, concertDAO.getConcertById(concertID) );
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    public Tour getTourById (final Integer id){
        String sql = "SELECT * FROM Tour WHERE id=" + id;
        ResultSetExtractor<Tour> extractor = new ResultSetExtractor<Tour>() {
            @Override
            public Tour extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String licensePlate = resultSet.getString("licensePlate");
                    String model = resultSet.getString("model");
                    String type = resultSet.getString("type");
                    String date = resultSet.getString("date");
                    double price = resultSet.getDouble("price");
                    String details = resultSet.getString("details");
                    int concertID = resultSet.getInt("concertID");
                    return new Tour(id, licensePlate, model, type, date, price, details, concertDAO.getConcertById(concertID) );
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public List<Tour> tourList (){
        String sql = "SELECT * FROM Tour";

        RowMapper<Tour> rowMapper = new RowMapper<Tour>() {
            @Override
            public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String licensePlate = resultSet.getString("licensePlate");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                int concertID = resultSet.getInt("concertID");
                return new Tour(id, licensePlate, model, type, date, price, details, concertDAO.getConcertById(concertID) );
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
    public List<Tour> tourListByConcert (int concertID) {
        String sql = "SELECT * FROM Tour where concertID=" + concertID;

        RowMapper<Tour> rowMapper = new RowMapper<Tour>() {
            @Override
            public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String licensePlate = resultSet.getString("licensePlate");
                String model = resultSet.getString("model");
                String type = resultSet.getString("type");
                String date = resultSet.getString("date");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                int concertID = resultSet.getInt("concertID");
                return new Tour(id, licensePlate, model, type, date, price, details, concertDAO.getConcertById(concertID) );
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Tour WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }

}
