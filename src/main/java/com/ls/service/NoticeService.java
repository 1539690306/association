package com.ls.service;
import java.util.*;

import com.ls.pojo.Check;
import com.ls.pojo.MinGanCi;
import com.ls.pojo.Notice;
import com.ls.pojo.User;

public interface NoticeService {
	
	 public List<Notice> findAllNotice();
	 
	 public void deleteNoticeById(int id);
	
	 public void addNotice(Notice notice);

	 public List<Notice> searchNotice(String article);
	 
	 public void addCheck(Check check);
	 
	 public Check findCheck(Check check);
	 
	 public int countCheck(int noticeId);
	 
	 public void insertNotice(Notice notice);
	 
	 public void deleteCheck(int noticeId);
	 
	 public Notice selectNoticeById(int id);
	 
	 public List<User> listChecker(int id);
	 
	 public List<MinGanCi> selectMinGanCi();
	 
	 public void addMinGanCi(MinGanCi word);
	 
	 public void deleteMinGanCi(int id);
	 
}
