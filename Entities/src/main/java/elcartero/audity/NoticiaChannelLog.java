package elcartero.audity;

import elcartero.ContentChannel;
import elcartero.noticia.Noticia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rafa on 09/09/2016.
 */
@Entity
@Getter
@Setter
public class NoticiaChannelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Noticia noticia;

    @OneToOne
    private ContentChannel contentChannel;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date generationDate;

    private int subscribers;
}
