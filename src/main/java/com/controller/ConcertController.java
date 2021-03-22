package com.controller;

import com.dao.ConcertDAO;
import com.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("/concerts")
public class ConcertController {

    @Autowired
    private ConcertDAO concertDAO;


    //Полный список одежды
    @RequestMapping(value = {"/listConcerts", "/"}, method = RequestMethod.GET)
    public ModelAndView listConcerts(ModelAndView model){
        List<Concert> listConcerts = concertDAO.concertList();
        model.addObject("Titles", concertDAO.getTitles());
        model.addObject("Locations", concertDAO.getLocations());
        model.addObject("Bands", concertDAO.getBands());
        model.addObject("listConcerts", listConcerts);
        model.setViewName("clientViews/listConcerts");

        return model;
    }

    @RequestMapping(value = "/ListConcertsByTitle", method = RequestMethod.GET)
    public ModelAndView ListConcertsByTitle(HttpServletRequest request, ModelAndView model){
        String title = request.getParameter("title");
        List<Concert> ListConcertsByTitle = concertDAO.getConcertByTitle(title);
        model.addObject("Titles", concertDAO.getTitles());
        model.addObject("Locations", concertDAO.getLocations());
        model.addObject("Bands", concertDAO.getBands());
        model.addObject("listConcerts", ListConcertsByTitle);
        model.setViewName("clientViews/listConcerts");

        return model;
    }

    @RequestMapping(value = "/ListConcertsByLocation", method = RequestMethod.GET)
    public ModelAndView ListConcertsByLocation(HttpServletRequest request, ModelAndView model){
        String location = request.getParameter("location");
        List<Concert> ListConcertsByLocation = concertDAO.getConcertByLocation(location);
        model.addObject("Titles", concertDAO.getTitles());
        model.addObject("Locations", concertDAO.getLocations());
        model.addObject("Bands", concertDAO.getBands());
        model.addObject("listConcerts", ListConcertsByLocation);
        model.setViewName("clientViews/listConcerts");

        return model;
    }

    @RequestMapping(value = "/ListConcertsByBand", method = RequestMethod.GET)
    public ModelAndView ListConcertsByBand(HttpServletRequest request, ModelAndView model){
        String band = request.getParameter("band");
        List<Concert> ListConcertsByBand = concertDAO.getConcertByBand(band);
        model.addObject("Titles", concertDAO.getTitles());
        model.addObject("Locations", concertDAO.getLocations());
        model.addObject("Bands", concertDAO.getBands());
        model.addObject("listConcerts", ListConcertsByBand);
        model.setViewName("clientViews/listConcerts");

        return model;
    }
}
