package downloader.batch;

import mail.EmailDispatcher;
import elcartero.noticia.Noticia;
import elcartero.noticia.NoticiaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Repository
public class NoticiaWriter implements ItemWriter<Noticia> {
    @Autowired
    NoticiaRepository noticiaRepository;

    @Override
    public void write(List<? extends Noticia> list) throws Exception {
        for (Noticia n : list) {
            List<Noticia> noticias = noticiaRepository.findByTitulo(n.getTitulo());
            if (noticias == null || noticias.size() <= 0) {
                //EmailDispatcher.SendSimpleMessage("rafaelhss@gmail.com", n);
                //EmailDispatcher.SendSimpleMessage("humberto.sales@gmail.com", n);
                noticiaRepository.save(n);
            }
        }
    }
}
