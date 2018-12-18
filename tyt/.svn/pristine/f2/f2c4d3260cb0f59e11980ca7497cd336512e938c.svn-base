${r'$'}(document).ready(function(){
    //语法验证
    $.formValidator.initConfig({
        formid:"edit${clazzinfo.classname}",
        alertmessage:false,
        onerror:function(msg,obj,errorlist){
            var msgArr = [];
            ${r'$'}.map(errorlist,function(msg){
                msgArr.push(msg)
            });
            DilogValid('<span>' + msgArr.join('<br/>') + '</span>');
        },
        onsuccess:function(){
            save${clazzinfo.classname}();
            return false;
        }
    });
    
    //${r'$'}("${r'#'}txtName").formValidator({automodify:false}).inputValidator({min:1,onerror:"名称必须输入!"});
    //${r'$'}("${r'#'}txtNum").formValidator({automodify:false,empty:true}).regexValidator({ regexp: "num1", datatype:"enum", onerror: "必须是正数!" });
    
});

/**
 * 显示${clazzinfo.objname}列表当前页
 * @author ${clazzinfo.author}
 * @createtime ${clazzinfo.createTime}
 */
function show${clazzinfo.classname}NowPageList(){
    if(${r'$'}('${r'#'}pg${clazzinfo.classname}_nowPg').length > 0){
        show${clazzinfo.classname}PageList(parseInt(${r'$'}('${r'#'}pg${clazzinfo.classname}_nowPg').text(),10));
    }else{
        show${clazzinfo.classname}PageList(1);
    }
}

/**
 * ${clazzinfo.objname}分页回调
 * @author ${clazzinfo.author}
 * @createtime ${clazzinfo.createTime}
 */
function ${clazzinfo.classnamelc}PageselectCallback(page_index, jq){
    show${clazzinfo.classname}PageList(page_index+1);
}

/**
 * ${clazzinfo.objname}分页
 * @author ${clazzinfo.author}
 * @createtime ${clazzinfo.createTime}
 */
function show${clazzinfo.classname}PageList(pgNum){
    var paraObj = {};
    paraObj["pageNum"] = pgNum;
    if(${r'$'}.trim(${r'$'}('${r'#'}txtParam').val()) != ''){
        paraObj["${clazzinfo.classnamelc}.${clazzinfo.classnamelc}Name"] = ${r'$'}.trim(${r'$'}('${r'#'}txtParam').val());
    }
    var loading = ShowLoading();
    $.ajax({
        url: "${clazzinfo.classnamelc}/listAjax",
        type: "post",           // 数据发送方式          
        dataType : "json",      // 接受数据格式
        data : paraObj,
        success : function(jsonData){
            HideLoading(loading);
            if(jsonData.errcode == -2){
                top.location.reload(true);
            }else if(jsonData.errcode == -1){
                DilogError('出错了!请联系管理员!');
            }else{
                var listHtml = [];
                ${r'$'}.each(jsonData.items, function(i,item){
                    listHtml.push('<tr>');
                    <#list attributes as item>
                    listHtml.push('<td>'+ (item.${item.name}==undefined?'':item.${item.name}) + '</td>');
                    </#list>
                    listHtml.push('<td><a class="btn btn-xs btn-link" href="javascript:show${clazzinfo.classname}('+ item.${clazzinfo.classnamelc}Id +');">修改</a>');
                    listHtml.push('<a class="btn btn-xs btn-link" href="javascript:delete${clazzinfo.classname}('+ item.${clazzinfo.classnamelc}Id +');">删除</a></td>');
                    listHtml.push('</tr>');
                });
                
                ${r'$'}('${r'#'}tb${clazzinfo.classname}List').html(listHtml.join(''));
                ${r'$'}('${r'#'}pg${clazzinfo.classname}').empty();
                create${clazzinfo.classname}Pagination(jsonData.totalPages, jsonData.totalRows, jsonData.pageNum, jsonData.pageSize);
            }
        },
        error: function(e){
            HideLoading(loading); 
        }
    });
}

/**
 * 构建${clazzinfo.objname}分页组件
 * @author ${clazzinfo.author}
 * @createtime ${clazzinfo.createTime}
 */
function create${clazzinfo.classname}Pagination(totalPages, totalRows, pageNum, pageSize){
    if(totalPages > 1){
        ${r'$'}("${r'#'}pg${clazzinfo.classname}").pagination(totalRows, {
            callback: ${clazzinfo.classnamelc}PageselectCallback,
            current_page:pageNum-1,
            num_edge_entries:1,
            num_display_entries:5,
            items_per_page:pageSize,
            show_if_single_page:true,
            show_ext:true
        });
    }
}

/**
 * 删除${clazzinfo.objname}
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
function delete${clazzinfo.classname}(objId){
    DilogConfirm('您确定要删除此${clazzinfo.objname}吗？', function(){
        ${r'$'}.ajax({
            url: "${clazzinfo.classnamelc}/"+ objId +"/delete",
            type: "get",           // 数据发送方式          
            dataType : "json",      // 接受数据格式
            success : function(jsonData){
                if(jsonData.errcode == -2){
                    top.location.reload(true);
                }else if(jsonData.errcode == -1){
                    DilogError('出错了!请联系管理员!');
                }else{
                    if(jsonData.errcode == 0){
                        show${clazzinfo.classname}NowPageList();
                    }else{
                        DilogError(jsonData.msg);
                    }
                }
            },
            error: function(e){
            }
        });
    });
}

/**
 * 查看${clazzinfo.objname}
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
function show${clazzinfo.classname}(objId){
    clearnForm();
    ${r'$'}.ajax({
        url: "${clazzinfo.classnamelc}/"+ objId +"/show",
        type: "get",           // 数据发送方式          
        dataType : "json",      // 接受数据格式
        success : function(jsonData){
            if(jsonData.errcode == -2){
                top.location.reload(true);
            }else if(jsonData.errcode == -1){
                DilogError('出错了!请联系管理员!');
            }else{
                <#list attributes as item>
                ${r'$'}('${r'#'}t${item.name}').val(jsonData.${item.name});
                </#list>
                
                DialogCustomForm('编辑${clazzinfo.objname}','div${clazzinfo.classname}Form','${clazzinfo.classnamelc}FormDig');
                show${clazzinfo.classname}NowPageList();
            }
        },
        error: function(e){
        }
    });
}

/**
 * 清空编辑表单
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
function clearnForm(){
    <#list attributes as item>
    ${r'$'}('${r'#'}t${item.name}').val('');
    </#list>
}

/**
 * 保存编辑${clazzinfo.objname}
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
function save${clazzinfo.classname}(){
    var msgArr = [];
    var paraObj = {};
    <#list attributes as item>
    if(${r'$'}.trim($('${r'#'}t${item.name}').val()) != ''){
        paraObj["${item.name}"] = ${r'$'}.trim($('${r'#'}t${item.name}').val());
    }else{
        msgArr.push('${item.desc}不能为空!');
    }
    </#list>
    
    if(msgArr.length > 0){
        DilogWarning(msgArr.join('<br/>'));
        return ;
    }
    ${r'$'}.ajax({
        url: "${clazzinfo.classnamelc}/save",
        type: "post",           // 数据发送方式          
        dataType : "json",      // 接受数据格式
        data : paraObj,
        success : function(jsonData){
            if(jsonData.errcode == -2){
                top.location.reload(true);
            }else if(jsonData.errcode == -1){
                DilogError('出错了!请联系管理员!');
            }else{
                if(jsonData.errcode == 0){
                    DialogClose('${clazzinfo.classnamelc}FormDig');
                    if($.trim($('${r'#'}hdn${clazzinfo.classname}Id').val()) != ''){
                        show${clazzinfo.classname}NowPageList();
                    }else{
                        show${clazzinfo.classname}PageList(1);
                    }
                }else{
                    DilogError(jsonData.msg);
                }
            }
        },
        error: function(e){
        }
    });
}

/**
 * 清空数据并打开添加表单
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
function showAdd${clazzinfo.classname}Dig(){
    clearEditForm();
    DialogCustomForm('新增${clazzinfo.objname}','div${clazzinfo.classname}Form','${clazzinfo.classnamelc}FormDig');
}
