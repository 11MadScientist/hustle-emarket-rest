package org.emarket.hustle.emarkethustle.response;

public class EmailMessages
{
	public static String registrationMessage(String role)
	{
		return ("Your application to Emarket Application as a " + role +
				" has been RECEIVED, please wait for the confirmation from the administrator");
	}

	public static String applicationApproved(String role)
	{
		return ("Congratulations! Your application to Emarket Application as a " + role +
				" has been APPROVED, you can now log in to the application using your credentials");
	}

	public static String prohibitedMessage(boolean prohibited)
	{
		if(prohibited)
		{
			return ("Your account has been BANNED, to appeal and recover your account,"
					+ " email us at emarket.teamhustle@gmail.com");
		}

		return ("Congratulations, your account has been UNBANNED,"
				+ " please follow EMARKET'S guidelines as you continue to enjoy"
				+ " our application. Thank You!");
	}
}
