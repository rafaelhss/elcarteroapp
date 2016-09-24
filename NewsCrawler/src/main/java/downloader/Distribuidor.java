package downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"downloader"})
@Configuration
@EnableJpaRepositories(basePackages="elcartero")
@EntityScan(basePackages="elcartero")
public class Distribuidor
{
    public static void main(String[] args)
    {
        SpringApplication.run(Distribuidor.class);
    }
}