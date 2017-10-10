package com.jumper.hospital.entity;
/**
 * 远程监控项目数据(和远程监控项目多对一关系，即一个项目对应多条监测数据)
 * @author rent
 * @date 2015-09-17
 */
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="monitor_project_record")
public class MonitorProjectRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 本次记录所对应的监控项目 **/
	private MonitorProject projectId;
	/** 测试总表记录ID **/
	private Integer recordId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="project_id")
	public MonitorProject getProjectId() {
		return projectId;
	}
	public void setProjectId(MonitorProject projectId) {
		this.projectId = projectId;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
}
