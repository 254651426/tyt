package com.bquan.util.gen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bquan.util.SpringContextUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;


public class Pacauto {

	private Configuration cfg; // FreeMaker模板加载器
	private Properties ploader; // Properties文件加载器
	public String s[];
	// 生成子包及文件
	OutputStream out = null;
	Map<String, Object> root = new HashMap<String, Object>();
	List  l = new ArrayList();
	/**
	 * 初始化工作
	 */
	public void init() throws Exception {
		// 负责管理的实例创建+设置模板文件所在的目录
		this.cfg = new Configuration();
		this.cfg.setDirectoryForTemplateLoading(new File(AutoCreater.class.getResource("/template").toURI()));

		// load资源的实例创建
		this.ploader = new Properties();
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream("pac.properties");
		this.ploader.load(is);
	}

	public void show() {
		String dbserver = this.ploader.getProperty("rules");
		String news = dbserver.substring(1, dbserver.length() - 2);
		String str2 = news.replace("\"","");
		s = str2.split(",");
		for(int i=0;i<s.length;i++ )
		{
			l.add(s[i]);
		}
	}

	public void jsFlag() throws FileNotFoundException, IOException, Exception, Exception {
		Template t = this.cfg.getTemplate("Templetepac.ftl");
		root.put("paclist", l);
		out = new FileOutputStream(new File("D:/","news" + ".js"));
		t.process(root, new OutputStreamWriter(out));
	}

	public static void main(String[] args) throws Exception {
		Pacauto p = new Pacauto();
		p.init();
		p.show();
		p.jsFlag();
		

	}

}
