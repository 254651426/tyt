package com.bquan.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.bquan.entity.mongo.BaseMongodbEntity;

/**
 * 推送信息
 * @Description: TODO
 * @author  lxk
 * @date 2016年10月9日 下午11:13:02 
 * @version V1.0
 */
@Document
public class PushNews extends BaseMongodbEntity{
	
	private String content;
	private boolean sendFlag;
	private String userId;
	private String command;// 执行命令
	
	public PushNews(){}
	
	public PushNews(
			String content,
			boolean sendFlag,
			String userId,
			String command){
		this.content=content;
		this.sendFlag=sendFlag;
		this.userId=userId;
		this.command=command;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(boolean sendFlag) {
		this.sendFlag = sendFlag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}
