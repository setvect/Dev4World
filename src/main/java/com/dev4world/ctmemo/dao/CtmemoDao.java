package com.dev4world.ctmemo.dao;

import java.util.List;

import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.CtmemoSearchCondition;

/**
 * 메모장 DAO
 * 
 * @version $Id$
 */
public interface CtmemoDao {
	public CtmemoVo getCtmemo(int ctmemoId);

	/**
	 * @param condition
	 * @return
	 */
	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition);

	/**
	 * @param board
	 */
	public void insert(CtmemoVo ctmemo);

	/**
	 * @param article
	 */
	public void updateCtmemo(CtmemoVo ctmemo);

	/**
	 * @param articleSeq
	 */
	public void deleteCtmemo(int ctmemoId);
}
