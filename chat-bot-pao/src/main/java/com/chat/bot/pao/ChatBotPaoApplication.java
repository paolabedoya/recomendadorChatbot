package com.chat.bot.pao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.chat.bot.pao.model.PalabrasClave;
import com.chat.bot.pao.repository.PalabraClaveRepository;

@Configuration
@SpringBootApplication
public class ChatBotPaoApplication implements WebMvcConfigurer {

	@Autowired 
	private PalabraClaveRepository palabraClaveRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ChatBotPaoApplication.class, args);
	}
	
	@Bean(name = "PalabrasClaves")
	public List<PalabrasClave> listPalabrasClave(){
		return (List<PalabrasClave>) palabraClaveRepository.findAll();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/templates/**")) {
			registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/resources/templates/");
		}
	}

	/**
	 * <p>
	 * Bean encargado de la instanciación de la view index de la aplicación.
	 * </p>
	 *
	 * @return the thymeleaf view
	 */
	@Bean(name = "content-part")
	@Scope("prototype")
	public ThymeleafView someViewBean() {
		ThymeleafView view = new ThymeleafView("index");
		view.setMarkupSelector("content");
		return view;
	}

	/**
	 * <p>
	 * Bean encargado de resolver la inicialización de los textos y los caracteres
	 * soportados para el mismó.
	 * </p>
	 *
	 * @return the class loader template resolver
	 */
	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(Integer.valueOf(1));
		return templateResolver;
	}

	/**
	 * <p>
	 * Bean encargado de la definición de la ingenieria del motor de plantillas.
	 * </p>
	 *
	 * @return the spring template engine
	 */
	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(templateResolver());
//		templateEngine.addTemplateResolver(textTemplateResolver());
		return templateEngine;
	}

	/**
	 * <p>
	 * Bean encargado de instanciar la ingenieria aplicada al motor de plantillas y
	 * su codificación de caracteres.
	 * </p>
	 *
	 * @return the view resolver
	 */
	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

	/**
	 * <p>
	 * Bean que inicializa los objetos de tipo Date o Calendar para poder ser
	 * manipulados en las plantillas Thymeleaf.
	 * </p>
	 *
	 * @return the java 8 time dialect
	 */
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
}
