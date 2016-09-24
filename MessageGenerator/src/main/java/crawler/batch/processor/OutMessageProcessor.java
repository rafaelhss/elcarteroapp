package crawler.batch.processor;

/**
 * Created by rafa on 04/06/2016.
 */


import elcartero.outmessage.OutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class OutMessageProcessor implements ItemProcessor<OutMessage, OutMessage> {

    private static final Logger log = LoggerFactory.getLogger(OutMessageProcessor.class);

    @Override
    public OutMessage process(final OutMessage noticia) throws Exception {

        final OutMessage transformed = noticia;
        transformed.setTitle("Transf: " + noticia.getTitle());

        return transformed;
    }

}