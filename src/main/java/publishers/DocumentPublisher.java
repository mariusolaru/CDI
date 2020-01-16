package publishers;

import entity.Document;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Stateless
public class DocumentPublisher {

    @Resource(lookup = "Documents")
    private Topic topic;

    @Inject
    private JMSContext context;

    public void sendDocumentMessage(Document document) {
        context.createProducer().send(topic, document);
    }
}
