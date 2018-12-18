package com.bquan.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class TreeNode implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String id;		// 节点的id
	private String pId;		// 节点的父级的id
	private String name;	// 节点名称
	private boolean open;	// 默认是否打开（true代表打开） 
	private boolean isParent;// 是否有下级节点（是显示为文件夹的样式）
	private String path;	// 文件的路径
	private boolean isFile; // 是否为文件（true代表为文件，false代表为文件夹）
	private BigDecimal size;// 大小
	private String time;	// 最后修改时间
	
	public TreeNode(
			String id,
			String pId,
			String name,
			boolean open,
			boolean isParent,
			String time){
		this.id=id;
		this.pId=pId;
		this.name=name;
		this.open=open;
		this.isParent=isParent;
		this.time=time;
	}
	
	public TreeNode(
			String id,String pId,
			String name,boolean open,
			boolean isParent,
			String path,
			boolean isFile,
			BigDecimal size,
			String time){
		this.id=id;
		this.pId=pId;
		this.name=name;
		this.open=open;
		this.isParent=isParent;
		this.path=path;
		this.isFile=isFile;
		this.size=size;
		this.time=time;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
