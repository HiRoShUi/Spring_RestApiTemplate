package org.MaKro.Spring.RestApi.General;

import java.lang.reflect.Type;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import classes.ioc.IocContainer;
import classes.ioc.MappingKey;
import classes.sql.IConnector;
import classes.sql.MySqlConnector;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{

	public static void main(String[] args) {
		//IoC still in progress...
//		IocContainer<MappingKey,String> container = new IocContainer();
//		Function<MySqlConnector,String> func = { MySqlConnector e { e = new MySqlConnector(""); return e.getClass().getName();};
//		container.Register(IConnector.class,func, MySqlConnector.class.getName());
		SpringApplication.run(Application.class, args);
	}
	
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	     return application.sources(Application.class);
	  }

}

