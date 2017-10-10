package com.jumper.hospital.vo.familyDoctor;
	public class VOProvince  {
		private String proName;
		private String remark;
		private String abbrevation;
	    private Integer  id;
		public VOProvince() {}
		public VOProvince(Integer id,String proName) {
			this.proName = proName;
			this.id = id;
		}
		public String getProName() {
			return proName;
		}
		public void setProName(String proName) {
			this.proName = proName;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getAbbrevation() {
			return abbrevation;
		}
		public void setAbbrevation(String abbrevation) {
			this.abbrevation = abbrevation;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
}
