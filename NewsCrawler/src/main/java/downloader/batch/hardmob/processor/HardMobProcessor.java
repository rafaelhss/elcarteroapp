package downloader.batch.hardmob.processor;

import downloader.batch.hardmob.model.HardMobPromo;

import elcartero.noticia.Noticia;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafa on 04/08/2016.
 */
public class HardMobProcessor  implements ItemProcessor<HardMobPromo, Noticia> {
    @Override
    public Noticia process(HardMobPromo hardMobPromo) throws Exception {
        //Faz o download do corpo do link e monta uma noticia

        Noticia n = new Noticia();
        n.setTitulo(hardMobPromo.getTitulo());
        n.setTexto("link:[" + hardMobPromo.getUrl().toString() + "]:" +  hardMobPromo.getDesc());

        List<String> tags = new ArrayList<String>();
        tags.add("hardmob");
        tags.add("promocoes");

        n.setTags(tags);
        return n;
    }
}
