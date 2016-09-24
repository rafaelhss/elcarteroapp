package elcartero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rafa on 06/09/2016.
 */
@Repository
public interface ContentChannelRepository extends CrudRepository<ContentChannel, String>{
    public List<ContentChannel> findByTagsIn(List<String> tags);
}
