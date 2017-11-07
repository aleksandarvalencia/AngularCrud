
package com.projects.angularcrud.config;


import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.projects.angularcrud.config", "com.projects.angularcrud.controller", "com.projects.angularcrud.service",
		"com.projects.angularcrud.exceptions", "com.projects.angularcrud.validation" })
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ServletContext servletContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
            registry.addResourceHandler("/include/**").addResourceLocations("/include/").setCachePeriod(31556926);
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(createXmlHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(268435456);
    return new CommonsMultipartResolver();
    }



    @Bean
    public InternalResourceViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setViewClass(JstlView.class);
            viewResolver.setOrder(2);
            viewResolver.setPrefix("/WEB-INF/jsp/");
            viewResolver.setSuffix(".jsp");
            return viewResolver;
    }
    @Bean
    public ViewResolver  getXmlViewResolver() {

            XmlViewResolver viewResolver = new XmlViewResolver();
            //viewResolver.setLocation(new ClassPathResource("/WEB-INF/views.xml"));
            viewResolver.setLocation(new ServletContextResource(servletContext,"/WEB-INF/views.xml"));
            viewResolver.setOrder(1);

            return viewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
            ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
            rb.setBasenames(new String[] { "messages", "validation","sqlconfig"});
            return rb;
    }
    
  private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter = 
          new MarshallingHttpMessageConverter();
 
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
 
        return xmlConverter;
    }
    /*protected Filter[] getServletFilters() {
    	Filter [] singleton = { new CORSFilter()};
    	return singleton;
    }*/
    /*@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}*/


}


