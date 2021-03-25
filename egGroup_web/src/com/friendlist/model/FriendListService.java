package com.friendlist.model;

import java.sql.Date;

import java.util.List;
import com.mem.model.*;
public class FriendListService
{
    private FriendListDAO_interface dao;

    public FriendListService()
    {
        dao = new FriendListDAO();
    }

    public void findByIds(String f_no,String f_memno)
    {
    	dao.findByIds(f_no, f_memno);
    }
    
    public void findByIds2(String f_no,String f_memno)
    {
    	dao.findByIds2(f_no, f_memno);
    }

//    public FriendListVO updateFriendList(String f_no, String f_memno, String f_status)
//    {
//
//    	FriendListVO friendlist = new FriendListVO();
//
//    	friendlist.setF_no(f_no);
//    	friendlist.setF_memno(f_memno);
//    	friendlist.setF_status(f_status);
//
//        dao.update(friendlist);
//
//        return friendlist;
//    }

    public void rejectFriendList(String f_no,String f_memno)
    {
        dao.reject(f_no,f_memno);
    }
    
//    public FriendListVO findByPrimaryKey(String f_num) {
//		return dao.findByPrimaryKey(f_num);
//    }
//    
//    
//    public FriendListVO getOneReportFeast(String f_num)
//    {
//        return dao.findByPrimaryKey(f_num);
//    }

    public List<MemVO> getAll(String mem_no)
    {
        return dao.getAll(mem_no);
    }
    
    public List<MemVO> getAllInvite(String mem_no){
    	return dao.getAllInvite(mem_no);
    }
    
    public List<MemVO> match(String mem_no){
    	return dao.match(mem_no);
    }
}
