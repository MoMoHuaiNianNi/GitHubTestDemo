package com.tedu.sp11.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tedu.web.util.JsonResult;

@Component
public class AccessFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// 对指定的serviceid过滤，如果要过滤所有服务，直接返回 true
		RequestContext context = RequestContext.getCurrentContext();
		String serverId = (String) context.get(FilterConstants.SERVICE_ID_KEY);
		if (serverId.equals("item-service")) {
			return true;
		}
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String token = request.getParameter("token");
		if (token == null) {
			// 此设置会阻止请求被路由到后台微服务
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(200);
			context.setResponseBody(JsonResult.err().code(JsonResult.NOT_LOGIN).toString());
		}
		// zuul过滤器返回的数据设计为以后扩展使用，
		// 目前该返回值没有被使用
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER;
	}

}
