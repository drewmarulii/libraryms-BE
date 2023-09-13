package com.drewdev.libraryms.constant;

public enum BookStatusConst {
	AVAILABLE("S1001"), OUT("S1002"), BROKEN("S1003"), LOST("S1004");
	
	private final String statusCode;

	BookStatusConst(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
	
}
