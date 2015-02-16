package com.dev4world.ctmemo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.dao.CtmemoDao;

@Service
public class CtmemoService {
	@Inject
	private CtmemoDao ctmemoDao;

	public CtmemoVo getCtmemo(int ctmemoId) {
		return ctmemoDao.getCtmemo(ctmemoId);
	}

	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		return ctmemoDao.listCtmemo(condition);
	}

	public void insert(CtmemoVo ctmemo) {
		ctmemoDao.insert(ctmemo);
	}

	public void updateCtmemo(CtmemoVo ctmemo) {
		ctmemoDao.updateCtmemo(ctmemo);
	}

	public void removeCtmemo(int ctmemoId) {
		CtmemoVo ctmemo = getCtmemo(ctmemoId);
		ctmemo.setDeleteF(true);
		updateCtmemo(ctmemo);
	}
}
