package com.bquan.controller.pc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bquan.util.JsonUtil;

/**
 * pc版本控制接口
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/pc/version")
public class PcVersionController extends BasePcController {

	/**
	 * 版本更新接口
	 * 
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletResponse response) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("status", "success");
		responseMap.put("msg", "成功");
		responseMap.put("versionNumber", isupdate());// 新版本的版本号
		responseMap.put("update", "0");// 0不强制升级，1强制升级
		responseMap.put("updateurl", "https://www.baidu.com");// 新版本地址
		return output(response, JsonUtil.toJson(responseMap));
	}

	public String isupdate() throws Exception {
		String versionUrl = "http://ydvpn.9ni.com:8080/tyt/download/ver.txt";
		URL url = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader netVer = null;
		// 读取网络上的版本号
	    url = new URL(versionUrl);
		is = url.openStream();
	    isr = new InputStreamReader(is);
		netVer = new BufferedReader(isr);
		String netVerStr = netVer.readLine();
		return netVerStr;
	}

}
