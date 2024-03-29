package kr.co.torpedo.uims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import kr.co.torpedo.uims.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	Page<User> findAll(Pageable pageable);
}
