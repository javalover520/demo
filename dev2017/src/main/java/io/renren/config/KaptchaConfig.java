package io.renren.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class KaptchaConfig {

	@Bean
	public DefaultKaptcha getKaptcha() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties p = new Properties();
		p.put("kaptcha.border", "no");
		p.put("kaptcha.textproducer.font.color", "black");
		p.put("kaptcha.textproducer.char.space", "5");
		Config config = new Config(p);
		kaptcha.setConfig(config);
		return kaptcha;
	}
}
