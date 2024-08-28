package com.sung.springframe.trans;

public interface AccountRepository {
    void transfer(double amount, String fromAccountId, String toAccountId);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
