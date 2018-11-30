//package org.yakimov.denis.countriesservice.configuration;
//
//import com.mongodb.MongoClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.authentication.UserCredentials;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//
//@Configuration
//public class MongoConfig {
//
//    @Value("${spring.data.mongodb.database}")
//    private String dbName;
//
//    @Value("${spring.data.mongodb.host}")
//    private String host;
//
//    @Value("${spring.data.mongodb.port}")
//    private String port;
//
//    @Bean
//    public MongoClient mongo() {
//        return new MongoClient(host, Integer.parseInt(port));
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        UserCredentials userCredentials = new UserCredentials(this.userName, this.password);
//        return new MongoTemplate(mongo(), dbName, userCredentials);
//    }
//}
