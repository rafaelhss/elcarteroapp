package elcartero;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

/**
 * Created by rafa on 06/09/2016.
 */
@Entity
@Getter
@Setter
public class ContentChannel {
    @Id
    String name;

    @ElementCollection
    @CollectionTable(name = "tags_channels")
    private List<String> tags;



}
