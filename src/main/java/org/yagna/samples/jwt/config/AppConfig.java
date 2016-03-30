package org.yagna.samples.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by asish on 3/30/16.
 */
@Configuration
@Import({SecurityConfig.class})
public class AppConfig {
}
