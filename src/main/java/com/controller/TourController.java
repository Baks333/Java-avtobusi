package com.controller;

import com.dao.RoomDAO;
import com.model.Room;
import com.model.Tour;
import com.dao.TourDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("/tour")
public class TourController {

    @Autowired
    private TourDAO tourDAO;

    @RequestMapping(value = "/listTour", method = RequestMethod.GET)
    public ModelAndView listTour(ModelAndView model){
        List<Tour> listTour = tourDAO.tourList();
        model.addObject("listTour", listTour);
        model.setViewName("clientViews/listTour");
        return model;
    }
}
