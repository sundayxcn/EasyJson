package easy.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author apple
 * @decrption
 * @data 2019-09-09
 **/
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Adapter {
    String[] value() ;
}
