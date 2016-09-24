package crawler.batch;

import elcartero.outmessage.OutMessage;
import elcartero.noticia.NoticiaRepository;
import elcartero.outmessage.OutMessageRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import rabbitmq.RabbitConfiguration;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Repository
public class OutMessageWriter implements ItemWriter<OutMessage> {
    @Autowired
    OutMessageRepository outMessageRepository;

    @Override
    public void write(List<? extends OutMessage> list) throws Exception {



        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);

        list.stream().forEach(noticia -> amqpTemplate.convertAndSend(noticia));

        //TODO quando esse codigo roda o batch nunca abaca.... :-((

        System.out.println("enviado (?)");
        outMessageRepository.save(list);
        System.out.println("salvo");

    }
}
