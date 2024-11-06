package com.mini.erp.controllers.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.mini.erp.product", "com.mini.erp.user"})
public class ControllerConfig {

//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @PostConstruct
//    public void inspectBeans() {
//        String[] beanNames = applicationContext.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println(beanName + " : " + applicationContext.getBean(beanName).getClass().toString());
//        }
//    }

}
