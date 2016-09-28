package elcartero;

import elcartero.noticia.Noticia;
import elcartero.noticia.NoticiaQueue;
import elcartero.outmessage.OutMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jca.cci.connection.SingleConnectionFactory;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/fila")
public class FilaController {


    @RequestMapping("noticias/primeira")
    public OutMessage getPrimeiraNoticia() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        Object o = amqpTemplate.receiveAndConvert();
        OutMessage n = (OutMessage) o;
        context.getBean(CachingConnectionFactory.class).destroy();
        return n;
    }




    @RequestMapping(value = "noticias/teste/{q}", method = RequestMethod.GET)
    public void enfileirarNoticia(@PathVariable("q") int q) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        OutMessage teste = new OutMessage();

        teste.setText("teste");

        teste.setGenerationDate(new Date());
        teste.setId(new Random().nextInt(Integer.MAX_VALUE));

        teste.setTitle("teste tit");

        Subscriber subscriber = new Subscriber();
       // subscriber.setDeliveryAddress("556196083232@c.us");
        subscriber.setDeliveryAddress("556181123858@c.us");



        teste.setSubscriber(subscriber);


        for(int i=0; i<q; i++) {
            amqpTemplate.convertAndSend(teste);
        }
        System.out.println("enviado (?)");
        context.getBean(CachingConnectionFactory.class).destroy();

    }


    @RequestMapping(value = "noticias", method = RequestMethod.POST)
    public void enfileirarNoticia(@RequestBody Noticia noticia) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        amqpTemplate.convertAndSend(noticia);
        System.out.println("enviado (?)");
    }


    @RequestMapping("teste/noticias/primeira")
    public String getPrimeiraNoticiaString() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        String n = (String) amqpTemplate.receiveAndConvert();

        return n;
    }


    @RequestMapping(value = "teste/noticias", method = RequestMethod.POST)
    public void enfileirarNoticiaString(@RequestBody String noticia) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        amqpTemplate.convertAndSend(noticia);
        System.out.println("enviado (?)");
    }
}
