package elcartero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rafa on 06/09/2016.
 */
@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long>{
    public List<Subscription> findBySubscriber(Subscriber subscriber);
    public List<Subscription> findByContentChannelIn(List<ContentChannel> channels);
}
