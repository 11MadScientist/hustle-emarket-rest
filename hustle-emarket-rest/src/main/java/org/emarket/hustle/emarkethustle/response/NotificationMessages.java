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
		notification.setNotifType("Order Declined");
//		notification.setLink("");
		notification
				.setMessage("Order Number: &lt;font color='black'>&lt;b>" + String.format("%08d", transaction.getId()) +
						"&lt;/b>&lt;/font> have items declined. Click to check.");

		return notification;

	}

//	transaction Preparing stage

	public static Notification transactionPreparing(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setNotifType("Order Preparing");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", transaction.getId())
				+ "&lt;/b>&lt;/font> is being prepared for pickup.");
		return notification;
	}

//	transaction Delivering

	public static Notification transactionDelivering(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setNotifType("Order On Delivery");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", transaction.getId())
				+ "&lt;/b>&lt;/font> has been picked up by the rider. Prepare a cash total &lt;font> color='black'&lt;b>₱"
				+ transaction.getGrandTotal()
				+ "\"&lt;/b>&lt;/font> and wait for a few minutes for a door to door delivery.");
		return notification;
	}

//	transaction Arrived

	public static Notification transactionArrived(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setNotifType("Order Arrived");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", transaction.getId())
				+ "&lt;/b>&lt;/font> has arrived. Receive your Order from the Rider.");
		return notification;
	}

// transaction complete
	public static Notification customerTransactionComplete(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setNotifType("Order Complete");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", transaction.getId())
				+ "&lt;/b>&lt;/font> has been completed."
				+ "Looking forward to your next shopping!");
		return notification;
	}

//	pickup ready for customer
	public static Notification readyForPickup(Transaction transaction)
	{
		Notification notification = new Notification();
		notification.setUserId(transaction.getCustomer().getId());
		notification.setNotifType("Ready For Pickup");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", transaction.getId())
				+ "&lt;/b>&lt;/font> is ready for Pick Up."
				+ "Looking forward to arrival!");
		return notification;
	}

	/* seller notifications */
//	customer cancels
	public static Notification customerCancels(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setNotifType("Order Cancelled");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", history.getId())
				+ "&lt;/b>&lt;/font> has been cancelled.");
		return notification;
	}

//	customer continues
	public static Notification customerContinues(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setNotifType("Order Proceed");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", history.getId())
				+ "&lt;/b>&lt;/font>: "
				+ history.getQuantity() + " " + history.getItem().getMeasurement()
				+ " of " + history.getItemName()
				+ " needs to be prepared and delivered to the station,"
				+ " with Transaction ID: &lt;font color='black'>&lt;b>"
				+ history.getTransaction().getId()
				+ "&lt;/b>&lt;/font>: ");
		return notification;
	}

//	transaction delivered successfully
	public static Notification sellerTransactionComplete(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setNotifType("Order Complete");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", history.getId())
				+ "&lt;/b>&lt;/font>: "
				+ history.getQuantity() + " " + history.getItem().getMeasurement()
				+ " of " + history.getItemName()
				+ " with Transaction ID: &lt;font color='black'>&lt;b>"
				+ history.getTransaction().getId()
				+ "&lt;/b>&lt;/font>: "
				+ " has been completed!");
		return notification;
	}

//	rated the product
	public static Notification itemRated(History history)
	{
		Notification notification = new Notification();
		notification.setUserId(history.getStore().getSeller().getId());
		notification.setNotifType("Item Rated");
//		notification.setLink(null);
		notification.setMessage("Order Number: &lt;font color='black'>&lt;b>"
				+ String.format("%08d", history.getId())
				+ "&lt;/b>&lt;/font>: "
				+ " of " + history.getItemName()
				+ " with Transaction ID: &lt;font color='black'>&lt;b>"
				+ history.getTransaction().getId()
				+ "&lt;/b>&lt;/font>: "
				+ " has been rated!");
		return notification;
	}

//
}
