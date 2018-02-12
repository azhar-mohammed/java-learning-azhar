package com.generics;

public class Generics {

	public static void main(String[] args) {

		Object e = EnumInterface.getEnum(DocumentType.class, "adhaar");
		String str = e.toString();
		System.out.println(str);

	}

}
