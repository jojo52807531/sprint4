package com.youssef.service;

import com.youssef.entities.Singer;
import com.youssef.entities.Type;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingerService {

	Singer saveSinger(Singer m);

	Singer updateSinger(Singer p);

	void deleteSingerById(Long id);

	Singer getSinger(Long id);

	List<Singer> getAllSinger();


    Page<Singer> getAllSingerByPage(int page, int size);

	List<Type> getAllType();

	Type getTypebyid(Long id);


	Type addtype(Type t);

	Type updatetype(Type t);

	void deletetype(Long id);
}
