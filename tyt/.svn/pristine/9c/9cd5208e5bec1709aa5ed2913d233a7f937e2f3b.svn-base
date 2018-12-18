package com.bquan.resolver;

import com.bquan.annotation.LoginUser;
import com.bquan.entity.mysql.UserEntity;
import com.bquan.interceptor.AuthorizationInterceptor;
import com.bquan.service.read.TbUserReadService;
import com.bquan.service.write.TbUserWriteService;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 22:02
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    
	private TbUserReadService userReadService;
	private TbUserWriteService userWriteService;

    public TbUserReadService getUserReadService() {
		return userReadService;
	}

	public void setUserReadService(TbUserReadService userReadService) {
		this.userReadService = userReadService;
	}

	public TbUserWriteService getUserWriteService() {
		return userWriteService;
	}

	public void setUserWriteService(TbUserWriteService userWriteService) {
		this.userWriteService = userWriteService;
	}

	@Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }

        //获取用户信息
        UserEntity user = userReadService.queryObject((Long)object);

        return user;
    }
}
