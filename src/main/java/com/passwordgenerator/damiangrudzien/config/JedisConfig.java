package com.passwordgenerator.damiangrudzien.config;


public class JedisConfig {
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory jedisConFactory
//				= new JedisConnectionFactory();
//		jedisConFactory.setHostName("red-ci05u7d269v5qbl54s5g");
//		jedisConFactory.setPort(6379);
//		return jedisConFactory;
//	}

//	@Value("${spring.redis.host}")
//	private String host;
//
//	@Value("${spring.redis.port}")
//	private int port;
//
//	private final int TIMEOUT = 60000;
//
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName(host);
//		redisStandaloneConfiguration.setPort(port);
//		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//		jedisClientConfiguration.connectTimeout(Duration.ofMillis(TIMEOUT));
//		jedisClientConfiguration.usePooling();
//		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
//	}

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		return template;
//	}

}
