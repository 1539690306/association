package com.ls.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.dao.NoticeDao;
import com.ls.mapper.ActMapper;
import com.ls.mapper.NoticeMapper;
import com.ls.pojo.Act;
import com.ls.pojo.Actbmjl;
import com.ls.pojo.Check;
import com.ls.pojo.Notice;
import com.ls.pojo.User;
import com.ls.service.ActService;
import com.ls.service.NoticeService;


@Service
public class ActserviceImp implements ActService{

       @Autowired
       ActMapper mapper;
     
	
	public List<Act> findAllAct() {
		
		List<Act> acts=mapper.findAllAct();
		
		return acts;
	}


	public void addAct(Act act) {
		mapper.addAct(act);
		
	}


	public void addActbmjl(Actbmjl bmjl) {
		mapper.addActbmjl(bmjl);
		
	}


	public Act findActById(int id) {
		return mapper.findActById(id);
	}


	public void deleteActById(int id) {
		mapper.deleteActById(id);
		
	}


	public int countActuser(int actId) {
		
		return mapper.countActuser(actId);
	}

	

	public List<Act> findActByTitle(String actname) {
		List<Act> acts=mapper.findActByTitle(actname);
		return acts;
	}


	public List<Actbmjl> findUserByActbmjl(int actId) {
		
		return mapper.findUserByActbmjl(actId);
	}


	public void deleteActBmjlById(int id) {
		mapper.deleteActBmjlById(id);
		
	}


}
