package com.controller;

import com.dao.*;
import com.model.Feedback;
import com.model.Hotel;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("/cart")
public class HotelController {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping(value = "/listHotel", method = RequestMethod.GET)
    public ModelAndView listHotel(ModelAndView model){
        List<Hotel> listHotel = hotelDAO.hotelList();
        model.addObject("listHotel", listHotel);
        model.setViewName("clientViews/listHotel");
        return model;
    }

    @RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
    public ModelAndView saveFeedback(HttpServletRequest request){
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        Hotel hotel = hotelDAO.getHotelById(hotelID);
        int feedbackID = Integer.parseInt(request.getParameter("id"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String text = request.getParameter("text");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        //ТЕКУЩИЙ ЮЗЕР
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User currentUser = usersDAO.findByUserName(username);

        Feedback feedback = new Feedback(feedbackID, currentUser, format.format(date), text, hotel, rating);
        feedbackDAO.saveOrUpdate(feedback);
        hotelDAO.updateHotelRating(hotel);
        return new ModelAndView("redirect:/listConcert");
    }
}
