package com.jumper.hospital.vo;

import java.io.Serializable;

public class VoObjectData<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public T object;

	public VoObjectData(T object) {
		this.object = object;
	}
	
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}

}
