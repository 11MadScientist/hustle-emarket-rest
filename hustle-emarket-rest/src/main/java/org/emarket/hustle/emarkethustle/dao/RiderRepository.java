package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RiderRepository extends JpaRepository<Rider, Integer>
{
	public Rider findByUsername(String username);

	@Query("SELECT i FROM Rider i WHERE i.riderDetail.authorized =:authorized"
			+ " and i.riderDetail.prohibited =:prohibited "
			+ "and (i.firstName LIKE %:searchPattern% or i.lastName LIKE %:searchPattern% "
			+ "or i.username LIKE %:searchPattern%)")
	public List<Rider> findRider(
			@Param("authorized") boolean authorize,
			@Param("prohibited") boolean prohibited,
			@Param("searchPattern") String searchPattern);

	@Query("SELECT COUNT(i) FROM Rider i WHERE i.riderDetail.authorized = false")
	public int countRiderRequest();
}
