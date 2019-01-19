package de.ics.cardmarket4j.structs;

import com.google.gson.JsonObject;

public class BankAccount {
	private String accountOwner;
	private String iban;
	private String bic;
	private String bankName;

	public BankAccount(JsonObject jAccount) {
		JsonObject j = jAccount.get("bankAccount").getAsJsonObject();
		this.accountOwner = j.get("accountOwner").getAsString();
		this.iban = j.get("iban").getAsString();
		this.bic = j.get("bic").getAsString();
		this.bankName = j.get("bankName").getAsString();
	}

	@Override
	public String toString() {
		return "BankAccount [accountOwner=" + accountOwner + ", iban=" + iban + ", bic=" + bic + ", bankName="
				+ bankName + "]";
	}
}
