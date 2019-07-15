package kr.co.torpedo.uims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.torpedo.uims.domain.User;
import kr.co.torpedo.uims.paging.Criteria;
import kr.co.torpedo.uims.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public List<User> selectAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public List<User> listCriteria(Criteria cri) {
		return repository.find(cri.getPageStart(), cri.getPerPageNum());
	}

	@Override
	public int countUser() {
		return ((int) repository.count());
	}

	@Override
	public User selectUser(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void insert(User user) {
		repository.save(user);
	}

	@Override
	public void update(User user) {
		repository.save(user);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

}
