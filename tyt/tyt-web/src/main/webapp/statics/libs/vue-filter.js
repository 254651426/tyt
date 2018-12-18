Vue.filter('time',
//<!-- value 格式为13位unix时间戳 -->
//<!-- 10位unix时间戳可通过value*1000转换为13位格式 -->
function(value) {
	try{
		var date = new Date(value);
	    Y = date.getFullYear(),
	    m = date.getMonth() + 1,
	    d = date.getDate(),
	    H = date.getHours(),
	    i = date.getMinutes(),
	    s = date.getSeconds();
	    if (m < 10) {
	        m = '0' + m;
	    }
	    if (d < 10) {
	        d = '0' + d;
	    }
	    if (H < 10) {
	        H = '0' + H;
	    }
	    if (i < 10) {
	        i = '0' + i;
	    }
	    if (s < 10) {
	        s = '0' + s;
	    }
	    //<!-- 获取时间格式 2017-01-03 10:13:48 -->
	    var t = Y+'-'+m+'-'+d+' '+H+':'+i+':'+s;
	    return t;
	}catch(e){
		return '';
	}
});

Vue.filter('date',
//<!-- value 格式为13位unix时间戳 -->
//<!-- 10位unix时间戳可通过value*1000转换为13位格式 -->
function(value) {
	try{
	    var date = new Date(value);
	    Y = date.getFullYear(),
	    m = date.getMonth() + 1,
	    d = date.getDate(),
	    H = date.getHours(),
	    i = date.getMinutes(),
	    s = date.getSeconds();
	    if (m < 10) {
	        m = '0' + m;
	    }
	    if (d < 10) {
	        d = '0' + d;
	    }
	    if (H < 10) {
	        H = '0' + H;
	    }
	    if (i < 10) {
	        i = '0' + i;
	    }
	    if (s < 10) {
	        s = '0' + s;
	    }
	    //<!-- 获取时间格式 2017-01-03 -->
	    var t = Y + '-' + m + '-' + d;
	    return t;
	}catch(e){
		return '';
	}
});