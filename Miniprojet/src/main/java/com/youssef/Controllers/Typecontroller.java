package com.youssef.Controllers;


import com.youssef.entities.Type;
import com.youssef.repos.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class Typecontroller {
    @Autowired
    TypeRepository typeRepository;


    @RequestMapping("/Addtype")
    public String showCreate() {

        return "Addtype";
    }

    @RequestMapping("/Addtypes")
    public String saveutil(
            @ModelAttribute("type") Type type,

            ModelMap modelMap
    ) {
        typeRepository.save(type);
        List<Type> types = typeRepository.findAll();
        modelMap.addAttribute("types", types);

        return "Listtype";
    }

    @RequestMapping("/type")
    public String listtype(
            ModelMap modelMap
    ) {
        List<Type> types = typeRepository.findAll();
        modelMap.addAttribute("types", types);

        return "Listtype";
    }

    @RequestMapping("/deletetype")
    public String deletetype(
            @RequestParam("idtype") Long id,
            ModelMap modelMap
    ) {
        typeRepository.deleteById(id);
        List<Type> types = typeRepository.findAll();
        modelMap.addAttribute("types", types);

        return "Listtype";
    }
}
