package midianet.busparty.bot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.BotOptions;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Begin {

    private Logger log = Logger.getLogger(Begin.class);

    @Autowired
    private void initBot(final BusPartyLongPolling bot){
        final BotOptions options = new BotOptions();
        try {
            log.info("Starting Bot...");
            final TelegramBotsApi telegramBot = new TelegramBotsApi();
            telegramBot.registerBot(bot);
            log.info("Bot started.");
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }
//
//    @Bean
//    public DataSource dataSource() {
//        final BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(Driver.class.getName());
//        dataSource.setUsername("sfr");
//        dataSource.setUrl("jdbc:h2:file:/opt/sfrbot/db/sfr-telegram;DB_CLOSE_DELAY=- 1;DB_CLOSE_ON_EXIT=FALSE");
//        dataSource.setPassword("sfr");
//        return dataSource;
//    }
//
//    @Autowired
//    private void initDB(final Repositorio repositorio){
//        repositorio.constroi();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Begin.class, args);
    }

}