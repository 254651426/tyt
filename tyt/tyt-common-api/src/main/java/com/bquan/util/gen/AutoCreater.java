package com.bquan.util.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * SpringMvc+Srping+MyBatis框架基础部分生成器(基于FreeMaker)
 * 本类主要根据配置文件input.properties的信息生成对应基础类,接口,实现类等等
 * 
 * @author Rocye
 * @createTime 2015-10-19
 */
public class AutoCreater {

	private Configuration cfg; // FreeMaker模板加载器
	private Properties ploader; // Properties文件加载器

	// private static String temppath; //模版目录
	// static{
	// temppath = System.getProperty("user.dir") + File.separator + "src" +
	// File.separator + "templet";
	// }

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
		InputStream is = cl.getResourceAsStream("gen.properties");
		this.ploader.load(is);
	}

	/**
	 * 生成JAVA文件
	 */
	public void process(boolean isAddMenu, boolean jsFlag, boolean htmlFlag, boolean controllerFlag,
			boolean customerConfigFlag, boolean providerConfigFlag, boolean entityFlag, boolean readServiceFlag,
			boolean readServiceImplFlag, boolean writeServiceFlag, boolean writeServiceImplFlag, boolean readDaoFlag,
			boolean writeDaoFlag, boolean readMapperFlag, boolean writeMapperFlag)
			throws IOException, TemplateException {
		// 从Properties中加载类信息
		ClassInfo clazz = loadClassInfo();

		String dbserver = this.ploader.getProperty("dbserver");
		String dbuser = this.ploader.getProperty("dbuser");
		String dbpass = this.ploader.getProperty("dbpass");

		// 直接从库中指定表读取所有属性及备注
		List<Attribute> attrList = GetInputPropertiesData.getTablecolumns(clazz.getTablename(), dbserver, dbuser,
				dbpass);

		// 向root中放入模版中所需信息
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("clazzinfo", clazz);
		root.put("attributes", attrList);

		// 将模版进行指定文件的输出
		String packageFolder[] = StringUtils.split(clazz.getPackagename(), ".");
		String packagePath = File.separator;
		for (int i = 0; i < packageFolder.length; i++) {
			packagePath += packageFolder[i] + File.separator;
		}

		// 生成子包及文件
		OutputStream out = null;

		if (isAddMenu) {
			List<String> sqlList = new ArrayList<String>();
			sqlList.add("INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)"
					+ " VALUES ('1', '" + new String(this.ploader.getProperty("objname").getBytes("iso8859-1"), "utf-8")
					+ "', 'sys/" + this.ploader.getProperty("tableas") + ".html', 'sys:"
					+ this.ploader.getProperty("tableas") + ":list', '1', 'fa fa-file-code-o', '6');");
			sqlList.add("set @parentId = @@identity;");
			sqlList.add("INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)"
					+ "SELECT @parentId, '查看', null, 'sys:" + this.ploader.getProperty("tableas") + ":list,sys:"
					+ this.ploader.getProperty("tableas") + ":info', '2', null, '6';");
			sqlList.add("INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)"
					+ "SELECT @parentId, '新增', null, 'sys:" + this.ploader.getProperty("tableas")
					+ ":save', '2', null, '6';");
			sqlList.add("INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)"
					+ "SELECT @parentId, '修改', null, 'sys:" + this.ploader.getProperty("tableas")
					+ ":update', '2', null, '6';");
			sqlList.add("INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)"
					+ "SELECT @parentId, '删除', null, 'sys:" + this.ploader.getProperty("tableas")
					+ ":delete', '2', null, '6';");
			GetInputPropertiesData.excSql(sqlList, dbserver, dbuser, dbpass);
		}

		/**
		 * 生成js文件
		 */
		if (jsFlag) {
			File jsFile = new File(this.ploader.getProperty("webPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "webapp" + File.separator + "js" + File.separator + "sys");
			jsFile.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempleteJs.ftl");
				out = new FileOutputStream(new File(jsFile.getPath(), this.ploader.getProperty("tableas") + ".js"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成页面
		 */
		if (htmlFlag) {
			File htmlFile = new File(this.ploader.getProperty("webPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "page"
					+ File.separator + "sys");
			htmlFile.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempleteHtml.ftl");
				out = new FileOutputStream(new File(htmlFile.getPath(), this.ploader.getProperty("tableas")) + ".html");
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成action文件
		 */
		if (controllerFlag) {
			File actionConfig = new File(this.ploader.getProperty("webPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "bquan"
					+ File.separator + "controller" + File.separator + "sys");
			actionConfig.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletAction.ftl");
				out = new FileOutputStream(new File(actionConfig.getPath(),
						"Sys" + this.ploader.getProperty("classname") + "Controller.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成customer文件
		 */
		if (customerConfigFlag) {
			File customerConfig = new File(this.ploader.getProperty("webPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "resources" + File.separator + "customer");
			customerConfig.mkdirs();
			try {
				Template t = this.cfg.getTemplate("Customer-Config.ftl");
				out = new FileOutputStream(
						new File(customerConfig.getPath(), "customer-" + this.ploader.getProperty("tableas") + ".xml"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成provider配置文件
		 */
		if (providerConfigFlag) {
			File providerConfig = new File(this.ploader.getProperty("providerPath") + File.separator + "src"
					+ File.separator + "main" + File.separator + "resources" + File.separator + "provider");
			providerConfig.mkdirs();
			try {
				Template t = this.cfg.getTemplate("Provider-Config.ftl");
				out = new FileOutputStream(
						new File(providerConfig.getPath(), "provider-" + this.ploader.getProperty("tableas") + ".xml"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成实体类
		 * 
		 */
		if (entityFlag) {
			File fileEntity = new File(this.ploader.getProperty("apiPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "bquan"
					+ File.separator + "entity" + File.separator + "mysql");
			fileEntity.mkdirs();
			try {
				Template t = this.cfg.getTemplate("Entity.ftl");
				out = new FileOutputStream(new File(fileEntity.getPath(), clazz.getClassname() + ".java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成Dao读接口
		 * 
		 */
		if (readDaoFlag) {
			File fileDaoRead = new File(this.ploader.getProperty("apiPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "bquan"
					+ File.separator + "dao" + File.separator + "read");
			fileDaoRead.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletDaoRead.ftl");
				out = new FileOutputStream(new File(fileDaoRead.getPath(), clazz.getClassname() + "ReadMapper.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成Dao写接口
		 * 
		 */
		if (writeDaoFlag) {
			File fileDaoWrite = new File(this.ploader.getProperty("apiPath") + File.separator + "src" + File.separator
					+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "bquan"
					+ File.separator + "dao" + File.separator + "write");
			fileDaoWrite.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletDaoWrite.ftl");
				out = new FileOutputStream(new File(fileDaoWrite.getPath(), clazz.getClassname() + "WriteMapper.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成Service读接口
		 * 
		 */
		if (readServiceFlag) {
			File fileServiceRead = new File(this.ploader.getProperty("apiPath") + File.separator + "src"
					+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator
					+ "bquan" + File.separator + "service" + File.separator + "read");
			fileServiceRead.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletReadService.ftl");
				out = new FileOutputStream(
						new File(fileServiceRead.getPath(), clazz.getClassname() + "ReadService.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成ServiceImpl读接口
		 * 
		 */
		if (readServiceImplFlag) {
			File fileServiceReadImpl = new File(this.ploader.getProperty("providerPath") + File.separator + "src"
					+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator
					+ "bquan" + File.separator + "service" + File.separator + "read");
			fileServiceReadImpl.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletReadServiceImpl.ftl");
				out = new FileOutputStream(
						new File(fileServiceReadImpl.getPath(), clazz.getClassname() + "ReadServiceImpl.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		// ----
		/**
		 * 生成Service写接口
		 * 
		 */
		if (writeServiceFlag) {
			File fileServiceWrite = new File(this.ploader.getProperty("apiPath") + File.separator + "src"
					+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator
					+ "bquan" + File.separator + "service" + File.separator + "write");
			fileServiceWrite.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletWriteService.ftl");
				out = new FileOutputStream(
						new File(fileServiceWrite.getPath(), clazz.getClassname() + "WriteService.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 生成ServiceImpl读接口
		 * 
		 */
		if (writeServiceImplFlag) {
			File fileServiceWriteImpl = new File(this.ploader.getProperty("providerPath") + File.separator + "src"
					+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator
					+ "bquan" + File.separator + "service" + File.separator + "write");
			fileServiceWriteImpl.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletWriteServiceImpl.ftl");
				out = new FileOutputStream(
						new File(fileServiceWriteImpl.getPath(), clazz.getClassname() + "WriteServiceImpl.java"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 读mapper文件
		 * 
		 */
		if (readMapperFlag) {
			File fileReadMapper = new File(
					this.ploader.getProperty("providerPath") + File.separator + "src" + File.separator + "main"
							+ File.separator + "resources" + File.separator + "mappers" + File.separator + "reads");
			fileReadMapper.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletXmlRead.ftl");
				out = new FileOutputStream(new File(fileReadMapper.getPath(), clazz.getClassname() + "ReadMapper.xml"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

		/**
		 * 写mapper文件
		 * 
		 */
		if (writeMapperFlag) {
			File fileWriteMapper = new File(
					this.ploader.getProperty("providerPath") + File.separator + "src" + File.separator + "main"
							+ File.separator + "resources" + File.separator + "mappers" + File.separator + "writes");
			fileWriteMapper.mkdirs();
			try {
				Template t = this.cfg.getTemplate("TempletXmlWrite.ftl");
				out = new FileOutputStream(
						new File(fileWriteMapper.getPath(), clazz.getClassname() + "WriteMapper.xml"));
				t.process(root, new OutputStreamWriter(out));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

	}

	public void deleteGenFile() throws IOException, TemplateException {
		// 从Properties中加载类信息
		ClassInfo clazz = loadClassInfo();

		String dbserver = this.ploader.getProperty("dbserver");
		String dbuser = this.ploader.getProperty("dbuser");
		String dbpass = this.ploader.getProperty("dbpass");

		// 直接从库中指定表读取所有属性及备注
		List<Attribute> attrList = GetInputPropertiesData.getTablecolumns(clazz.getTablename(), dbserver, dbuser,
				dbpass);

		// 向root中放入模版中所需信息
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("clazzinfo", clazz);
		root.put("attributes", attrList);

		// 将模版进行指定文件的输出
		String packageFolder[] = StringUtils.split(clazz.getPackagename(), ".");
		String packagePath = File.separator;
		for (int i = 0; i < packageFolder.length; i++) {
			packagePath += packageFolder[i] + File.separator;
		}

		// 生成子包及文件
		OutputStream out = null;

		File InputPage = new File(this.ploader.getProperty("webPath") + File.separator + "WebRoot" + File.separator
				+ "WEB-INF" + File.separator + "template" + File.separator + "admin");
		File inputFile = new File(InputPage.getPath(),
				Utils.camelToUnderline(this.ploader.getProperty("tableas")) + "_input.ftl");
		if (inputFile.exists()) {
			inputFile.delete();
		}

		File ListPage = new File(this.ploader.getProperty("webPath") + File.separator + "WebRoot" + File.separator
				+ "WEB-INF" + File.separator + "template" + File.separator + "admin");
		File listFile = new File(ListPage.getPath(),
				Utils.camelToUnderline(this.ploader.getProperty("tableas")) + "_list.ftl");
		if (listFile.exists()) {
			listFile.delete();
		}

		/**
		 * 生成action文件
		 */
		File actionConfig = new File(this.ploader.getProperty("webPath") + File.separator + "src" + File.separator
				+ "com" + File.separator + "telecom" + File.separator + "action" + File.separator + "admin");
		actionConfig.mkdirs();
		File actionFile = new File(actionConfig.getPath(), this.ploader.getProperty("classname") + "Action.java");
		if (actionFile.exists()) {
			actionFile.delete();
		}

		/**
		 * 生成customer文件
		 */
		File customerConfig = new File(
				this.ploader.getProperty("webPath") + File.separator + "src" + File.separator + "spring_dubbo");
		customerConfig.mkdirs();
		File customerFile = new File(customerConfig.getPath(),
				"customer-" + this.ploader.getProperty("tableas") + ".xml");
		if (customerFile.exists()) {
			customerFile.delete();
		}

		/**
		 * 生成provider配置文件
		 */
		File providerConfig = new File(this.ploader.getProperty("providerPath") + File.separator + "src"
				+ File.separator + "main" + File.separator + "resources" + File.separator + "provider");
		providerConfig.mkdirs();
		File providerFile = new File(providerConfig.getPath(),
				"provider-" + this.ploader.getProperty("tableas") + ".xml");
		if (providerFile.exists()) {
			providerFile.delete();
		}

		/**
		 * 生成实体类
		 * 
		 */
		File fileEntity = new File(this.ploader.getProperty("apiPath") + File.separator + "src" + File.separator
				+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "alibaba"
				+ File.separator + "api" + File.separator + "entity" + File.separator + "mysql");
		fileEntity.mkdirs();
		File entityFile = new File(fileEntity.getPath(), clazz.getClassname() + ".java");
		if (entityFile.exists()) {
			entityFile.delete();
		}

		/**
		 * 生成Dao读接口
		 * 
		 */
		File fileDaoRead = new File(this.ploader.getProperty("providerPath") + File.separator + "src" + File.separator
				+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "alibaba"
				+ File.separator + "provider" + File.separator + "dao" + File.separator + "reads");
		fileDaoRead.mkdirs();
		File fileDaoReadFile = new File(fileDaoRead.getPath(), clazz.getClassname() + "ReadMapper.java");
		if (fileDaoReadFile.exists()) {
			fileDaoReadFile.delete();
		}

		/**
		 * 生成Dao写接口
		 * 
		 */
		File fileDaoWrite = new File(this.ploader.getProperty("providerPath") + File.separator + "src" + File.separator
				+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "alibaba"
				+ File.separator + "provider" + File.separator + "dao" + File.separator + "writes");
		fileDaoWrite.mkdirs();
		File fileDaoWriteFile = new File(fileDaoWrite.getPath(), clazz.getClassname() + "WriteMapper.java");
		if (fileDaoWriteFile.exists()) {
			fileDaoWriteFile.delete();
		}

		/**
		 * 生成Service读接口
		 * 
		 */
		File fileServiceRead = new File(this.ploader.getProperty("apiPath") + File.separator + "src" + File.separator
				+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "alibaba"
				+ File.separator + "api" + File.separator + "service" + File.separator + "reads");
		fileServiceRead.mkdirs();
		File fileServiceReadFile = new File(fileServiceRead.getPath(), clazz.getClassname() + "ReadService.java");
		if (fileServiceReadFile.exists()) {
			fileServiceReadFile.delete();
		}

		/**
		 * 生成ServiceImpl读接口
		 * 
		 */
		File fileServiceReadImpl = new File(this.ploader.getProperty("providerPath") + File.separator + "src"
				+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator
				+ "alibaba" + File.separator + "provider" + File.separator + "service" + File.separator + "reads");
		fileServiceReadImpl.mkdirs();
		File fileServiceReadImplFile = new File(fileServiceReadImpl.getPath(),
				clazz.getClassname() + "ReadServiceImpl.java");
		if (fileServiceReadImplFile.exists()) {
			fileServiceReadImplFile.delete();
		}

		// ----
		/**
		 * 生成Service写接口
		 * 
		 */
		File fileServiceWrite = new File(this.ploader.getProperty("apiPath") + File.separator + "src" + File.separator
				+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "alibaba"
				+ File.separator + "api" + File.separator + "service" + File.separator + "writes");
		fileServiceWrite.mkdirs();
		File fileServiceWriteFile = new File(fileServiceWrite.getPath(), clazz.getClassname() + "WriteService.java");
		if (fileServiceWriteFile.exists()) {
			fileServiceWriteFile.delete();
		}

		/**
		 * 生成ServiceImpl读接口
		 * 
		 */
		File fileServiceWriteImpl = new File(this.ploader.getProperty("providerPath") + File.separator + "src"
				+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator
				+ "alibaba" + File.separator + "provider" + File.separator + "service" + File.separator + "writes");
		fileServiceWriteImpl.mkdirs();
		File fileServiceWriteImplFile = new File(fileServiceWriteImpl.getPath(),
				clazz.getClassname() + "WriteServiceImpl.java");
		if (fileServiceWriteImplFile.exists()) {
			fileServiceWriteImplFile.delete();
		}

		/**
		 * 读mapper文件
		 * 
		 */
		File fileReadMapper = new File(
				this.ploader.getProperty("providerPath") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "mappers" + File.separator + "reads");
		fileReadMapper.mkdirs();
		File fileReadMapperFile = new File(fileReadMapper.getPath(), clazz.getClassname() + "ReadMapper.xml");
		if (fileReadMapperFile.exists()) {
			fileReadMapperFile.delete();
		}

		/**
		 * 写mapper文件
		 * 
		 */
		File fileWriteMapper = new File(
				this.ploader.getProperty("providerPath") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "mappers" + File.separator + "writes");
		fileWriteMapper.mkdirs();
		File fileWriteMapperFile = new File(fileWriteMapper.getPath(), clazz.getClassname() + "WriteMapper.xml");
		if (fileWriteMapperFile.exists()) {
			fileWriteMapperFile.delete();
		}
	}

	/**
	 * 加载类信息：包名，类名，表名等等
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public ClassInfo loadClassInfo() throws UnsupportedEncodingException {
		String author = new String(this.ploader.getProperty("author").trim().getBytes("ISO8859-1"), "UTF-8");
		String objname = new String(this.ploader.getProperty("objname").trim().getBytes("ISO8859-1"), "UTF-8");
		String srcpath = this.ploader.getProperty("srcpath");
		String packagename = this.ploader.getProperty("packagename");
		String tablename = this.ploader.getProperty("tablename");
		String classname = this.ploader.getProperty("classname");
		String tableas = this.ploader.getProperty("tableas");

		String baseEntity = this.ploader.getProperty("baseEntity");
		String entityPackageName = this.ploader.getProperty("entityPackageName");
		String daoReadPackageName = this.ploader.getProperty("daoReadPackageName");
		String daoWritePackageName = this.ploader.getProperty("daoWritePackageName");
		String serviceApiReadPackageName = this.ploader.getProperty("serviceApiReadPackageName");
		String serviceApiWritePackageName = this.ploader.getProperty("serviceApiWritePackageName");
		String serviceImplReadPackageName = this.ploader.getProperty("serviceImplReadPackageName");
		String serviceImplWritePackageName = this.ploader.getProperty("serviceImplWritePackageName");
		String controllerPackageName = this.ploader.getProperty("controllerPackageName");

		ClassInfo clazz = new ClassInfo(author, objname, srcpath, packagename, tablename, tableas, classname,
				baseEntity, entityPackageName, daoReadPackageName, daoWritePackageName, serviceApiReadPackageName,
				serviceApiWritePackageName, serviceImplReadPackageName, serviceImplWritePackageName,
				controllerPackageName);
		return clazz;
	}

	/**
	 * 执行入口
	 */
	public static void main(String[] args) throws Exception {
		AutoCreater autoCreater = new AutoCreater();
		autoCreater.init();
		autoCreater.process(false, // isAddMenu
				false, // jsFlag
				false, // htmlFlag
				false, // controllerFlag
				false, // customerConfigFlag
				false, // providerConfigFlag
				false, // entityFlag
				false, // readServiceFlag
				false, // readServiceImplFlag
				false, // writeServiceFlag
				false, // writeServiceImplFlag
				false, // readDaoFlag
				false, // writeDaoFlag
				true, // readMapperFlag
				true // writeMapperFlag
		);

		// autoCreater.deleteGenFile();
	}

}
