package com.controller;

import com.dao.*;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("/order")
public class OrderController {
    @Autowired
    private ConcertDAO concertDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private TourDAO tourDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;

    @RequestMapping(value="chooseConcert", method = RequestMethod.POST)
    public ModelAndView chooseConcert(HttpServletRequest request, ModelAndView model) {
        model.addObject("concertID",Integer.parseInt(request.getParameter("concertID")));
        List<Tour> listTour = tourDAO.tourListByConcert(Integer.parseInt(request.getParameter("concertID")));
        model.addObject("listTour", listTour);
        Concert concert = concertDAO.getConcertById(Integer.parseInt(request.getParameter("concertID")));
        model.addObject("concert", concert);
        model.setViewName("clientViews/listTour");
        return model;
    }

    @RequestMapping(value = "chooseTour", method = RequestMethod.POST)
    public ModelAndView chooseTour(HttpServletRequest request, ModelAndView model) {
        model.addObject("stars", hotelDAO.getStars());
        model.addObject("titles", hotelDAO.getTitles());
        model.addObject("concertID", Integer.parseInt(request.getParameter("concertID")));
        model.addObject("tourID", Integer.parseInt(request.getParameter("tourID")));
        List<Hotel> listHotel = hotelDAO.hotelListByTour(Integer.parseInt(request.getParameter("tourID")));
        model.addObject("listHotel", listHotel);
        Tour tour = tourDAO.getTourById(Integer.parseInt(request.getParameter("tourID")));
        model.addObject("tour", tour);
        model.setViewName("clientViews/listHotel");
        return model;
    }

    @RequestMapping(value = "chooseHotel", method = RequestMethod.POST)
    public ModelAndView chooseHotel(HttpServletRequest request, ModelAndView model) {
        model.addObject("capacities", roomDAO.getCapacities());
        List<Feedback> listFeedback = feedbackDAO.getFeedbackByHotel(Integer.parseInt(request.getParameter("hotelID")));
        model.addObject("Feedback", listFeedback);
        model.addObject("concertID", Integer.parseInt(request.getParameter("concertID")));
        model.addObject("tourID", Integer.parseInt(request.getParameter("tourID")));
        model.addObject("hotelID", Integer.parseInt(request.getParameter("hotelID")));
        List<Room> listRoom = roomDAO.roomListByHotel(Integer.parseInt(request.getParameter("hotelID")));
        model.addObject("listRoom", listRoom);
        Hotel hotel = hotelDAO.getHotelById(Integer.parseInt(request.getParameter("hotelID")));
        model.addObject("hotel", hotel);
        Feedback newFeedback =  new Feedback();
        model.addObject("newFeedback", newFeedback);
        model.setViewName("clientViews/listRoom");
        return model;
    }

    @RequestMapping(value = "payment", method = RequestMethod.POST)
    public ModelAndView payment(HttpServletRequest request, ModelAndView model) {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        orderDAO.payStatus(orderDAO.getOrderById(orderID),"Оплачен");
        model.setViewName("clientViews/account");
        return model;
    }

    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public ModelAndView createOrder(HttpServletRequest request, ModelAndView model) {
        int concertID = Integer.parseInt(request.getParameter("concertID"));
        int tourID = Integer.parseInt(request.getParameter("tourID"));
        int roomID = Integer.parseInt(request.getParameter("roomID"));

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User currentUser = usersDAO.findByUserName(username);
        int userID = currentUser.getId();

        double cost = concertDAO.getConcertById(concertID).getPrice() + tourDAO.getTourById(tourID).getPrice() + roomDAO.getRoomById(roomID).getPrice();
        String status = "Ожидает оплаты";
        Order order = new Order(concertDAO.getConcertById(concertID),tourDAO.getTourById(tourID),usersDAO.getUserById(userID), roomDAO.getRoomById(roomID), cost, status);
        orderDAO.saveOrUpdate(order);

        model.setViewName("clientViews/account");
        return model;
    }
}
