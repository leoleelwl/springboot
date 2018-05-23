package springboot.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


@Configuration
@EnableWebMvc
@ComponentScan("com.jhy.springboot")
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

	
	/**
	 * 配置试图解析器freemarker
	 * @return
	 */
	@Bean
	public FreeMarkerViewResolver getFreeMarkerViewResolver(){
		FreeMarkerViewResolver view = new FreeMarkerViewResolver();
		view.setOrder(1);
		view.setSuffix(".html");
		view.setCache(false);
		view.setRequestContextAttribute("request");
		view.setContentType("text/html;charset=utf-8");
		view.setViewClass(FreeMarkerView.class);
		return view;
	}
	
	/**
	 * 配置fastJson解析器
	 * @return
	 */
	@Bean
	public FastJsonHttpMessageConverter  fastJsonHttpMessageConverters(){
		FastJsonHttpMessageConverter fastConverter  = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
	       fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames);
	       fastJsonConfig.setCharset(Charset.forName("UTF-8"));
	       fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
	       List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
	       supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
	       fastConverter.setSupportedMediaTypes(supportedMediaTypes);
	       fastConverter.setFastJsonConfig(fastJsonConfig);
	       return fastConverter;
	}
	
	
	/**
     * 配置FreeMarker
     * @return
     */
    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "5");
        settings.setProperty("url_escaping_charset", "UTF-8");
        settings.setProperty("defaultEncoding", "UTF-8");
        settings.setProperty("whitespace_stripping", "true");
        settings.setProperty("boolean_format", "true,false");
        settings.setProperty("number_format", "0.##########");
        settings.setProperty("locale", "zh_CN");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("date_format", "yyyy-MM-dd");
        settings.setProperty("time_format", "HH:mm:ss");
        settings.setProperty("tag_syntax", "square_bracket");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        settings.setProperty("auto_import", "/spring.ftl as spring, /common/spring.ftl as spring");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        
        // 配置自定义指令
//        Map<String,Object> variables = new HashMap<String,Object>();
//        variables.put("role", new RoleDirectiveModel());
//        freeMarkerConfigurer.setFreemarkerVariables(variables);
        
        return freeMarkerConfigurer;
    }
	
	//静态资源
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
	 /**
     * 配置JSON解析器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(this.fastJsonHttpMessageConverters());
    }
	
}
