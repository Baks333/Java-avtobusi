package com.dao;

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

public class FeedbackDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private HotelDAO hotelDAO;

    public FeedbackDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Feedback f){
        if(f.getId() > 0){
            String sql = "UPDATE Feedback SET user=?, date=?, text=?, hotelID=?, rating=? WHERE id='"+f.getId()+"';";
            jdbcTemplate.update(sql, f.getUser().getId(), f.getDate(), f.getText(), f.getHotel().getId(), f.getRating());
        } else {
            String sql = "INSERT INTO Feedback (user, date, text, hotelID, rating) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, f.getUser().getId(), f.getDate(), f.getText(), f.getHotel().getId(), f.getRating());
        }
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Feedback WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public List<Feedback> getFeedbackByHotel (int id){
        String sql = "SELECT * FROM Feedback WHERE hotelID ="+id;

        RowMapper<Feedback> rowMapper = new RowMapper<Feedback>() {
            @Override
            public Feedback mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int userID = resultSet.getInt("user");
                String date = resultSet.getString("date");
                String text = resultSet.getString("text");
                int hotelID = resultSet.getInt("hotelID");
                int rating = resultSet.getInt("rating");
                return new Feedback(id, usersDAO.getUserById(userID), date, text, hotelDAO.getHotelById(hotelID), rating);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Feedback> feedbackList (){
        String sql = "SELECT * FROM Feedback";

        RowMapper<Feedback> rowMapper = new RowMapper<Feedback>() {
            @Override
            public Feedback mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int userID = resultSet.getInt("user");
                String date = resultSet.getString("date");
                String text = resultSet.getString("text");
                int hotelID = resultSet.getInt("hotelID");
                int rating = resultSet.getInt("rating");
                return new Feedback(id, usersDAO.getUserById(userID), date, text, hotelDAO.getHotelById(hotelID), rating);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}
