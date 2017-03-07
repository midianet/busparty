package midianet.busparty;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

@Configuration
@ComponentScan("midianet")
@EnableAutoConfiguration
public class StartBOT {

    private Logger log = Logger.getLogger(StartBOT.class);

    private TelegramBotsApi telegramBot;

    @Autowired
    private void initBot(final BuspartyBot bot){
        try {
            telegramBot = new TelegramBotsApi();
            log.info("Starting bot...");
            telegramBot.registerBot(bot);
            log.info("Bot started.");
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(StartBOT.class, args);
    }

}