package elcartero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subsService;

    @RequestMapping(value = "{subscriber_id}/subscription", method = RequestMethod.GET)
    public List<Subscription> getSubscriptions(@PathVariable("subscriber_id") String subscriberId) {
        return subsService.listSubscriptionsByDeliveryAddress(subscriberId);
    }

    @RequestMapping(value = "{subscriber_id}/subscription", method = RequestMethod.POST)
    public Subscription createSubscription(@PathVariable("subscriber_id") String subscriberId,
                                                 @RequestBody Subscription subscription) {

        return subsService.createSubscription(subscription);
    }


    @RequestMapping(value = "criar")
    public void criarTudo(){
        subsService.criarTeste();
    }


}
