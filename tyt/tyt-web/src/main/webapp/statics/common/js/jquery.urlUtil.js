/* 获取url传入的参数 */

// 参数： name --- url参数名称
(function($){
$.getUrlParam = function(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
}
})(jQuery);