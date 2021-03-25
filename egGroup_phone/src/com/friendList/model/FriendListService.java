package com.friendList.model;

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

//    public FriendListVO addFriendList(String f_no,String f_memno)
//    {
//
//    	FriendListVO friendlist = new FriendListVO();
//
//    	friendlist.setF_no(f_no);
//    	friendlist.setF_memno(f_memno);
//        dao.insert(friendlist);
//        return friendlist;
//    }

    //
    
    public void insertFriendList(String f_no,String f_memno,String f_status)
    {

    	FriendListVO friendlist = new FriendListVO();

    	friendlist.setF_no(f_no);
    	friendlist.setF_memno(f_memno);
        dao.insert(friendlist);
   
    }
    
    public void dealeteFriendList(String f_no, String f_memno)
    {
        dao.delete(f_no,f_memno);
    }

    public void rejectFriendList(String f_no,String f_memno)
    {
        dao.reject(f_no,f_memno);
    }
    
    public FriendListVO findByPrimaryKey(String f_num) {
		return dao.findByPrimaryKey(f_num);
    }
    
    public FriendListVO findByTwoPrimaryKey(String f_no, String f_memno) {
		return dao.findByTwoPrimaryKey(f_no, f_memno);
    }
    
    public FriendListVO getOneReportFeast(String f_num)
    {
        return dao.findByPrimaryKey(f_num);
    }

    public List<MemVO> getAll(String mem_no)
    {
        return dao.getAll(mem_no);
    }
    
//    public List<MemVO> getAllInvite(String mem_no){
//    	return dao.getAllInvite(mem_no);
//    }
    
//    public List<MemVO> match(String mem_no){
//    	return dao.match(mem_no);
//    }
}
