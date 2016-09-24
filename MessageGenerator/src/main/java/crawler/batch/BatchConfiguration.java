package crawler.batch;

/**
 * Created by rafa on 04/06/2016.
 */

import crawler.batch.processor.OutMessageProcessor;

import crawler.batch.processor.NoticiaReader;
import elcartero.outmessage.OutMessage;
import elcartero.noticia.Noticia;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    //@Autowired
   // public DataSource dataSource;

    @Autowired
    public OutMessageWriter outMessageWriter;

    @Autowired
    private NoticiaReader noticiaReader;


    // tag::readerwriterprocessor[]
/*    @Bean
    public ItemReader<Noticia> reader() {
        FakeCrawler reader = new FakeCrawler();
        return reader;
    }
*/
    @Bean
    public OutMessageProcessor processor() {
        return new OutMessageProcessor();
    }

    @Bean
    public ItemWriter<OutMessage> writer() {
        return outMessageWriter;
    }
    // end::readerwriterprocessor[]

    // tag::listener[]

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }

    // end::listener[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                //.next(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1 - globo")
                .<OutMessage, OutMessage>chunk(10)
                .reader(noticiaReader)
                .processor(processor())
                .writer(writer())
                .build();
    }
/*
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2 - fake")
                .<Noticia, Noticia>chunk(10)
                .reader(new FakeCrawler())
                .processor(processor())
                .writer(writer())
                .build();
    }
*/
    // end::jobstep[]
}