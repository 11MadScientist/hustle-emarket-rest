package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.RiderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderDetailRepository extends JpaRepository<RiderDetail, Integer>
{
	public RiderDetail findByEmail(String email);
}
