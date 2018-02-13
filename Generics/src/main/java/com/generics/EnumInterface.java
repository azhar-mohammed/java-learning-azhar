package com.generics;

public interface EnumInterface<T> {
	public T getVal();

	public static <T extends EnumInterface<?>, V> T getEnum(Class<T> class1, V v) {

		for (T en : class1.getEnumConstants()) {
			if (en.getVal().equals(v)) {
				return en;
			}
		}
		return null;
	}
}
