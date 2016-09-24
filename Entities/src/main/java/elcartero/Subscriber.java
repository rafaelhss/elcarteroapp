package elcartero;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rafa on 06/09/2016.
 */
@Entity
@Getter
@Setter
public class Subscriber implements Serializable{
    @Id
    private String deliveryAddress;
}