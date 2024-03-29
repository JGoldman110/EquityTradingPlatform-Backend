package citivelociti.backend.JMS;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;
import java.util.Calendar;
import java.util.logging.Logger;

@Component
public class OrderBroker {
    @Autowired
    JmsTemplate jmsTemplate;

    private static final Logger LOGGER = Logger.getLogger(OrderBroker.class.getName());

    @JmsListener(destination = "OrderBroker", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage)message;
            Boolean buy = mapMessage.getBoolean("buy");
            Double price = mapMessage.getDouble("price");
            Integer size = mapMessage.getInt("size");
            String stock = mapMessage.getString("stock");
            String whenAsDate = mapMessage.getString("whenAsDate");
            int correlationID = Integer.parseInt(mapMessage.getJMSCorrelationID());
            sendMessageBack(correlationID, buy, price,  size,  stock, whenAsDate);
        } catch (Exception e) {
            LOGGER.severe("Error with data obtained");
        }
    }

    public void sendMessageBack(int correlationID, boolean buy, double price, int size, String stock, String whenAsDate) {
        jmsTemplate.send("OrderBroker_Reply", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setBoolean("buy",buy);
                message.setDouble("price",price);
                message.setInt("size",size);
                message.setString("stock", stock);
                message.setString("whenAsDate", whenAsDate);
                message.setJMSCorrelationID(correlationID + "");
                message.setString("result", "FILLED");
                return message;
            }
        });
    }
}
