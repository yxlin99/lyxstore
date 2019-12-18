package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.NoticeDao;
import cn.itcast.itcaststore.domain.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	//鍚庡彴绯荤粺锛屾煡璇㈡墍鏈夊叕鍛�
	public List<Notice> getAllNotices() {
		try {
			return dao.getAllNotices();
		} catch (SQLException e) {
			throw new RuntimeException("鏌ヨ鎵�鏈夌殑鍏憡澶辫触锛�");
		}
	}
	//鍚庡彴绯荤粺锛屾坊鍔犲叕鍛�
	public void addNotice(Notice notice) {
		try {
			dao.addNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("娣诲姞鍏憡澶辫触!");
		}
	}
	//鍚庡彴绯荤粺锛屾牴鎹甶d鏌ユ壘鍏憡
	public Notice findNoticeById(String n_id) {
		try {
			return dao.findNoticeById(n_id);
		} catch (SQLException e) {
			throw new RuntimeException("鏍规嵁id鏌ユ壘鍏憡澶辫触锛�");
		}
	}
	
	//鍚庡彴绯荤粺锛屾牴鎹甶d淇敼鍏憡
	public void updateNotice(Notice bean) {
		try {
			dao.updateNotice(bean);
		} catch (SQLException e) {
//			throw new RuntimeException("鏍规嵁id淇敼鍏憡澶辫触锛�");
			e.printStackTrace();
		}
	}
	
	//鍚庡彴绯荤粺锛屾牴鎹甶d鍒犻櫎鍏憡
	public void deleteNotice(String n_id) {
		try {
			dao.deleteNotice(n_id);
		} catch (SQLException e) {
			throw new RuntimeException("鏍规嵁id鍒犻櫎鍏憡澶辫触锛�");
		}
	}
	
	//鍓嶅彴绯荤粺锛屾煡璇㈡渶鏂版坊鍔犳垨淇敼鐨勪竴鏉″叕鍛�
	public Notice getRecentNotice() {
		try {
			return dao.getRecentNotice();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
