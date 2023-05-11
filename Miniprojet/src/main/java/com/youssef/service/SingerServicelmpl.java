package com.youssef.service;

import com.youssef.entities.Singer;
import com.youssef.entities.Type;
import com.youssef.repos.SingerReposotiry;
import com.youssef.repos.TypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SingerServicelmpl implements SingerService {
	 SingerReposotiry singerReposotiry;
	 TypeRepository ty;
	public SingerServicelmpl(SingerReposotiry singerReposotiry, TypeRepository t) {
		this.singerReposotiry = singerReposotiry;
		this.ty=t;
	}

	@Override
	public Singer saveSinger(Singer m) {
		return singerReposotiry.save(m);
	}
	@Override
	public Singer updateSinger(Singer m) {
	return singerReposotiry.save(m);
	
	}
	@Override
	public void deleteSingerById(Long id) {
		singerReposotiry.deleteById(id);
	}
	@Override
	public Singer getSinger(Long id) {
	return singerReposotiry.findById(id).get();
	}
	@Override
	public List<Singer> getAllSinger() {
	return singerReposotiry.findAll();
	}
	@Override
	public Page<Singer> getAllSingerByPage(int page, int size) {
		return singerReposotiry.findAll(PageRequest.of(page, size));

	}
	@Override
	public List<Type> getAllType(){
		return ty.findAll();
	}
	@Override
	public Type getTypebyid(Long id){
		return  ty.findById(id).get();
	}
	@Override
	public Type addtype(Type t){
		return ty.save(t);

	}
	@Override
	public Type updatetype(Type t){
		return ty.save(t);

	}
	@Override
	public void deletetype(Long id){
		 ty.deleteById(id);

	}
	}
	
	


	
	
	
	


