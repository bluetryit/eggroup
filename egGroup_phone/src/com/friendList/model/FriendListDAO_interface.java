package com.friendList.model;

import java.util.*;
import com.mem.model.MemVO;

public interface FriendListDAO_interface {
          public void insert(FriendListVO friendListVO);
//          public void update(FriendListVO friendListVO);
          public void reject(String f_no,String f_memno);
          public void update(String f_no,String _memno,String f_status);
          public FriendListVO findByPrimaryKey(String f_no);
          public void delete(String f_no,String _memno);
          
          
//          public List<MemVO> match(String mem_no);
          public List<MemVO> getAll(String mem_no);
//          public List<MemVO> getAllInvite(String mem_no);
          public FriendListVO findByTwoPrimaryKey(String f_no, String f_memno);	
}
