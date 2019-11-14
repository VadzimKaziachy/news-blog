package news.blog.com;


import com.tabasoft.converter.configuration.ExtendedConversionServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ExtendedConversionServiceConfig.class)
public class Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
