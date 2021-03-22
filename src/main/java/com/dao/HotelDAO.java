package com.dao;

import com.model.Concert;
import com.model.Feedback;
import com.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotelDAO {

    @Autowired
    private TourDAO tourDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;
    private JdbcTemplate jdbcTemplate;

    public HotelDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Hotel h){
        if(h.getId() > 0){
            String sql = "UPDATE Hotel SET title=?, star=?, rating=?,  hotelImg=?, address=?, phoneNumber=?, details=?, tourID=? WHERE id='"+h.getId()+"';";
            jdbcTemplate.update(sql, h.getTitle(), h.getStar(), h.getRating(),  h.getHotelImg(), h.getAddress(), h.getPhoneNumber(), h.getDetails(), h.getTour().getId());
        } else {
            String sql = "INSERT INTO Hotel (title, star, rating, hotelImg, address, phoneNumber, details, tourID) VALUES (?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, h.getTitle(), h.getStar(), h.getRating(),  h.getHotelImg(), h.getAddress(), h.getPhoneNumber(), h.getDetails(), h.getTour().getId());
        }
    }


    public List<Hotel> getHotelByTitle (final String title){
        String sql = "SELECT * FROM Hotel WHERE title='" + title +"';";
        RowMapper<Hotel> rowMapper = new RowMapper<Hotel>() {
            @Override
            public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int star = resultSet.getInt("star");
                double rating = resultSet.getDouble("rating");
                String hotelImg = resultSet.getString("hotelImg");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String details = resultSet.getString("details");
                int tourID = resultSet.getInt("tourID");
                return new Hotel(id, title, star, rating, hotelImg, address, phoneNumber, details, tourDAO.getTourById(tourID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<String> getTitles (){
        String sql = "SELECT DISTINCT title FROM Hotel";
        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String title = resultSet.getString("title");
                return title;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }



    public List<Integer> getStars (){
        String sql = "SELECT DISTINCT star FROM Hotel";
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer star = resultSet.getInt("star");
                return star;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Hotel getHotelById (int id){
        String sql = "SELECT * FROM Hotel WHERE id=" + id;
        ResultSetExtractor<Hotel> extractor = new ResultSetExtractor<Hotel>() {
            @Override
            public Hotel extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String title = resultSet.getString("title");
                    int star = resultSet.getInt("star");
                    double rating = resultSet.getDouble("rating");
                    String hotelImg = resultSet.getString("hotelImg");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String details = resultSet.getString("details");
                    int tourID = resultSet.getInt("tourID");
                    return new Hotel(id, title, star, rating, hotelImg, address, phoneNumber, details, tourDAO.getTourById(tourID));
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Hotel WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public void updateHotelRating(Hotel h){
        List<Feedback> listFeedback = feedbackDAO.getFeedbackByHotel(h.getId());
        int rating = 0;
        for (Feedback feedback: listFeedback){
            rating += feedback.getRating();
        }
        double newRating = rating/listFeedback.size();
        newRating = Math.rint(10.0*newRating)/10.0; //Округление до десятых
        String sql = "UPDATE Hotel SET rating =? WHERE id ='"+h.getId()+"';";
        jdbcTemplate.update(sql, newRating);
    }

    public List<Hotel> hotelList (){
        String sql = "SELECT * FROM Hotel";

        RowMapper<Hotel> rowMapper = new RowMapper<Hotel>() {
            @Override
            public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int star = resultSet.getInt("star");
                double rating = resultSet.getDouble("rating");
                String hotelImg = resultSet.getString("hotelImg");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String details = resultSet.getString("details");
                int tourID = resultSet.getInt("tourID");
                return new Hotel(id, title, star, rating, hotelImg, address, phoneNumber, details, tourDAO.getTourById(tourID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Hotel> hotelListByTour (int tourID){
        String sql = "SELECT * FROM Hotel where tourID=" + tourID;

        RowMapper<Hotel> rowMapper = new RowMapper<Hotel>() {
            @Override
            public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int star = resultSet.getInt("star");
                double rating = resultSet.getDouble("rating");
                String hotelImg = resultSet.getString("hotelImg");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String details = resultSet.getString("details");
                int tourID = resultSet.getInt("tourID");
                return new Hotel(id, title, star, rating, hotelImg, address, phoneNumber, details, tourDAO.getTourById(tourID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

}
