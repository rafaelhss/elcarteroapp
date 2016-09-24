package elcartero.noticia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Repository
public interface NoticiaRepository extends CrudRepository<Noticia, Long> {

    List<Noticia> findByTitulo(String titulo);
    List<Noticia> findByDeliveryDateIsNull();
}