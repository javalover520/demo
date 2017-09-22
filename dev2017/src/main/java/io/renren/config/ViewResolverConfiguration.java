package io.renren.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;

@Configuration
public class ViewResolverConfiguration {
	@Bean
	public InternalResourceViewResolver getJspViewResolver() {
		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
		jspViewResolver.setPrefix("");
		jspViewResolver.setSuffix("");
		jspViewResolver.setViewClass(JstlView.class);
		// 通过ViewNames属性来实现，通过请求中返回的视图名称匹配其采用哪个对应的视图解析器处理，从而找到对应prefix下的页面
		jspViewResolver.setViewNames("*.jsp");
		jspViewResolver.setOrder(Ordered.LOWEST_PRECEDENCE);
		// 开发时不启用缓存，改动即可生效
		jspViewResolver.setCache(false);
		return jspViewResolver;
	}
	
	@Bean
	public CommonsMultipartResolver getCommonsMultipartResolver(){
		CommonsMultipartResolver multi=new CommonsMultipartResolver();
		multi.setMaxUploadSize(1000000000);
		return multi;
	}
}
