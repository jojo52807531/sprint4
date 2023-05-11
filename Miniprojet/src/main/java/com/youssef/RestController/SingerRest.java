package com.youssef.RestController;

import com.youssef.entities.Singer;
import com.youssef.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Singersres")
@CrossOrigin("*")

public class SingerRest {
    @Autowired
    SingerService singerService;
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    Singer getSinger(@PathVariable("id") Long id){
        return singerService.getSinger(id);
    }
    @RequestMapping(method = RequestMethod.GET)
    List<Singer> getAllSinger(){
        return singerService.getAllSinger();
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    void DeleteSinger(@PathVariable("id") Long id){
         singerService.deleteSingerById(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    Singer AddSinger(@RequestBody Singer singer){
        return singerService.saveSinger(singer);
    }
    @RequestMapping(method = RequestMethod.PUT)
    Singer UpdateSinger(@RequestBody Singer singer){
        return singerService.updateSinger(singer);
    }

}
