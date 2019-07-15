package kr.co.torpedo.uims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kr.co.torpedo.uims.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(nativeQuery = true, value = "select * from user order by date DESC limit :pageStart, :perPageNum")
	public List<User> find(@Param("pageStart")int pageStart, @Param("perPageNum")int perPageNum);
}
