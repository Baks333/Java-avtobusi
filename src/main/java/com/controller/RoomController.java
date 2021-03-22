package com.controller;

import com.dao.RoomDAO;
import com.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/room")
public class RoomController {

    @Autowired
    private RoomDAO roomDAO;

    @RequestMapping(value = "/listRoom", method = RequestMethod.GET)
    public ModelAndView listRoom(ModelAndView model){
        List<Room> listRoom = roomDAO.roomList();
        model.addObject("listRoom", listRoom);
        model.setViewName("clientViews/listRoom");
        return model;
    }
}
