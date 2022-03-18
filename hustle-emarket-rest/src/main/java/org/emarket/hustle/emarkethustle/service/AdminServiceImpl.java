package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.AdminRepository;
import org.emarket.hustle.emarkethustle.entity.Admin;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	AdminRepository adminRepository;

	@Override
	public List<Admin> getAdmin()
	{
		return adminRepository.findAll();
	}

	@Override
	public Admin getAdminById(int id)
	{
		Optional<Admin> result = adminRepository.findById(id);

		if(result.isEmpty())
		{
			return null;
		}

		return result.get();
	}

	@Override
	public Admin addAdmin(Admin admin)
	{
		admin.setId(0);
		return adminRepository.save(admin);

	}

	@Override
	public Admin updateAdmin(Admin admin)
	{
		return adminRepository.save(admin);
	}

	@Override
	public void deleteAdminById(int id)
	{
		try
		{
			adminRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETION OF ADMIN ACCOUNT");
		}
	}

}
