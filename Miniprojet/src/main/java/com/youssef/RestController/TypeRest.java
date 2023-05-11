package com.youssef.RestController;

import com.youssef.entities.Type;
import com.youssef.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Typeres")
@CrossOrigin("*")

public class TypeRest {
    @Autowired
    SingerService singerService;
    @RequestMapping(method = RequestMethod.POST)
    Type Addtype(@RequestBody Type t){
        return singerService.addtype(t);
    }
    @RequestMapping(method = RequestMethod.PUT)
    Type UpdateType(@RequestBody Type t){
        return singerService.updatetype(t);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    void DeleteType(@PathVariable("id") Long id){
        singerService.deletetype(id);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    Type getSinger(@PathVariable("id") Long id){
        return singerService.getTypebyid(id);
    }
    @RequestMapping(method = RequestMethod.GET)
    List<Type> getSingers(){
        return singerService.getAllType();
    }
}
