package com.ddc.server.shiro;

import com.ddc.server.annotation.CurrentUser;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.exception.UnauthorizedException;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *  增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @author dingpengfei
 * @since 2019-05-09
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(DDCAdmin.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        SimplePrincipalCollection collection = (SimplePrincipalCollection) webRequest.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY", RequestAttributes.SCOPE_SESSION);
        if (collection == null) {
            throw new UnauthorizedException("获取用户信息失败");
        }
        DDCAdmin admin = (DDCAdmin) collection.getPrimaryPrincipal();
        if (admin == null) {
            throw new UnauthorizedException("获取用户信息失败");
        }
        return admin;
    }
}
