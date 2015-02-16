package com.dev4world.ctmemo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.dao.CtmemoDao;
import com.dev4world.ctmemo.vo.Ctmemo;

@Service
public class CtmemoService {
	@Inject
	private CtmemoDao ctmemoDao;

	public Ctmemo getCtmemo(int ctmemoId) {
		return ctmemoDao.getCtmemo(ctmemoId);
	}

	public List<Ctmemo> listCtmemo(CtmemoSearchCondition condition) {
		return ctmemoDao.listCtmemo(condition);
	}

	public void insert(Ctmemo ctmemo) {
		ctmemoDao.insert(ctmemo);
	}

	public void updateCtmemo(Ctmemo ctmemo) {
		ctmemoDao.updateCtmemo(ctmemo);
	}

	public void removeCtmemo(int ctmemoId) {
		Ctmemo ctmemo = getCtmemo(ctmemoId);
		ctmemo.setDeleteF(true);
		updateCtmemo(ctmemo);
	}
}
