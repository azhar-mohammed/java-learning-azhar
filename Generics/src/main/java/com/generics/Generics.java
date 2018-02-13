package com.generics;

public class Generics {

	public static void main(String[] args) {

		String document = EnumInterface.getEnum(DocumentType.class, "adhaar").toString();
		System.out.println(document);

	}

}
