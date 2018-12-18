package com.bquan.controller.pc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bquan.util.JsonUtil;
import com.bquan.util.gen.AutoCreater;

import freemarker.template.Configuration;
import freemarker.template.Template;

import com.bquan.bean.AjaxResponse;
import com.bquan.entity.mysql.WebDomain;
import com.bquan.service.write.WebDomainWriteService;

@Controller
@RequestMapping(value = "/pc/pacauto")
public class Pacauto extends BasePcController {
	List<WebDomain> domainlist = new ArrayList<WebDomain>();
	String ss=null;
	String s[] = null;
	private Properties ploader; // Properties文件加载器
	@Autowired
	private WebDomainWriteService webDomainWriteService;

	@RequestMapping(value = "/addBatch", method = RequestMethod.POST)
	public String addBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		{
			AjaxResponse ajaxRes = new AjaxResponse();
			// load资源的实例创建
			this.ploader = new Properties();
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream is = cl.getResourceAsStream("pac.properties");
			this.ploader.load(is);
			ss = this.ploader.getProperty("pac");
			try {
				s = ss.split(",");
				for (int i = 0; i < s.length; i++) {
					WebDomain webDomain = new WebDomain();
					webDomain.setDomain(s[i]);
					webDomain.setUserId("");
					webDomain.setCreateDate(new Date());
					webDomain.setType("1");
					webDomain.setStatus("0");
					webDomain.setIsWall("0");
					domainlist.add(webDomain);
				}
				webDomainWriteService.insertBatch(domainlist);
				ajaxRes.setMsg("添加成功！");
				ajaxRes.setCode(AjaxResponse.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxRes.setMsg("添加失败！");
				ajaxRes.setCode(AjaxResponse.FAILURE);
			}
			return output(response, JsonUtil.toJson(ajaxRes));
		}
	}
}
