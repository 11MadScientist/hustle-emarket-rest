package org.emarket.hustle.emarkethustle.service;

public interface ValidationService
{
	public boolean isEmailNotTaken(String email);

	public boolean isUsernameNotTaken(String username);
}
