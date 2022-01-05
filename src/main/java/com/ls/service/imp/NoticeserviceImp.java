package com.ls.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.dao.MinGanCiDao;
import com.ls.dao.NoticeDao;
import com.ls.mapper.NoticeMapper;
import com.ls.pojo.Check;
import com.ls.pojo.MinGanCi;
import com.ls.pojo.Notice;
import com.ls.pojo.User;
import com.ls.service.NoticeService;


@Service
public class NoticeserviceImp implements NoticeService{
       @Autowired
       NoticeDao dao;
       @Autowired
       MinGanCiDao mgcdao;
       @Autowired
       NoticeMapper mapper;
     
	
	public List<Notice> findAllNotice() {
		
		List<Notice> notices=mapper.findAllNotice();
		
		return notices;
	}

	public void deleteNoticeById(int id) {
		dao.deleteById(id);	
	}

	public void addNotice(Notice notice) {
		dao.insert(notice);	
		
	}

	public List<Notice> searchNotice(String article) {
		List<Notice> notices=mapper.searchNotice(article);
		
		return notices;
	}


	public void addCheck(Check check) {
	mapper.addCheck(check);
		
	}

	@Override
	public Check findCheck(Check check) {
		
		return mapper.findCheck(check);
	}

	@Override
	public int countCheck(int noticeId) {
		
		return mapper.countCheck(noticeId);
	}

	@Override
	public void insertNotice(Notice notice) {
		mapper.insertNotice(notice);
		
	}

	@Override
	public void deleteCheck(int noticeId) {
		mapper.deleteCheck(noticeId);
		
	}

	@Override
	public Notice selectNoticeById(int id) {
		
		return mapper.selectNoticeById(id);
	}

	@Override
	public List<User> listChecker(int id) {
		
		return mapper.listChecker(id);
	}

	@Override
	public List<MinGanCi> selectMinGanCi() {
		// TODO Auto-generated method stub
		return mapper.selectMinGanCi();
	}

	@Override
	public void addMinGanCi(MinGanCi word) {
		// TODO Auto-generated method stub
		mgcdao.insert(word);
	}

	@Override
	public void deleteMinGanCi(int id) {
		mgcdao.deleteById(id);
		
	}


}
