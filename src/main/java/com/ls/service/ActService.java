package com.ls.service;
import java.util.*;

import com.ls.pojo.Act;
import com.ls.pojo.Actbmjl;

public interface ActService {
	
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
