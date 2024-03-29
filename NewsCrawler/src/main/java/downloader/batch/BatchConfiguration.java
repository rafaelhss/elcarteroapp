package downloader.batch;

/**
 * Created by rafa on 04/06/2016.
 */

import downloader.batch.hardmob.model.HardMobPromo;
import downloader.batch.hardmob.processor.HardMobProcessor;


import downloader.batch.hardmob.reader.HardMobReader;

import elcartero.noticia.Noticia;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

    @Autowired
    public NoticiaWriter noticiaWriter;

    @Autowired
    public HardMobReader hardMobReader;

    @Bean
    public HardMobProcessor processor() {
        return new HardMobProcessor();
    }

    @Bean
    public ItemWriter<Noticia> writer() {
        return noticiaWriter;
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<HardMobPromo, Noticia>chunk(10)
                .reader(hardMobReader)
                .processor(processor())
                .writer(writer())
                .build();
    }



    // end::jobstep[]
}