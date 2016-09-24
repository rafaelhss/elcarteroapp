package crawler.batch.processor;

import crawler.Generator;
import elcartero.noticia.Noticia;
import elcartero.noticia.NoticiaRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rafa on 06/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Generator.class)   // 2
public class NoticiaReaderTest {

    @Autowired
    NoticiaRepository noticiaRepository;

    NoticiaReader noticiaReader = new NoticiaReader();

    @Before
    public void setUp() throws Exception {


        /*ContentChannel contentChannel = new ContentChannel();
        contentChannel.setName("Canal de teste");
        List<String> tags = new ArrayList<>();
        tags.add("Futebol");
        tags.add("Samba");
        contentChannel.setTags(tags);
*/


        Noticia n1 = new Noticia();
        n1.setTexto("Teste Brasil");
        List<String> tags1 = new ArrayList<String>();
        tags1.add("Futebol");
        tags1.add("Samba");
        n1.setTags(tags1);
        noticiaRepository.save(n1);

        Noticia n2 = new Noticia();
        n2.setTexto("Teste Argentina");
        List<String> tags2 = new ArrayList<String>();
        tags2.add("Tango");
        tags2.add("Parrilla");
        n2.setTags(tags2);
        noticiaRepository.save(n2);


    }

    @Test
    public void testFindTags() throws Exception {
        assertThat( noticiaReader.findTags(Lists.newArrayList(noticiaRepository.findAll())),
                hasItems("Futebol", "Tango", "Parrilla", "Samba"));

    }
}