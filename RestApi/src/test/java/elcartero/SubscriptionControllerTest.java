package elcartero;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



import java.util.Date;

/**
 * Created by rafa on 06/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class SubscriptionControllerTest {

    @Value("${local.server.port}")   // 6
    int port;

    @Autowired
    private SubscriptionService subscriptionSvc;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ContentChannelRepository contentChannelRepository;

    @Before
    public void setUp() {

        Subscriber subscriber = new Subscriber();
        subscriber.setDeliveryAddress("999999999");

        Subscription subscription = new Subscription();
        subscription.setSubscriber(subscriber);
        subscription.setDate(new Date());

        ContentChannel contentChannel = new ContentChannel();
        contentChannel.setName("Corinthians");
        //contentChannel = contentChannelRepository.save(contentChannel);

        subscription.setContentChannel(contentChannel);

        subscriptionRepository.deleteAll();
        subscriptionRepository.save(subscription);

        RestAssured.port = 8080;
    }

    @Test
    @Ignore
    public void testGetSubscriptions() throws Exception {
        RestAssured.when().
                get("/subscription/999999999/subscription").
                then().
                statusCode(HttpStatus.SC_OK).
                body("id", Matchers.notNullValue()).
                body("subscriber", Matchers.notNullValue()).
                body("subscriber.deliveryAddress", Matchers.hasItem("999999999"));
    }

    @Test
    @Ignore
    public void createSubscription() throws Exception {

        Subscriber subscriber = new Subscriber();
        subscriber.setDeliveryAddress("999999999");

        Subscription subscription = new Subscription();
        subscription.setSubscriber(subscriber);
        subscription.setDate(new Date());

        ContentChannel contentChannel = new ContentChannel();
        contentChannel.setName("Flamengo");
        contentChannel = contentChannelRepository.save(contentChannel);

        subscription.setContentChannel(contentChannel);
        RestAssured.
                given().
                    contentType(ContentType.JSON).
                    body(subscription).
                when().
                    post("/subscription/888888888/subscription").
                then().
                    statusCode(HttpStatus.SC_OK).
                    body("id", Matchers.notNullValue()).
                    body("subscriber", Matchers.notNullValue()).
                    body("subscriber.deliveryAddress", Matchers.is("999999999"));
    }



}