package com.jumper.hospital.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * FamilyDoctors entity. @author MyEclipse Persistence Tools
 */
@Entity
@JsonIgnoreProperties
@Table(name = "family_doctors", catalog = "jumper_anglesound")
public class FamilyDoctorinfo implements java.io.Serializable
{ 
	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer hospitalId;
    private Integer provinceId;
    private Integer cityId;
    private Integer districtId;
    private String detailAdd;
    private Integer majorId;
    private String position;
    private String phone;
    private String remark;
    private String username;
    private String password;
    private String enabled;
    private String responsibleArea;
    private String doctorName;
    private Date addDate;
    private HospitalInfo hospitalInfo;
    private List<FamilyExaminationArranged>  FamilyExaminationArranged;
  //  private HospitalDoctorMajor hospitalDoctorMajor;
    

@OneToMany()
@JoinColumn(name="doctorid")
    public List<FamilyExaminationArranged> getFamilyExaminationArranged() {
		return FamilyExaminationArranged;
	}

	public void setFamilyExaminationArranged(
			List<FamilyExaminationArranged> familyExaminationArranged) {
		FamilyExaminationArranged = familyExaminationArranged;
	}

	/** default constructor */
    public FamilyDoctorinfo()
    {}

    /** minimal constructor */
    public FamilyDoctorinfo(Integer hospitalId, String username, String password, String doctorName)
    {
        this.hospitalId = hospitalId;
        this.username = username;
        this.password = password;
        this.doctorName = doctorName;
    }

    /** full constructor */
    public FamilyDoctorinfo(Integer hospitalId, Integer provinceId, Integer cityId, Integer districtId, String detailAdd, Integer majorId, String position, String phone, String remark, String username,
            String password, String isEnabled, String responsibleArea, String doctorName)
    {
        this.hospitalId = hospitalId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.districtId = districtId;
        this.detailAdd = detailAdd;
        this.majorId = majorId;
        this.position = position;
        this.phone = phone;
        this.remark = remark;
        this.username = username;
        this.password = password;
        this.enabled = isEnabled;
        this.responsibleArea = responsibleArea;
        this.doctorName = doctorName;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Column(name = "hospital_id")
    public Integer getHospitalId()
    {
        return this.hospitalId;
    }

    public void setHospitalId(Integer hospitalId)
    {
        this.hospitalId = hospitalId;
    }

@JoinColumn(name="hospital_id", nullable = false,insertable = false, updatable = false)
@ManyToOne()
@JsonIgnoreProperties(ignoreUnknown = true)
    public HospitalInfo getHospitalInfo()
    {
        return hospitalInfo;
    }

    public void setHospitalInfo(HospitalInfo hospitalInfo)
    {
        this.hospitalInfo = hospitalInfo;
    }

    @Column(name = "add_date" )
    public Date getAddDate()
    {
        return this.addDate;
    }

    public void setAddDate(Date addDate)
    {
        this.addDate = addDate;
    }
    
    @Column(name = "province_id")
    public Integer getProvinceId()
    {
        return this.provinceId;
    }

    public void setProvinceId(Integer provinceId)
    {
        this.provinceId = provinceId;
    }

    @Column(name = "city_id")
    public Integer getCityId()
    {
        return this.cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    @Column(name = "district_id")
    public Integer getDistrictId()
    {
        return this.districtId;
    }

    public void setDistrictId(Integer districtId)
    {
        this.districtId = districtId;
    }

    @Column(name = "detail_add", length = 200)
    public String getDetailAdd()
    {
        return this.detailAdd;
    }

    public void setDetailAdd(String detailAdd)
    {
        this.detailAdd = detailAdd;
    }


    @Column(name = "position", length = 20)
    public String getPosition()
    {
        return this.position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    @Column(name = "phone", length = 30)
    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Column(name = "remark", length = 200)
    public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Column(name = "username", nullable = false, length = 100)
    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 100)
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name = "enabled", length = 2)
    public String getEnabled()
    {
        return this.enabled;
    }

    public void setEnabled(String isEnabled)
    {
        this.enabled = isEnabled;
    }

    @Column(name = "responsible_area")
    public String getResponsibleArea()
    {
        return this.responsibleArea;
    }

    public void setResponsibleArea(String responsibleArea)
    {
        this.responsibleArea = responsibleArea;
    }

    @Column(name = "doctor_name", nullable = false)
    public String getDoctorName()
    {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName)
    {
        this.doctorName = doctorName;
    }
    @Column(name = "major_id")
    public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
/*@ManyToOne
@JoinColumn(name = "major_id",insertable=false,updatable=false)
public HospitalDoctorMajor getHospitalDoctorMajor() {
	return hospitalDoctorMajor;
}

public void setHospitalDoctorMajor(HospitalDoctorMajor hospitalDoctorMajor) {
	this.hospitalDoctorMajor = hospitalDoctorMajor;
} */

@Transient
	public static long getSerialversionuid() {
	return serialVersionUID;
}

	@Override
    public String toString()
    {
        return "FamilyDoctorinfo [id=" + id + ", hospitalId=" + hospitalId + ", provinceId=" + provinceId + ", cityId=" + cityId + ", districtId=" + districtId + ", detailAdd=" + detailAdd
                + ", majorId=" + majorId + ", position=" + position + ", phone=" + phone + ", remark=" + remark + ", username=" + username + ", password=" + password + ", enabled=" + enabled
                + ", responsibleArea=" + responsibleArea + ", doctorName=" + doctorName + "]";
    }



}