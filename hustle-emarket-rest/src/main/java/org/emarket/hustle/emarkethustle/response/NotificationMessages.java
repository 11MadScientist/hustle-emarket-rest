package org.emarket.hustle.emarkethustle.response;

import org.emarket.hustle.emarkethustle.entity.History;
import org.emarket.hustle.emarkethustle.entity.Notification;
import org.emarket.hustle.emarkethustle.entity.Transaction;

public class NotificationMessages
{
	/* customer notification */

//	seller declines
	public static Notification sellerDeclines(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink("");
		notification
				.setMessage("Order Number:(" + String.format("%08d", transaction.getId()) +
						") have items declined. Click to check.");

		return notification;

	}

// all seller declines
	public static Notification allSellerDeclines(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink("");
		notification
				.setMessage("Order Number:(" + String.format("%08d", transaction.getId()) +
						") has been cancelled as all sellers declines");

		return notification;
	}

//	transaction Preparing stage

	public static Notification transactionPreparing(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", transaction.getId())
				+ ") is being prepared.");
		return notification;
	}

//	transaction Delivering

	public static Notification transactionDelivering(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Order Delivery");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", transaction.getId())
				+ ") has been picked up by the rider. Prepare a cash total &lt;font> color='black'&lt;b>₱"
				+ transaction.getGrandTotal()
				+ "\") and wait for a few minutes for a door to door delivery.");
		return notification;
	}

//	transaction Arrived

	public static Notification transactionArrived(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Order Delivery");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", transaction.getId())
				+ ") has arrived. Receive your Order from the Rider.");
		return notification;
	}

// transaction complete
	public static Notification customerTransactionComplete(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Feedback");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", transaction.getId())
				+ ") has been completed."
				+ "Looking forward to your next shopping!");
		return notification;
	}

//	pickup ready for customer
	public static Notification readyForPickup(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setRole(transaction.getCustomer().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", transaction.getId())
				+ ") is ready for Pick Up."
				+ "Looking forward to arrival!");
		return notification;
	}

	/* seller notifications */
//	customer cancels
	public static Notification customerCancels(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setRole(history.getStore().getSeller().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", history.getId())
				+ ") has been cancelled.");
		return notification;
	}

//	customer continues
	public static Notification customerContinues(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setRole(history.getStore().getSeller().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", history.getId())
				+ "): "
				+ history.getQuantity() + " " + history.getItem().getMeasurement()
				+ " of " + history.getItemName()
				+ " needs to be prepared and delivered to the station,"
				+ " with Transaction ID:("
				+ history.getTransaction().getId()
				+ "): ");
		return notification;
	}

//	transaction delivered successfully
	public static Notification sellerTransactionComplete(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setRole(history.getStore().getSeller().getRole());
		notification.setNotifType("Order Status");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", history.getId())
				+ "): "
				+ history.getQuantity() + " " + history.getItem().getMeasurement()
				+ " of " + history.getItemName()
				+ " with Transaction ID:("
				+ history.getTransaction().getId()
				+ "): "
				+ " has been completed!"
				+ " You can claim your profit at the counter after a while.");
		return notification;
	}

//	rated the product
	public static Notification itemRated(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setRole(history.getStore().getSeller().getRole());
		notification.setNotifType("Feedback");
//		notification.setLink(null);
		notification.setMessage("Order Number:("
				+ String.format("%08d", history.getId())
				+ "): "
				+ " of " + history.getItemName()
				+ " with Transaction ID:("
				+ history.getTransaction().getId()
				+ "): "
				+ " has been rated!");
		return notification;
	}

//
}
