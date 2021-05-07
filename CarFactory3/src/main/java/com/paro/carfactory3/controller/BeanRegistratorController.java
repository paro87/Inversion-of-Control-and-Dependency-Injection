package com.paro.carfactory3.controller;

import com.paro.carfactory3.model.BeanMD;
import com.paro.carfactory3.service.CCL;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@RestController
public class BeanRegistratorController {
    @Autowired
    private GenericApplicationContext context;

    @Autowired
    private CCL ccl;

    @PostMapping("/regbean")
    @SneakyThrows
    public String registerNewSingleton(@RequestBody BeanMD beanMD){
        Class beanClass = ccl.findClass(beanMD.getBeanClassName());
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) context.getBeanFactory();

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setScope(SCOPE_SINGLETON);
        beanDefinition.setBeanClass(beanClass);
        beanFactory.registerBeanDefinition(beanMD.getBeanName(),beanDefinition);
        context.getBean(beanMD.getBeanName());

        return "registered";
    }
}
