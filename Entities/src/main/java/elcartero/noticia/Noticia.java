package elcartero.noticia;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Getter
@Setter
@Entity
public class Noticia implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String titulo;
    @Column( length = 100000 )
    private String texto;
    private URL imagem;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tags")
    private List<String> tags;


    private Date deliveryDate;
}
