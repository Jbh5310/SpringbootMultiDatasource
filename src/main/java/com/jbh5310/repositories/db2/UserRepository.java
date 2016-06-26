package com.jbh5310.repositories.db2;

import com.jbh5310.domain.db2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jbh5310 on 2016-06-26.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
