package com.ls.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ls.pojo.Act;
import com.ls.pojo.Actbmjl;
import com.ls.pojo.Check;
import com.ls.pojo.Notice;
import com.ls.pojo.User;


public interface ActMapper {
		 
	 public List<Act> findAllAct();
	 
	 public void addAct(Act act);
	 
	 public void addActbmjl(Actbmjl bmjl);

	 public Act findActById(int id);
	 
	 public void deleteActById(int id);
	 
	 public int countActuser(int actId);
	 
	 public List<Act> findActByTitle(String actname);
	 
	 public List<Actbmjl> findUserByActbmjl(int actId);
	 
	 public void deleteActBmjlById(int id);


}
