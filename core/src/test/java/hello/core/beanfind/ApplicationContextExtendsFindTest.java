package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPoliy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext ( TestConfig.class );

    @Test
    @DisplayName ( "부모타입으로 조회, 자식이 둘 이상 있으면, 중복오류 발생" )
    void findBeanByParentTypeDuplicate(){
        assertThrows ( NoUniqueBeanDefinitionException.class , () -> ac.getBean ( DiscountPolicy.class ) );
    }

    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy () {
            return new RateDiscountPoliy ();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy () {
            return new FixDiscountPolicy ();
        }

    }

}
