package com.dao;

import com.model.Hotel;
import com.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private HotelDAO hotelDAO;

    public RoomDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Room r){
        if(r.getId() > 0){
            String sql = "UPDATE Room SET capacity=?, price=?, roomImg=?, facilities=?, hotelID=?  WHERE id='"+r.getId()+"';";
            jdbcTemplate.update(sql, r.getCapacity(), r.getPrice(), r.getRoomImg(), r.getFacilities(), r.getHotel().getId());
        } else {
            String sql = "INSERT INTO Room (capacity, price, roomImg, facilities, hotelID) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, r.getCapacity(), r.getPrice(), r.getRoomImg(), r.getFacilities(), r.getHotel().getId());
        }
    }


    public List<Room> getRoomByCapacity (final int capacity){
        String sql = "SELECT * FROM Room WHERE capacity=" + capacity;
        RowMapper<Room> rowMapper = new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                double price = resultSet.getInt("price");
                String roomImg = resultSet.getString("roomImg");
                String facilities = resultSet.getString("facilities");
                int hotelID = resultSet.getInt("hotelID");

                return new Room(id, capacity, price, roomImg, facilities, hotelDAO.getHotelById(hotelID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Integer> getCapacities (){
        String sql = "SELECT DISTINCT capacity FROM Room";
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer capacity = resultSet.getInt("capacity");
                return capacity;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    public List<Room> roomList (){
        String sql = "SELECT * FROM Room";

        RowMapper<Room> rowMapper = new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int capacity = resultSet.getInt("capacity");
                double price = resultSet.getInt("price");
                String roomImg = resultSet.getString("roomImg");
                String facilities = resultSet.getString("facilities");
                int hotelID = resultSet.getInt("hotelID");

                return new Room(id, capacity, price, roomImg, facilities, hotelDAO.getHotelById(hotelID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Room> roomListByHotel (int hotelID){
        String sql = "SELECT * FROM Room where hotelID=" + hotelID;

        RowMapper<Room> rowMapper = new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int capacity = resultSet.getInt("capacity");
                double price = resultSet.getInt("price");
                String roomImg = resultSet.getString("roomImg");
                String facilities = resultSet.getString("facilities");
                int hotelID = resultSet.getInt("hotelID");

                return new Room(id, capacity, price, roomImg, facilities, hotelDAO.getHotelById(hotelID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    public int delete (Integer id){
        String sql = "DELETE FROM Room WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }

    public Room getRoomById (final Integer id){
        String sql = "SELECT * FROM Room WHERE id=" + id;
        ResultSetExtractor<Room> extractor = new ResultSetExtractor<Room>() {
            @Override
            public Room extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int capacity = resultSet.getInt("capacity");
                    double price = resultSet.getInt("price");
                    String roomImg = resultSet.getString("roomImg");
                    String facilities = resultSet.getString("facilities");
                    int hotelID = resultSet.getInt("hotelID");

                    return new Room(id, capacity, price, roomImg, facilities, hotelDAO.getHotelById(hotelID));
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

}
