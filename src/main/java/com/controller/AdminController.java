package com.controller;

import com.dao.*;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("/admin")
public class AdminController {

    @Autowired
    private ConcertDAO concertDAO;
    @Autowired
    private TourDAO tourDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UsersDAO usersDAO;

    //Полный список пользователей
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public ModelAndView listUsers(ModelAndView model){
        List<User> listUsers = usersDAO.usersList();
        model.addObject("usersRoles", usersDAO.getUsersRoles());
        model.addObject("listUsers", listUsers);
        model.setViewName("adminViews/listUsers");
        return model;
    }

    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    public ModelAndView listOrders(ModelAndView model){
        List<Order> listOrders = orderDAO.orderList();
        model.addObject("listOrders", listOrders);
        model.setViewName("adminViews/listOrders");
        return model;
    }

    //Список пользователей по выбранной роли
    @RequestMapping(value = "/listUsersByRole", method = RequestMethod.GET)
    public ModelAndView listClothesClientByType(HttpServletRequest request, ModelAndView model){
        String role = request.getParameter("role");
        List<User> listUsersByRole = usersDAO.getUsersByRole(role);
        model.addObject("usersRoles", usersDAO.getUsersRoles());
        model.addObject("listUsers", listUsersByRole);
        model.setViewName("adminViews/listUsers");

        return model;
    }


    //Редактирование пользователя
    @RequestMapping(value = "/editRole", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        User user = usersDAO.getUserById(userID);
        ModelAndView model = new ModelAndView("adminViews/editRole");
        model.addObject("user", user);

        return model;
    }

