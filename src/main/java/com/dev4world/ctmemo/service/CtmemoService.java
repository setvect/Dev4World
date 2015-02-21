package com.dev4world.ctmemo.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dev4world.ctmemo.CtmemoConstant;
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

	/**
	 * z-index 최대 값
	 * 
	 * @return
	 */
	public int getMaxZindex() {
		return ctmemoDao.getMaxZindex();
	}

	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		return ctmemoDao.listCtmemo(condition);
	}

	public void insertCtmemo(CtmemoVo ctmemo) {
		ctmemoDao.insertCtmemo(ctmemo);
	}

	
	/**
	 * 트랜젝션 테스트 용도. 
	 * @param ctmemo
	 */
	public void insertCtmemoAndUpdate(CtmemoVo ctmemo) {
		ctmemoDao.insertCtmemo(ctmemo);
		ctmemo.setContent(ctmemo.getContent() + "########");
		ctmemo.setBgCss(null);
		ctmemoDao.updateCtmemo(ctmemo);
	}
	
	/**
	 * 새로운 메모장 생성<br>
	 * 생성과 동시에 DB에 저장
	 * 
	 * @return
	 */
	public CtmemoVo newMemo() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setContent(" ");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_1);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_1);
		ctmemo.setWidth(150);
		ctmemo.setHeight(150);
		ctmemo.setPositionX(1);
		ctmemo.setPositionY(1);
		ctmemo.setzIndex(getMaxZindex());
		Date date = new Date();
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);
		insertCtmemo(ctmemo);
		return ctmemo;
	}

	/**
	 * 삭제 취소
	 * 
	 * @param ctmemoSeq
	 * @return 
	 */
	public CtmemoVo undeleteCtmemo(int ctmemoSeq) {
		CtmemoVo memo = getCtmemo(ctmemoSeq);
		memo.setDeleteF(false);
		updateCtmemo(memo);
		return memo;
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
