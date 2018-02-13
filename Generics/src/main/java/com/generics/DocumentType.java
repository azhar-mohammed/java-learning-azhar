package com.generics;

public enum DocumentType implements EnumInterface<String> {
	
	PANCARD("pan"),ADHAAR("adhaar"),BANKSTATEMENT("stmt");

	 String name;
	 
	 DocumentType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getVal() {
		// TODO Auto-generated method stub
		return name;
	}
	

}
