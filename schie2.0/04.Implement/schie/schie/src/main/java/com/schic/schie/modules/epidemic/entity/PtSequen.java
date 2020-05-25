/**
 *
 */
package com.schic.schie.modules.epidemic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeespring.common.persistence.AbstractBaseEntity;
import com.jeespring.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 时序高频实体Entity
 * @author Leo
 * @version 2020-03-11
 */
public class PtSequen extends AbstractBaseEntity<PtSequen> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// 病患id
	private String cjDate;		// 采集日期
	private String ofShizhou;		// 所属市周
	private String ofQuxian;		// 所属区县
	private String stationAddr;		// 基站地址
	private Integer stayTimes;		// 累计驻留次数
	private Date beginCreateDate;		// 开始 入库时间
	private Date CreateDate;		// 入库时间
	private Date endCreateDate;		// 结束 入库时间
	
	public PtSequen() {
		super();
	}

	public PtSequen(String id){
		super(id);
	}

	@Length(min=0, max=64, message="病患id长度必须介于 0 和 64 之间")
	@ExcelField(title="病患id", align=2, sort=1)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}


	@Length(min=0, max=100, message="采集日期长度必须介于 0 和 100 之间")
	@ExcelField(title="采集日期", align=2, sort=2)
	public String getCjDate() {
		return cjDate;
	}

	public void setCjDate(String cjDate) {
		this.cjDate = cjDate;
	}


	@Length(min=0, max=255, message="所属市周长度必须介于 0 和 255 之间")
	@ExcelField(title="所属市周", align=2, sort=3)
	public String getOfShizhou() {
		return ofShizhou;
	}

	public void setOfShizhou(String ofShizhou) {
		this.ofShizhou = ofShizhou;
	}


	@Length(min=0, max=255, message="所属区县长度必须介于 0 和 255 之间")
	@ExcelField(title="所属区县", align=2, sort=4)
	public String getOfQuxian() {
		return ofQuxian;
	}

	public void setOfQuxian(String ofQuxian) {
		this.ofQuxian = ofQuxian;
	}


	@Length(min=0, max=255, message="基站地址长度必须介于 0 和 255 之间")
	@ExcelField(title="基站地址", align=2, sort=5)
	public String getStationAddr() {
		return stationAddr;
	}

	public void setStationAddr(String stationAddr) {
		this.stationAddr = stationAddr;
	}


	@ExcelField(title="累计驻留次数", align=2, sort=6)
	public Integer getStayTimes() {
		return stayTimes;
	}

	public void setStayTimes(Integer stayTimes) {
		this.stayTimes = stayTimes;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Override
	public Date getCreateDate() {
		return CreateDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Override
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
}