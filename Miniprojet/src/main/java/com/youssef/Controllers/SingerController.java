package com.youssef.Controllers;

import com.youssef.entities.Singer;
import com.youssef.entities.Type;
import com.youssef.service.SingerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




@Controller
public class SingerController {

	SingerService singerService;

	public SingerController(SingerService singerService) {
		this.singerService = singerService;
	}

	@RequestMapping("Addsinger")
	public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("Singer",new Singer());
		modelMap.addAttribute("pg","ajoute");
		modelMap.addAttribute("page",0);
		List <Type> types = singerService.getAllType();
		modelMap.addAttribute("type",types);
		return "Addsinger";
	}

	@RequestMapping("/save")
	public String savesinger(
			@Valid Singer singer,
			BindingResult r,
			@RequestParam("ty") String id,
			@RequestParam(name = "page",defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			ModelMap modelMap

	) {

		// Convert the date
		if(r.hasErrors()) {
			List <Type> u= singerService.getAllType();

			modelMap.addAttribute("type",u);
			modelMap.addAttribute("singer",singer);
			return "Addsinger";}
		else {
			if(!id.contains("Pas Type")){
				Long idd = Long.parseLong(id);
				Type u = singerService.getTypebyid(idd);
				singer.setType(u);}
			Singer ajmach = singerService.saveSinger(singer);
			Page<Singer> singers = singerService.getAllSingerByPage(page, size);
			modelMap.addAttribute("singers", singers);
			modelMap.addAttribute("pages", new int[singers.getTotalPages()]);
			int n = singers.getTotalPages();
			if(singer.getIdSinger().equals(null)){
				System.out.println("ajouter");
				return ("redirect:/Singers?page="+n+"&size="+size);

			}else{
				System.out.println("modifier");

				return ("redirect:/Singers?page="+page+"&size="+size);
			}}
	}




		@RequestMapping("/Singers")
	public String listsinger(
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size
	) {
		Page<Singer> Singers = singerService.getAllSingerByPage(page, size);
		modelMap.addAttribute("Singers", Singers);
		modelMap.addAttribute("pages", new int[Singers.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "Listsingers";
	}

	@RequestMapping("/deletesinger")
	public String deleteAlbum(
			@RequestParam("IdSinger") Long id,
			ModelMap modelMap,
			@RequestParam(name = "page") String page,
			@RequestParam(name = "size",defaultValue = "2") int size
	) {
	singerService.deleteSingerById(id);
	int p=Integer.parseInt(page);
		Page<Singer> Singer = singerService.getAllSingerByPage(p,size);
		modelMap.addAttribute("Singers", Singer);
		modelMap.addAttribute("pages", new int[Singer.getTotalPages()]);
		int n= Singer.getTotalPages()-1;
		if(n>p){
		modelMap.addAttribute("currentPage", 1);}
		else{
			modelMap.addAttribute("currentPage",1);
		}
		modelMap.addAttribute("size", size);
		return "Listsingers"; // Replace with your target URL
	}

	@RequestMapping("/showUpdate")
	public String showUpdate(
			@RequestParam("IdSinger") Long IdSinger,
			@RequestParam("page") int page,
			ModelMap modelMap
	) {
		Singer Singer = singerService.getSinger(IdSinger);
		modelMap.addAttribute("Singer", Singer);
		modelMap.addAttribute("page",page);
		modelMap.addAttribute("selectedType", Singer.getType());
		modelMap.addAttribute("pg","modifer");

		List <Type> types = singerService.getAllType();
		modelMap.addAttribute("types",types);
		return "Addsinger";
	}

	@RequestMapping("/Modifiermachine")
	public String updateSinger(
			@ModelAttribute("singer") Singer singer,
			@RequestParam("datebirth") String date,
			@RequestParam("ty") String id,
			//import page from url

			@RequestParam(name = "page",defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			ModelMap modelMap
	) throws ParseException {
		// Convert the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date DateBirth = dateFormat.parse(String.valueOf(date));
		singer.setDateBirth(DateBirth);
		Long idd= Long.parseLong(id);
		Type t= singerService.getTypebyid(idd);
		singer.setType(t);
		singerService.updateSinger(singer);
		Page<Singer> singers = singerService.getAllSingerByPage(0, 2);
		modelMap.addAttribute("Singers", singers);
		modelMap.addAttribute("pages", new int[singers.getTotalPages()]);
		//convert string to int

		modelMap.addAttribute("size", size);
		modelMap.addAttribute("currentPage", page);
		return "Listsingers";
	}
}
