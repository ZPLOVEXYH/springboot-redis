package com.sample.spring.boot.redis.proxy.dynamic;

import com.sample.spring.boot.redis.proxy.IUserServ;
import com.sample.spring.boot.redis.proxy.IUserServImpl;

public class DynamicProxyMain {

    public static void main(String[] args) {
        DynamicProxyImpl dynamicProxy = new DynamicProxyImpl();
        IUserServ iUserServ = (IUserServ) dynamicProxy.createProxy(new IUserServImpl());
        iUserServ.findAllUser();

//        Resource resource = new ClassPathResource("spring-bean.xml");
//        Resource resource = new FileSystemResource("C:\\idea_project\\springboot-redis\\src\\main\\resources\\spring-bean.xml");
//        BeanFactory beanFactory = new XmlBeanFactory(resource);
//        String[] strs = ((XmlBeanFactory) beanFactory).getBeanDefinitionNames();
//        for (String s : strs) {
//            System.out.println("s：" + s);
//        }
    }
}
