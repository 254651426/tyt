package com.bquan.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bquan.util.DirectiveUtil;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ShiroPermissionDirective implements TemplateDirectiveModel {
	
	private static final String ROLE = "role";
	
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String role = DirectiveUtil.getStringParameter(ROLE, params);
		
		boolean flag = true;
		
		if(StringUtils.isNotEmpty(role)){
			Subject subject = SecurityUtils.getSubject();
			String [] roleArray = role.split(",");
			for(String r : roleArray){
				if(!subject.isPermitted(r)){
					flag = false;
				}
			}
			
			if(flag){
				body.render(env.getOut());
			}
		}
		
	}

}