package com.jumper.hospital.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * PregnantAntenatalInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pregnant_antenatal_examination_info", catalog = "jumper_anglesound")
public class PregnantAntenatalInfo implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer examinationNumbers;
    private Integer startWeek;
    private Integer endWeek;
    private String remind;
    private String addTime;
    private Integer remindWeek;

    // Constructors

    /** default constructor */
    public PregnantAntenatalInfo()
    {}

    /** full constructor */
    public PregnantAntenatalInfo(Integer examinationNumbers, Integer startWeek, Integer endWeek, String remind, String addTime, Integer remindWeek)
    {
        this.examinationNumbers = examinationNumbers;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.remind = remind;
        this.addTime = addTime;
        this.remindWeek = remindWeek;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Column(name = "examination_numbers")
    public Integer getExaminationNumbers()
    {
        return this.examinationNumbers;
    }

    public void setExaminationNumbers(Integer examinationNumbers)
    {
        this.examinationNumbers = examinationNumbers;
    }

    @Column(name = "start_week")
    public Integer getStartWeek()
    {
        return this.startWeek;
    }

    public void setStartWeek(Integer startWeek)
    {
        this.startWeek = startWeek;
    }

    @Column(name = "end_week")
    public Integer getEndWeek()
    {
        return this.endWeek;
    }

    public void setEndWeek(Integer endWeek)
    {
        this.endWeek = endWeek;
    }

    @Column(name = "remind", length = 1000)
    public String getRemind()
    {
        return this.remind;
    }

    public void setRemind(String remind)
    {
        this.remind = remind;
    }

    @Column(name = "add_time", length = 19)
    public String getAddTime()
    {
        return this.addTime;
    }

    public void setAddTime(String addTime)
    {
        this.addTime = addTime;
    }

    @Column(name = "remind_week")
    public Integer getRemindWeek()
    {
        return this.remindWeek;
    }

    public void setRemindWeek(Integer remindWeek)
    {
        this.remindWeek = remindWeek;
    }

}