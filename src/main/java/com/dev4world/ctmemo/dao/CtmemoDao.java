package com.dev4world.ctmemo.dao;

import java.util.List;

import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.vo.Ctmemo;

/**
 * 메모장 DAO
 * 
 * @version $Id$
 */
public interface CtmemoDao {
	public Ctmemo getCtmemo(int ctmemoId);

	/**
	 * @param condition
	 * @return
	 */
	public List<Ctmemo> listCtmemo(CtmemoSearchCondition condition);

	/**
	 * @param board
	 */
	public void insert(Ctmemo ctmemo);

	/**
	 * @param article
	 */
	public void updateBoard(Ctmemo ctmemo);

	/**
	 * @param articleSeq
	 */
	public void removeBoard(int ctmemoId);
}
