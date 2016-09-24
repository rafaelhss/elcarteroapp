package elcartero.noticia;

import lombok.Getter;
import lombok.Setter;

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
public class NoticiaQueue implements Serializable{
    private long id;
    private String titulo;
    private String texto;
    private URL imagem;

    private Date deliveryDate;
}
