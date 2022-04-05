package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer>
{
	public Admin findAdminByUsername(String username);
}
