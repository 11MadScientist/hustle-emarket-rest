package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.CustomerDetail;

public interface CustomerDetailService
{
	public List<CustomerDetail> getCustomerDetail();

	public CustomerDetail getCustomerDetailById(int id);

	public void saveCustomerDetail(CustomerDetail customerDetail);

	public void deleteCustomerDetailById(int id);

	public void deleteCustomerDetail(CustomerDetail customerDetail);

}
