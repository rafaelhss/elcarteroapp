package elcartero.outmessage;

import elcartero.Subscriber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by rafa on 06/09/2016.
 */
@Entity
@Getter
@Setter
public class OutMessage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Subscriber subscriber;
    private String title;
    private String text;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date generationDate;

}
