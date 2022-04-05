package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer>
{
	public List<Promotion> findAllByOrderByIdDesc();

//	@Query("SELECT i FROM Promotion i WHERE i.disabled =:disabled")
//	public List<Promotion> findPromotions(
//			@Param("disabled") boolean disabled);

	public List<Promotion> findByDisabledOrderByIdDesc(boolean disable);
}
