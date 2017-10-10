package com.jumper.hospital.enums;

public enum Time {

	a("00:00"),
	b("01:00"),
	c("02:00"),
	d("03:00"),
	e("04:00"),
	f("05:00"),
	g("06:00"),
	h("07:00"),
	j("08:00"),
	k("09:00"),
	l("10:00"),
	m("11:00"),
	n("12:00"),
	o("13:00"),
	p("14:00"),
	q("15:00"),
	r("16:00"),
	s("17:00"),
	t("18:00"),
	u("19:00"),
	v("20:00"),
	w("21:00"),
	x("22:00"),
	y("23:00"),
	z("24:00");
	
	private String desc;

	private Time(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
