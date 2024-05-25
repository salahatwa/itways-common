package com.api.common.annotations;

import java.lang.annotation.*;

/**
 * Cache parameter annotation.
 *
 * @author ssatwa
 * @date 3/28/19
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

}
