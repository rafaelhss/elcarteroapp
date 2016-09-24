package elcartero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rafa on 06/09/2016.
 */
@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, String> {
}
