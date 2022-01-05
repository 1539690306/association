package com.ls.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ls.pojo.Check;
import com.ls.pojo.MinGanCi;
import com.ls.pojo.Notice;
import com.ls.pojo.User;


public interface NoticeMapper {
	
	 public List<Notice> searchNotice(String article);
	 
	 public List<Notice> findAllNotice();
	 
	 public void addCheck(Check check);
	 
	 public Check findCheck(Check check);
	 
	 public int countCheck(int noticeId);
	 
	 public void insertNotice(Notice notice);
	 
	 public void deleteCheck(int noticeId);
	 
	 public Notice selectNoticeById(int id);
	 
	 public List<User> listChecker(int id);
	 
	 public List<MinGanCi> selectMinGanCi();
}
