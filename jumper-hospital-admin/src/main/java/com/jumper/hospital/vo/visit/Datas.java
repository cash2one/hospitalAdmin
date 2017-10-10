package com.jumper.hospital.vo.visit;

public class Datas<T> {
	private VoCloudVisitInfo<T> datas;

	public VoCloudVisitInfo<T> getDatas() {
		return datas;
	}

	public void setDatas(VoCloudVisitInfo<T> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "Datas [datas=" + datas + "]";
	}

}
