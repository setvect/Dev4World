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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxZindex() {
		System.out.println(query);
		return 0;
	}

	@Override
	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(CtmemoVo ctmemo) {
		System.out.println(query);
		// TODO Auto-generated method stub
	}

	@Override
	public void updateCtmemo(CtmemoVo ctmemo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCtmemo(int ctmemoId) {
		// TODO Auto-generated method stub

	}
}
