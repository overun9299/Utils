package overun.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BeanUtil
 * @Description: BeanUtil通过实例名获取实例
 * @author: 壹米滴答-西安-ZhangPY
 * @version: V1.0
 * @date: 2019/9/9 10:18
 * @Copyright: 2019 www.yimidida.com Inc. All rights reserved.
 */
@Component
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

}
