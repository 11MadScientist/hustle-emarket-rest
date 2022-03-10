package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.RiderDetailRepository;
import org.emarket.hustle.emarkethustle.entity.RiderDetail;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderDetailServiceImpl implements RiderDetailService
{

	@Autowired
	private RiderDetailRepository riderDetailService;

	@Override
	public List<RiderDetail> getRiderDetail()
	{
		List<RiderDetail> riderDetails = riderDetailService.findAll();

		if(riderDetails.isEmpty())
		{
			throw new NotFoundException("RIDERS");
		}
		return riderDetails;
	}

	@Override
	public RiderDetail getRiderDetailById(int id)
	{
		Optional<RiderDetail> result = riderDetailService.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("RIDERDETAIL WITH ID: " + id);
		}
		return result.get();

	}

	@Override
	public void saveRiderDetail(RiderDetail riderDetail)
	{
		try
		{
			riderDetailService.save(riderDetail);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING RIDERDETAIL");
		}
	}

	@Override
	public void deleteRiderDetailById(int id)
	{
		try
		{
			riderDetailService.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING RIDERDETAIL WITH ID: " + id);
		}

	}

	@Override
	public void deleteRiderDetail(RiderDetail riderDetail)
	{
		try
		{
			riderDetailService.delete(riderDetail);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING RIDERDETAIL");
		}
	}

}
