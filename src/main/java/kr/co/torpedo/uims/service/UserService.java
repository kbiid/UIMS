package kr.co.torpedo.uims.service;

import java.util.List;

import kr.co.torpedo.uims.domain.User;
import kr.co.torpedo.uims.paging.Criteria;

public interface UserService {
	public List<User> selectAll();
	
	public List<User> listCriteria(Criteria cri);
	
	public int countUser();
	
	public User selectUser(int id);
	
	public void insert(User user);
	
	public void update(User user);
	
	public void delete(int id);
}
