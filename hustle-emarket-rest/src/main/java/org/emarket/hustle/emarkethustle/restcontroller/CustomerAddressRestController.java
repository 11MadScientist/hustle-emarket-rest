package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.CustomerAddress;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class CustomerAddressRestController
{
	Logger log = Logger.getLogger(CustomerAddressRestController.class.getName());
	@Autowired
	private CustomerAddressService customerAddressService;

	/*
	 * #######################################
	 * ######## GET CUSTOMER ADDRESS #########
	 * #######################################
	 */
	@GetMapping("/customer-addresses")
	public List<CustomerAddress> getCustomerAddress(@RequestParam(required = false) Integer id)
	{
		if(id == null)
		{
			return customerAddressService.getCustomerAddress();
		}

		return customerAddressService.getCustomerAddressByCustomerId(id);
	}

	/*
	 * #######################################
	 * ##### GET CUSTOMER ADDRESS BY ID ######
	 * #######################################
	 */
	@GetMapping("/customer-addresses/{id}")
	public CustomerAddress getCustomerAddressById(@PathVariable int id)
	{
		return customerAddressService.getCustomerAddressById(id);
	}

	/*
	 * #######################################
	 * ######## ADD CUSTOMER ADDRESS #########
	 * #######################################
	 */
	@PostMapping("/customer-addresses")
	public CustomerAddress addCustomerAddress(@RequestBody CustomerAddress customerAddress)
	{
		customerAddressService.saveCustomerAddress(customerAddress);
		return customerAddress;
	}

	/*
	 * #######################################
	 * ###### UPDATE CUSTOMER ADDRESS ########
	 * #######################################
	 */
	@PutMapping("/customer-addresses")
	public CustomerAddress updateCustomerAddress(@RequestBody CustomerAddress customerAddress)
	{
		customerAddressService.saveCustomerAddress(customerAddress);
		return customerAddress;
	}

	/*
	 * #######################################
	 * ####### DELETE CUSTOMER ADDRESS #######
	 * #######################################
	 */
	@DeleteMapping("/customer-addresses/{id}")
	public ProcessConfirmation deleteCustomerAddress(@PathVariable int id)
	{
		customerAddressService.deleteCustomerAddressById(id);

		return new ProcessConfirmation("SUCCESS", "CUSTOMERDETAIL",
				"CUSTOMER ADDRESS WITH ID: " + id + " WAS DELETED.");
	}

}
