package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.RiderDetail;

public interface RiderDetailService
{
	public List<RiderDetail> getRiderDetail();

	public RiderDetail getRiderDetailById(int id);

	public void saveRiderDetail(RiderDetail riderDetail);

	public void deleteRiderDetailById(int id);

	public void deleteRiderDetail(RiderDetail riderDetail);
}
