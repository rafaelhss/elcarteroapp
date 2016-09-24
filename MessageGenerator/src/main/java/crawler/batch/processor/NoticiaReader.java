package crawler.batch.processor;

import elcartero.*;
import elcartero.noticia.Noticia;
import elcartero.noticia.NoticiaRepository;
import elcartero.outmessage.OutMessage;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rafa on 06/09/2016.
 */
@Controller
public class NoticiaReader implements ItemReader<OutMessage> {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private ContentChannelRepository contentChannelRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private List<OutMessage> outMessages = null;

    public NoticiaReader(){


    }

    private List<OutMessage> generateOutMessagesList() {
        List<OutMessage> outMessages = new ArrayList<>();

        //Lista de tags de noticias ainda nao enviadas
        List<Noticia> noticias = noticiaRepository.findByDeliveryDateIsNull();
        for(Noticia n : noticias) {
            //String tags = String.join(",", n.getTags());
            List<ContentChannel> contentChannels = contentChannelRepository.findByTagsIn(n.getTags());
            List<Subscription> subscriptions = subscriptionRepository.findByContentChannelIn(contentChannels);

            for(Subscription s : subscriptions) {
                OutMessage outMessage = new OutMessage();
                outMessage.setSubscriber(s.getSubscriber());
                outMessage.setText(n.getTexto());
                outMessage.setTitle(n.getTitulo());
                outMessages.add(outMessage);
            }

            //TODO Se der pau durante o envio ja era. Isso deveria ser setado em outro momento
            n.setDeliveryDate(new Date());
        }
        return outMessages;
    }


    public OutMessage read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(outMessages == null) {
            outMessages = generateOutMessagesList();
        }
        OutMessage outMessage = null;
        try {
            outMessage = outMessages.get(0);
            outMessage.setGenerationDate(new Date());
            outMessages.remove(0);
        } catch (Exception e) {
        }

        return outMessage;
    }


    protected List<String> findTags(List<Noticia> noticias){
        return noticias.stream().
                map(n -> n.getTags()).
                flatMap(l -> l.stream()).
                distinct().collect(Collectors.toList());
    }
}
