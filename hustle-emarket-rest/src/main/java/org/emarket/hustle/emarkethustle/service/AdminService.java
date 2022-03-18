package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Admin;

public interface AdminService
{
	public List<Admin> getAdmin();

	public Admin getAdminById(int id);

	public Admin addAdmin(Admin admin);

	public Admin updateAdmin(Admin admin);

	public void deleteAdminById(int id);
}
