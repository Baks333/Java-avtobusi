package com.dao;

import com.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private TourDAO tourDAO;
    @Autowired
    private ConcertDAO concertDAO;

    public OrderDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Order o){
        if(o.getId() > 0){
            String sql = "UPDATE Orders SET concertId=?, tourId=?, userId=?, roomId=?, cost=?, status=? WHERE id='"+o.getId()+"';";
            jdbcTemplate.update(sql, o.getConcert().getId(), o.getTour().getId(), o.getUser().getId(), o.getRoom().getId(), o.getCost(), o.getStatus());
        } else {
            String sql = "INSERT INTO Orders (concertId, tourId, userId, roomId, cost, status) VALUES (?,?,?,?,?,?)";
            jdbcTemplate.update(sql, o.getConcert().getId(), o.getTour().getId(), o.getUser().getId(), o.getRoom().getId(), o.getCost(), o.getStatus());
        }
    }

    public void payStatus (Order o, String newStatus){
            String sql = "UPDATE Orders SET status=? WHERE id='"+o.getId()+"';";
            jdbcTemplate.update(sql, newStatus);
    }

    public Order getOrderById (final Integer id){
        String sql = "SELECT * FROM Orders WHERE id=" + id;
        ResultSetExtractor<Order> extractor = new ResultSetExtractor<Order>() {
            @Override
            public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int concertID = resultSet.getInt("concertID");
                    int tourId = resultSet.getInt("tourId");
                    int userId = resultSet.getInt("userId");
                    int roomId = resultSet.getInt("roomId");
                    String status = resultSet.getString("status");
                    double cost = resultSet.getDouble("cost");
                    return new Order(id, concertDAO.getConcertById(concertID), tourDAO.getTourById(tourId), usersDAO.getUserById(userId), roomDAO.getRoomById(roomId), cost, status);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public List<Order> getOrderByUser (final int userId){
        String sql = "SELECT * FROM Orders WHERE userId=" + userId;
        RowMapper<Order> rowMapper = new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int concertID = resultSet.getInt("concertID");
                int tourId = resultSet.getInt("tourId");
                int roomId = resultSet.getInt("roomId");
                String status = resultSet.getString("status");
                double cost = resultSet.getDouble("cost");
                return new Order(id, concertDAO.getConcertById(concertID), tourDAO.getTourById(tourId), usersDAO.getUserById(userId), roomDAO.getRoomById(roomId) , cost, status);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Orders WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }



    public List<Order> orderList (){
        String sql = "SELECT * FROM Orders";

        RowMapper<Order> rowMapper = new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int concertID = resultSet.getInt("concertID");
                int tourId = resultSet.getInt("tourId");
                int userId = resultSet.getInt("userId");
                int roomId = resultSet.getInt("roomId");
                String status = resultSet.getString("status");
                double cost = resultSet.getDouble("cost");
                return new Order(id, concertDAO.getConcertById(concertID), tourDAO.getTourById(tourId), usersDAO.getUserById(userId), roomDAO.getRoomById(roomId) , cost, status);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


}
