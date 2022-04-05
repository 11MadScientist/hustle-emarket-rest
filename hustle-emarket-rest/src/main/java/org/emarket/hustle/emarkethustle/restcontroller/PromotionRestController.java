package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Promotion;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.PromotionService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class PromotionRestController
{
	@Autowired
	private PromotionService promotionService;

	Logger log = Logger.getLogger(PromotionRestController.class.getName());

	/*
	 * #######################################
	 * ########## GET PROMOTIONS #############
	 * #######################################
	 */

	@GetMapping("/promotions")
	public List<Promotion> getPromotions()

	{
		return promotionService.getPromotionsDisabled(false);
	}

	/*
	 * #######################################
	 * ####### GET PROMOTION BY ID ###########
	 * #######################################
	 */

	@GetMapping("/promotions/{id}")
	public Promotion getPromotionById(
			@PathVariable int id)

	{
		return promotionService.getPromotionById(id);
	}

	/*
	 * #######################################
	 * ########### ADD PROMOTION #############
	 * #######################################
	 */

	@PostMapping("/promotions")
	public Promotion addPromotions(@RequestBody Promotion promotion)
	{
		promotionService.addPromotion(promotion);
		return promotion;
	}

	/*
	 * #######################################
	 * ######### UPDATE PROMOTION ############
	 * #######################################
	 */

	@PutMapping("/promotions")
	public Promotion updatePromotions(@RequestBody Promotion promotion)
	{
		promotionService.updatePromotion(promotion);
		return promotion;
	}

	/*
	 * #######################################
	 * ####### DELETE NOTIFICATION ###########
	 * #######################################
	 */

	@DeleteMapping("/promotions")
	public ProcessConfirmation deletePromotion(@RequestBody Promotion promotion)
	{
		promotionService.deletePromotion(promotion);

		return new ProcessConfirmation("SUCCESS", "PROMOTION",
				"THE ITEM WITH ID:" + promotion.getId() + " WAS DELETED.");

	}
}
