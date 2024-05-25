package com.api.common.annotations;
//package com.blinked.annotations;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import org.springframework.core.annotation.AliasFor;
//
//import com.blinked.modules.core.model.enums.Mode;
//
///**
// * This annotation can restrict access under certain conditions api
// *
// * @author ssatwa
// * @date 2020-02-14 13:48
// */
//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface DisableOnCondition {
//    @AliasFor("mode")
//    Mode value() default Mode.DEMO;
//
//    @AliasFor("value")
//    Mode mode() default Mode.DEMO;
//}
