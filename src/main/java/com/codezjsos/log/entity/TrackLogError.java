package com.codezjsos.log.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tracklog_error")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TrackLogError extends TrackBase{

	private String unid;
	private Date createtime;
	private String errormsg;
	private String remark;
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(strategy="uuid",name="uuid")
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
