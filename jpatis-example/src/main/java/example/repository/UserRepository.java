package example.repository;

import com.next.jpatis.spring.JpatisRepository;

import example.domain.User;

public interface UserRepository extends JpatisRepository<User,Integer>{

}
