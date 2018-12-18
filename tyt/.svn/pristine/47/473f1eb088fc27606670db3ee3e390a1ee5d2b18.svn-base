package ${clazzinfo.packagename}.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import com.guoxin.common.base.BaseController;
import com.guoxin.common.page.PageInfo;
import ${clazzinfo.packagename}.condition.${clazzinfo.classname}Condition;
import ${clazzinfo.packagename}.entity.${clazzinfo.classname};
import ${clazzinfo.packagename}.service.I${clazzinfo.classname}Service;

/**
 * ${clazzinfo.objname} Controller
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
@Controller
@RequestMapping("/${clazzinfo.classnamelc}")
public class ${clazzinfo.classname}Controller extends BaseController{

    private final static Logger logger = Logger.getLogger(${clazzinfo.classname}Controller.class.getName());
	
	@Resource
    private I${clazzinfo.classname}Service ${clazzinfo.classnamelc}Service;
    
    /**
     * 跳转到${clazzinfo.objname}列表首页
     * @author ${clazzinfo.author}
     * @createTime ${clazzinfo.createTime}
     */
    @RequestMapping(value="/list")
    public String get${clazzinfo.classname}List(Model model){
        try{
            ${clazzinfo.classname}Condition condition = new ${clazzinfo.classname}Condition();
            condition.setOrderBys(" A.${clazzinfo.classnamelc}Id DESC ");
            PageInfo pageInfo = this.${clazzinfo.classnamelc}Service.get${clazzinfo.classname}PageList(condition);
            
            model.addAttribute("pageInfo", pageInfo);
            return "/${clazzinfo.classnamelc}/${clazzinfo.classnamelc}-list";
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("显示${clazzinfo.objname}分页列表时:" + e.toString());
            model.addAttribute("msg", "显示${clazzinfo.objname}列表出错!");
            model.addAttribute("url", "${clazzinfo.classnamelc}/list");
            return "error";
        }
    }
    
    /**
     * 请求${clazzinfo.objname}分页列表
     * @author ${clazzinfo.author}
     * @createTime ${clazzinfo.createTime}
     */
    @RequestMapping(value="/listAjax", method=RequestMethod.POST)
    public ModelAndView get${clazzinfo.classname}ListByAjax(@ModelAttribute("condition") ${clazzinfo.classname}Condition condition){
        try{
            condition.setOrderBys(" A.${clazzinfo.classnamelc}Id DESC ");
            PageInfo pageInfo = this.${clazzinfo.classnamelc}Service.get${clazzinfo.classname}PageList(condition);
            String jsonResult = JSON.toJSONStringWithDateFormat(pageInfo, "yyyy-MM-dd HH:mm");
            this.printResponseMsg(jsonResult);
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("Ajax请求${clazzinfo.objname}分页列表时:" + e.toString());
            this.printResponseMsg("{\"errcode\":-1}");
            return null;
        }
    }
    
    /**
     * 准备编辑记录(跳转到编辑页面)
     * @author ${clazzinfo.author}
     * @createTime ${clazzinfo.createTime}
     */
    @RequestMapping(value="/{id}/edit")
    public String preAdd${clazzinfo.classname}(@PathVariable Integer id, Model model){
        if(id != null && id > 0){
            ${clazzinfo.classname} ${clazzinfo.classnamelc} = this.${clazzinfo.classnamelc}Service.get${clazzinfo.classname}ById(id);
            model.addAttribute("${clazzinfo.classnamelc}", ${clazzinfo.classnamelc});
        }
        return "${clazzinfo.classnamelc}/${clazzinfo.classnamelc}-edit";
    }
    
    /**
     * 保存编辑记录By Ajax
     * @author ${clazzinfo.author}
     * @createTime ${clazzinfo.createTime}
     */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public ModelAndView save${clazzinfo.classname}(@ModelAttribute("${clazzinfo.classnamelc}") ${clazzinfo.classname} ${clazzinfo.classnamelc}){
        try{
            if(${clazzinfo.classnamelc}.get${clazzinfo.classname}Id() != null && ${clazzinfo.classnamelc}.get${clazzinfo.classname}Id() > 0){
                int result = this.${clazzinfo.classnamelc}Service.update${clazzinfo.classname}(${clazzinfo.classnamelc});
                if(result > 0){
                    this.printResponseMsg("{\"errcode\":0,\"msg\":\"更新${clazzinfo.objname}成功!\"}");
                }else{
                    this.printResponseMsg("{\"errcode\":1,\"msg\":\"更新${clazzinfo.objname}失败!\"}");
                }
            }else{
                int result = this.${clazzinfo.classnamelc}Service.add${clazzinfo.classname}(${clazzinfo.classnamelc});
                if(result > 0){
                    this.printResponseMsg("{\"errcode\":0,\"msg\":\"添加${clazzinfo.objname}成功!\"}");
                }else{
                    this.printResponseMsg("{\"errcode\":1,\"msg\":\"添加${clazzinfo.objname}失败!\"}");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存编辑的记录时:" + e.toString());
            this.printResponseMsg("{\"errcode\":-1}");
        }
        return null;
    }
    
    /**
     * 删除指定ID的记录By Ajax
     * @author ${clazzinfo.author}
     * @createTime ${clazzinfo.createTime}
     */
    @RequestMapping(value="/{id}/delete")
    public ModelAndView delete${clazzinfo.classname}(@PathVariable Integer id){
        try{
            if(this.${clazzinfo.classnamelc}Service.delete${clazzinfo.classname}ById(id) > 0){
                this.printResponseMsg("{\"errcode\":0,\"msg\":\"删除${clazzinfo.objname}成功!\"}");
            }else{
                this.printResponseMsg("{\"errcode\":1,\"msg\":\"删除${clazzinfo.objname}失败!\"}");
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存编辑的记录时:" + e.toString());
            this.printResponseMsg("{\"errcode\":-1}");
        }
        return null;
    }
    
    /**
     * 获取指定ID的${clazzinfo.objname}By Ajax
     * @author ${clazzinfo.author}
     * @createTime ${clazzinfo.createTime}
     */
    @RequestMapping(value="/{id}/show")
    public ModelAndView show${clazzinfo.classname}(@PathVariable Integer id){
        try{
            ${clazzinfo.classname} ${clazzinfo.classnamelc} = this.${clazzinfo.classnamelc}Service.get${clazzinfo.classname}ById(id);
            String jsonResult = JSON.toJSONStringWithDateFormat(${clazzinfo.classnamelc}, "yyyy-MM-dd HH:mm");
            this.printResponseMsg(jsonResult);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存编辑的记录时:" + e.toString());
            this.printResponseMsg("{\"errcode\":-1}");
        }
        return null;
    }

}
