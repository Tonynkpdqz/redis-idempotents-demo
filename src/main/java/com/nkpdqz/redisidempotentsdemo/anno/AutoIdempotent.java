package com.nkpdqz.redisidempotentsdemo.anno;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {
}
