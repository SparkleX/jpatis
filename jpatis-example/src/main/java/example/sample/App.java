package example.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.next.jpatis.spring.JpatisConfig;
import com.next.jpatis.spring.JpatisRepositoryImpl;

import example.domain.User;
import example.repository.UserRepository;

@SpringBootApplication
@Import(JpatisConfig.class)
@EntityScan(basePackageClasses=User.class)
@EnableJpaRepositories(repositoryBaseClass = JpatisRepositoryImpl.class, basePackageClasses=UserRepository.class)
public class App {
	
	@Autowired
	ServiceExample service;

	public static void main(String[] args) {
		ConfigurableApplicationContext appCtx = SpringApplication.run(App.class, args);
		appCtx.getBean(App.class).run();
	}
	private void run() {
		service.repositoryExample();
		service.entityManagerExample();
		service.sqlConnectionExample();		
	}
}
