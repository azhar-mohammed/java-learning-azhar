package com.generics;

public enum ValidationType implements EnumInterface<Integer> {

	NAME(1),TELEPHONENO(2),EMAIL(3);

	private int i;

	ValidationType(int i) {
		this.i = i;
	}

	@Override
	public Integer getVal() {

		return i;
	}

}
