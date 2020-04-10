import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws LoginException, RateLimitedException {
        JDA jda = new JDABuilder("NjM3NzA3MDMxODA0OTAzNDI1.Xo-wtA.C01nq67unAIS5Yrg_EcY8WKb90I").build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextChannel annonce = jda.getTextChannelById("630100358613565442");

        List<User> random = new ArrayList<>();
        MessageHistory history = annonce.getHistory();
        List<Message> msgs = history.retrievePast(1).complete();
        for (Message msg : msgs) {
            for (MessageReaction reaction : msg.getReactions()) {
                for (User retrieveUser : reaction.retrieveUsers()) {
                    System.out.println(retrieveUser.getName()+ "#"+retrieveUser.getDiscriminator());
                    random.add(retrieveUser);
                }
            }
        }

        User winer = getWiner(random);
        System.out.println("-------------------------------------------------------");
        System.out.println("Le gagnant est : " + winer.getName() +"#"+ winer.getDiscriminator());
        System.out.println("-------------------------------------------------------");


    }

    public static User getWiner(List<User> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
