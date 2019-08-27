package com.cjbs.demo.repository;

import com.cjbs.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author shj
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 根据姓名查询年龄
     * @param name name
     * @return User
     */
    @Query(value = "select * from Users u where u.name = ?1", nativeQuery = true)
    Optional<User> getAgeByName(String name);

    /**
     * 根据年龄查询所有符合条件的user
     * @param age age
     * @return List
     */
    @Query(value = "select * from Users u where u.age = ?1", nativeQuery = true)
    Optional<List<User>> getAllByAge(String age);
}
