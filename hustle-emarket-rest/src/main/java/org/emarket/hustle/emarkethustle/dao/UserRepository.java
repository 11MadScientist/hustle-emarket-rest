package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
