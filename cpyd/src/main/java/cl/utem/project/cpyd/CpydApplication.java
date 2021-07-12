package cl.utem.project.cpyd;

import cl.utem.project.cpyd.api.rest.filter.SimpleCorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CpydApplication {

    @Bean
    public FilterRegistrationBean<SimpleCorsFilter> simpleCorsFilter() {
        FilterRegistrationBean<SimpleCorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleCorsFilter());
        registrationBean.addUrlPatterns("/v1/");
        return registrationBean;
    }
    
    public static void main(String[] args) {
    	SpringApplication.run(CpydApplication.class, args);
    }

}
