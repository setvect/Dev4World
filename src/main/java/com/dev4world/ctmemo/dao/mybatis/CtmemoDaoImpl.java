package com.dev4world.ctmemo.dao.mybatis;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.dao.CtmemoDao;

@Repository
public class CtmemoDaoImpl implements CtmemoDao {
	@Inject
	private SqlSession query;

	@Override
	public CtmemoVo getCtmemo(int ctmemoId) {
		CtmemoDao mapper = query.getMapper(CtmemoDao.class);
		CtmemoVo val = mapper.getCtmemo(ctmemoId);
		return val;
	}

	@Override
	public int getMaxZindex() {
		CtmemoDao mapper = query.getMapper(CtmemoDao.class);
		int val = mapper.getMaxZindex();
		return val;
	}

	@Override
	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		CtmemoDao mapper = query.getMapper(CtmemoDao.class);
		List<CtmemoVo> val = mapper.listCtmemo(condition);
		return val;
	}

	@Override
	public void insertCtmemo(CtmemoVo ctmemo) {
		CtmemoDao mapper = query.getMapper(CtmemoDao.class);
		mapper.insertCtmemo(ctmemo);
	}

	@Override
	public void updateCtmemo(CtmemoVo ctmemo) {
		CtmemoDao mapper = query.getMapper(CtmemoDao.class);
		mapper.updateCtmemo(ctmemo);
	}

	@Override
	public void deleteCtmemo(int ctmemoId) {
		CtmemoDao mapper = query.getMapper(CtmemoDao.class);
		mapper.deleteCtmemo(ctmemoId);

	}
}
