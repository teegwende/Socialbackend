
package org.waziup.socialbackend;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Drabo Constantin
 *
 */
@Path("socials")
public class Socials implements Serializable {

    ConfigurationBuilder config;
    TwitterFactory tf;
    Twitter twitter;
    DirectMessage tweet;

    /**
     * Constructor
     */
    public Socials() {
        config = new ConfigurationBuilder();
        config.setDebugEnabled(true)
                .setOAuthConsumerKey("2jZQWTn50FKzQ5L7dW6srEy3b")
                .setOAuthConsumerSecret("y8IHmnoDv3fXk55bhZvTj9i5wrOzrK5rm7lThSXi92w1BBe8b4")
                .setOAuthAccessToken("1899407767-IWV6LNohrUxVMqnSEKHQx7TtiAc6fCnMkSjGxAb")
                .setOAuthAccessTokenSecret("cyRW7MjIfEcrrb7aRq8BPgMbDvMUA5lYSHMIIKvx9LSRM");
        tf = new TwitterFactory(config.build());
        twitter = tf.getInstance();
    }

    @POST
    public void sendMessage(@FormParam("user_id") String user_id, @FormParam("channel") String channel, @FormParam("message") String message) {

        switch (channel) {
            case "facebook":
                sendFacebookMessage(user_id, message);
                break;
            case "twitter":
                sendTwitterMessage(user_id, message);
                break;
            default:
                sendTwitterMessage(user_id, message);
        }

    }

    @GET
    public String getMessageHistory() {
        return null;
    }

    @DELETE
    public void deleteMessage() {

    }

    /**
     *
     * @param messageReceiver
     * @param message
     */
    public void sendFacebookMessage(String messageReceiver, String message) {

    }

    /**
     *
     * @param messageReceiver
     * @param message
     */
    public void sendTwitterMessage(String messageReceiver, String message) {
        try {
            tweet = twitter.sendDirectMessage(messageReceiver, message);
        } catch (TwitterException ex) {
            Logger.getLogger(Socials.class.getName()).log(Level.SEVERE, tweet.getId() + " did not deliver", ex);
        } finally {
            Logger.getLogger(Socials.class.getName()).log(Level.INFO, "Message delivered by "  + tweet.getSenderScreenName() + " to "  + tweet.getRecipientScreenName() );

        }
    }

}