    //Удаление пользователя
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        usersDAO.delete(userID);
        return new ModelAndView("redirect:/listUsers");
    }


    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public ModelAndView deleteOrder(HttpServletRequest request) {
        int orderID = Integer.parseInt(request.getParameter("id"));
        orderDAO.delete(orderID);
        return new ModelAndView("redirect:/listOrders");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) {
        usersDAO.saveOrUpdate(user);
        return new ModelAndView("redirect:/listUsers");
    }

    //Добавление одежды
    @RequestMapping(value = "/newConcert", method = RequestMethod.GET)
    public ModelAndView newConcert(ModelAndView model) {
        Concert newConcert = new Concert();
        model.addObject("concert", newConcert);
        model.setViewName("adminViews/newConcert");
        return model;
    }

    //Редактирование одежды
    @RequestMapping(value = "/editConcert", method = RequestMethod.GET)
    public ModelAndView editConcert(HttpServletRequest request) {
        int concertId = Integer.parseInt(request.getParameter("id"));
        Concert concert = concertDAO.getConcertById(concertId);
        ModelAndView model = new ModelAndView("adminViews/editConcert");
        model.addObject("concert", concert);
        return model;
    }

    //Удаление одежды
    @RequestMapping(value = "/deleteConcert", method = RequestMethod.GET)
    public ModelAndView deleteConcert(HttpServletRequest request) {
        int concertId = Integer.parseInt(request.getParameter("id"));
        concertDAO.delete(concertId);
        return new ModelAndView("redirect:/listConcerts");
    }


    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveConcert", method = RequestMethod.POST)
    public ModelAndView saveClothes(@ModelAttribute Concert concert) {
        concertDAO.saveOrUpdate(concert);
        return new ModelAndView("redirect:/listConcerts");
    }



    @RequestMapping(value = "/newRoom", method = RequestMethod.GET)
    public ModelAndView newRoom(HttpServletRequest request,ModelAndView model) {
        Room newRoom = new Room();
        model.addObject("newRoom", newRoom);
        model.addObject("hotelID", request.getParameter("hotelID"));
        model.setViewName("adminViews/newRoom");
        return model;
    }

    @RequestMapping(value = "/editRoom", method = RequestMethod.GET)
    public ModelAndView editRoom(HttpServletRequest request) {
        int roomId = Integer.parseInt(request.getParameter("id"));
        Room room = roomDAO.getRoomById(roomId);
        ModelAndView model = new ModelAndView("adminViews/editRoom");
        model.addObject("room", room);
        model.addObject("hotelID", request.getParameter("hotelID"));

        return model;
    }

    @RequestMapping(value = "/deleteRoom", method = RequestMethod.GET)
    public ModelAndView deleteRoom(HttpServletRequest request) {
        int roomId = Integer.parseInt(request.getParameter("id"));
        roomDAO.delete(roomId);
        return new ModelAndView("redirect:/listRoom");
    }

    @RequestMapping(value = "/saveRoom", method = RequestMethod.POST)
    public ModelAndView saveImage(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String roomImg = request.getParameter("roomImg");
        String facilities = request.getParameter("facilities");
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        Hotel hotel = hotelDAO.getHotelById(hotelID);
        Room room =  new Room(id, capacity, price, roomImg, facilities, hotel);
        roomDAO.saveOrUpdate(room);
        return new ModelAndView("redirect:/listRoom");
    }

    @RequestMapping(value = "/newHotel", method = RequestMethod.GET)
    public ModelAndView newHotel(HttpServletRequest request, ModelAndView model) {
        Hotel newHotel = new Hotel();
        model.addObject("newHotel", newHotel);
        model.addObject("tourID", request.getParameter("tourID"));
        model.setViewName("adminViews/newHotel");
        return model;
    }

    @RequestMapping(value = "/editHotel", method = RequestMethod.GET)
    public ModelAndView editHotel(HttpServletRequest request) {
        int hotelId = Integer.parseInt(request.getParameter("id"));
        Hotel hotel = hotelDAO.getHotelById(hotelId);
        ModelAndView model = new ModelAndView("adminViews/editHotel");
        model.addObject("tourID", request.getParameter("tourID"));
        model.addObject("hotel", hotel);

        return model;
    }

    @RequestMapping(value = "/deleteHotel", method = RequestMethod.GET)
    public ModelAndView deleteHotel(HttpServletRequest request) {
        int hotelId = Integer.parseInt(request.getParameter("id"));
        hotelDAO.delete(hotelId);
        return new ModelAndView("redirect:/listHotel");
    }

    @RequestMapping(value = "/saveHotel", method = RequestMethod.POST)
    public ModelAndView saveHotel(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int star = Integer.parseInt(request.getParameter("star"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String hotelImg = request.getParameter("hotelImg");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String details = request.getParameter("details");
        int tourID = Integer.parseInt(request.getParameter("tourID"));
        Hotel hotel =  new Hotel(id, title, star, rating, hotelImg, address, phoneNumber, details, tourDAO.getTourById(tourID));
        hotelDAO.saveOrUpdate(hotel);
        return new ModelAndView("redirect:/listHotel");
    }


    @RequestMapping(value = "/newTour", method = RequestMethod.GET)
    public ModelAndView newTour(HttpServletRequest request, ModelAndView model) {
        Tour newTour = new Tour();
        model.addObject("concertID", request.getParameter("concertID"));
        model.addObject("newTour", newTour);
        model.setViewName("adminViews/newTour");
        return model;
    }

    @RequestMapping(value = "/editTour", method = RequestMethod.GET)
    public ModelAndView editTour(HttpServletRequest request) {
        int tourID = Integer.parseInt(request.getParameter("id"));
        Tour tour = tourDAO.getTourById(tourID);
        ModelAndView model = new ModelAndView("adminViews/editTour");
        model.addObject("concertID", request.getParameter("concertID"));
        model.addObject("tour", tour);

        return model;
    }

    @RequestMapping(value = "/deleteTour", method = RequestMethod.GET)
    public ModelAndView deleteTour(HttpServletRequest request) {
        int tourID = Integer.parseInt(request.getParameter("id"));
        tourDAO.delete(tourID);
        return new ModelAndView("redirect:/listTour");
    }

    @RequestMapping(value = "/saveTour", method = RequestMethod.POST)
    public ModelAndView saveTour(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String licensePlate = request.getParameter("licensePlate");
        String model = (request.getParameter("model"));
        String type = (request.getParameter("type"));
        String date = request.getParameter("date");
        double price = Double.parseDouble(request.getParameter("price"));
        String details = request.getParameter("details");
        int concertID = Integer.parseInt(request.getParameter("concertID"));

        Concert concert = concertDAO.getConcertById(concertID);
        Tour tour = new Tour(id, licensePlate, model, type, date ,price, details, concert);
        tourDAO.saveOrUpdate(tour);
        return new ModelAndView("redirect:/listTour");
    }
}


