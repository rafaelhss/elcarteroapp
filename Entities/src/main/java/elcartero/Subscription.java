package elcartero;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rafa on 06/09/2016.
 */
@Entity
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //(cascade = CascadeType.ALL)
    private Subscriber subscriber;

    private Date date;

    @ManyToOne//(cascade = CascadeType.ALL)
    private ContentChannel contentChannel;
}
