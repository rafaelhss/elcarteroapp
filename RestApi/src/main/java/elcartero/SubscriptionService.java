package elcartero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by rafa on 06/09/2016.
 */
@Service
public class SubscriptionService {

    @Autowired
    private ContentChannelRepository contentChannelRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<Subscription> listSubscriptionsByDeliveryAddress(String deliveryAddress){
        Subscriber subscriber = subscriberRepository.findOne(deliveryAddress);
        return subscriptionRepository.findBySubscriber(subscriber);
    }

    public Subscription createSubscription(Subscription subscription){
        Subscriber subscriber = subscriberRepository.findOne(subscription.getSubscriber().getDeliveryAddress());
        if(subscriber != null) {
            subscription.setSubscriber(subscriber);
        }
        return subscriptionRepository.save(subscription);
    }


    public void criarTeste() {
        Subscriber s1 = new Subscriber();
        s1.setDeliveryAddress("61981014885");
        s1 = subscriberRepository.save(s1);

        Subscriber s2 = new Subscriber();
        s2.setDeliveryAddress("61999999999");
        s2 = subscriberRepository.save(s2);

        ContentChannel c1 = new ContentChannel();
        c1.setName("Canal do mengao");
        c1.setTags(Arrays.asList(new String[]{"futebol carioca","flamengo"}));
        c1 = contentChannelRepository.save(c1);


        ContentChannel c2 = new ContentChannel();
        c2.setName("Canal de promos");
        c2.setTags(Arrays.asList(new String[]{"hardmob","promocoes"}));
        c2 = contentChannelRepository.save(c2);

        Subscription su1 = new Subscription();
        su1.setContentChannel(c1);
        su1.setSubscriber(s1);
        su1.setDate(new Date());

        Subscription su2 = new Subscription();
        su2.setContentChannel(c1);
        su2.setSubscriber(s2);
        su2.setDate(new Date());

        Subscription su3 = new Subscription();
        su3.setContentChannel(c2);
        su3.setSubscriber(s1);
        su3.setDate(new Date());

        Subscription su4 = new Subscription();
        su4.setContentChannel(c2);
        su4.setSubscriber(s2);
        su4.setDate(new Date());

        subscriptionRepository.save(su1);
        subscriptionRepository.save(su2);
        subscriptionRepository.save(su3);
        subscriptionRepository.save(su4);








    }
}
