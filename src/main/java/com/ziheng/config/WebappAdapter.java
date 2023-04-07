package com.ziheng.config;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
// 后端跨域配置 注册拦截器

@Configuration
public class WebappAdapter implements WebMvcConfigurer {

	//注册拦截器

	// 跨域请求问题有很多解决方案，但是项目都有细微差距，网上找的解决办法看似一致但不通用
	// 将allowingOrigins换成allowedOriginPatterns即可。
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				// .allowedOrigins("*") //放行哪些原始域（这种放行会报错）
				.allowedOriginPatterns("*")
				// 是否发送Cookie信息
				.allowCredentials(true)
				// 放行哪些原始域(请求方式) 可以是* 任意
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
				// 放行哪些原始域的头部信息
				.allowedHeaders("*")
				// 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//				.exposedHeaders("Header1", "Header2")
				.exposedHeaders("*")
				// cookie最大存活时间
				.maxAge(3600);

	}


}
