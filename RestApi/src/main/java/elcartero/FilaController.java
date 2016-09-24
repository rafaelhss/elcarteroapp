package elcartero;

import elcartero.noticia.Noticia;
import elcartero.noticia.NoticiaQueue;
import elcartero.outmessage.OutMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@RestController
@RequestMapping("/fila")
public class FilaController {

    @RequestMapping("noticias/primeira")
    public OutMessage getPrimeiraNoticia() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        Object o = amqpTemplate.receiveAndConvert();
        OutMessage n = (OutMessage) o;

        return n;
    }




    @RequestMapping(value = "noticias/teste", method = RequestMethod.GET)
    public void enfileirarNoticia() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        OutMessage teste = new OutMessage();

        teste.setText("teste");

        teste.setGenerationDate(new Date());
        teste.setId(154);

        teste.setTitle("teste tit");



        amqpTemplate.convertAndSend(teste);
        System.out.println("enviado (?)");
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
