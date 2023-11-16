package com.passwordgenerator.damiangrudzien.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class JedisConfig {
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory jedisConFactory
//				= new JedisConnectionFactory();
//		jedisConFactory.setHostName("red-ci05u7d269v5qbl54s5g");
//		jedisConFactory.setPort(6379);
//		return jedisConFactory;
//	}

	@Value("${spring.redis.host}")
	private String host;
//	@Value("${spring.redis.password}")
//	private String password;
//	@Value("${spring.redis.username}")
//	private String username;

	@Value("${spring.redis.port}")
	private int port;

	private final int TIMEOUT = 60000;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
//		redisStandaloneConfiguration.setUsername(username);
//		redisStandaloneConfiguration.setPassword(password);
//		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//		jedisClientConfiguration.connectTimeout(Duration.ofMillis(TIMEOUT));
//		jedisClientConfiguration.
//		jedisClientConfiguration.usePooling();
		return new JedisConnectionFactory(redisStandaloneConfiguration);

//		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
	}

	@Bean
	public RedisTemplate<String, Object> template() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}

}
