package crawler.batch;

import java.util.List;

import elcartero.noticia.Noticia;
import elcartero.noticia.NoticiaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);



    @Autowired
    private NoticiaRepository noticiaRepository;


    @Autowired
    public JobCompletionNotificationListener() {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
       /* System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            List<Noticia> results = (List<Noticia>) noticiaRepository.findAll();
            System.out.println("tabammnhi: " + results.size());
            for (Noticia person : results) {
                System.out.println("Found <" + person.getTitulo() + "> in the database.");
            }

        }


        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
        System.out.println("############################");
*/
    }
}