package midianet.busparty.bot;

import midianet.busparty.domain.bussines.FinanceBussines;
import midianet.busparty.domain.bussines.PersonBussines;
import midianet.busparty.domain.model.Gender;
import midianet.busparty.domain.model.Payment;
import midianet.busparty.domain.model.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class BuspartyBot extends TelegramLongPollingBot {
    private Logger log = Logger.getLogger(getClass());
//    private static final String user = "@MidianetBot";
//    private static final String token = "193114577:AAGVvy-HeqaqYz9wvzmg-fjkvljwOvlEpOA";

    private static final String user = "@LatinowareGoiasBot";
    private static final String token = "206834104:AAF-N-BJH1iuzrE14JtHD-JzZdVtknYQCjs";

    private static final String CMD_START   = "/start";
    private static final String CMD_LIST    = "/list";
    private static final String CMD_ACCOUNT = "/account";
    private static final String CMD_BALANCE = "/balance";

    private static final String MNU_ROOT    = "MNUROOT";

//    private static final String CMD_NTC_ALL = "/noticeAll";
//    private static final String CMD_NTC_PAY = "/noticePay";
//    private static final String CMD_PROFILE = "/profile";
//    private static final String CMD_KICK    = "/kick";
//    private static final String CMD_DRINK   = "/drink";
//    private static final String CMD_CERVEJA = "/cerva";
//    private static final String CMD_REFRIG  = "/refri";
//    private static final String CMD_AGREE   =  "/concordo";
//    private static final String CMD_DISAGREE=  "/discordo";
//    private static final String CMD_ENERG   = "/energ";
//    private static final String CMD_SUCO    = "/suco";
//    private static final String CMD_ICE     = "/ice";
//    private static final String CMD_TODDY   = "/toddy";
//    private static final String CMD_AGUAC   = "/agua";
//    private static final String CMD_VOLTAR  = "/back";
//    private static final String MNU_DRINK   = "MNUDRINK";
//    private static final String CMD_CONDUCT = "/conduct";

    @Autowired
    private PersonBussines personBussines;

//    @Autowired
//    private QuartoBussines quartoBussines;

    @Autowired
    private FinanceBussines financeBussines;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return user;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage()) {
            final String text = update.getMessage().getText();
            if (text.indexOf(CMD_START) == 0) {
                actionStart(update);
            }
        }else if(update.hasCallbackQuery()){
            switch (update.getCallbackQuery().getData()) {
                case CMD_LIST:
                    actionList(update);
                    break;
                case CMD_ACCOUNT:
                    actionAccount(update);
                    break;
                case CMD_BALANCE:
                    actionBalance(update);
                    break;
//                case CMD_PROFILE:
//                    actionProfile(update);
//                    break;
//                case CMD_DRINK:
//                    actionDrink(update);
//                    break;
//                case CMD_VOLTAR:
//                    actionBack(update);
//                    break;
//                case CMD_CERVEJA:
//                    actionCerveja(update);
//                    break;
//                case CMD_ENERG:
//                    actionEnerg(update);
//                    break;
//                case CMD_REFRIG:
//                    actionRefrig(update);
//                    break;
//                case CMD_ICE:
//                    actionIce(update);
//                    break;
//                case CMD_SUCO:
//                    actionSuco(update);
//                    break;
//                case CMD_TODDY:
//                    actionToddy(update);
//                    break;
//                case CMD_AGUAC:
//                    actionAguaC(update);
//                    break;
//                case CMD_CONDUCT:
//                    actionConduct(update);
//                    break;
//                case CMD_AGREE:
//                    actionAgree(update);
//                    break;
//                case CMD_DISAGREE:
//                    actionDesagree(update);
//                    break;
//            }
            }

        }
    }



            /*else if(text.indexOf(CMD_NTC_ALL) ==  0) {
                actionNoticeAll(update);
            }else if(text.indexOf(CMD_NTC_PAY) ==  0) {
                actionNoticePay(update);*/
//            } else if (text.indexOf(CMD_KICK_ALL) == 0) {
//                actionKickAll(update);
//            }
//        }
//    }

//    private void actionKickAll(final Update update) {
//        final Long chatId = update.getMessage().getChatId();
//        try {
//            final List<Person> l = bussinesPessoa.listAll();
//            final StringBuilder s = new StringBuilder();
//            l.forEach(person -> {
//                s.append(person.getName()).append("\n");
//
//            } );
//
//
//
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId.toString());
//            send.setText("apaga o povo..");
//            sendMessage(send);
//        } catch (Exception e) {
//            log.error(e);
//        }
//    }

    private void actionStart(final Update update){
        final Long chatId = update.getMessage().getChatId();
        final StringBuilder name = new StringBuilder(update.getMessage().getFrom().getFirstName());
        final Optional<String> lastName = Optional.ofNullable(update.getMessage().getFrom().getLastName());
        lastName.ifPresent(s -> name.append(" ").append(s));
        try{
            final SendMessage send = new SendMessage();
            final Optional<Person> old = personBussines.findByTelegram(chatId);
            old.orElseGet(() -> {
                final Person young = new Person();
                young.setName(name.toString());
                young.setTelegram(chatId);
                personBussines.save(young);
                return young;
            });
            send.setChatId(chatId.toString());
            send.setText("Bem vindo ".concat(name.toString()).concat("\n").concat("Escolha uma das opções abaixo."));
            send.setReplyMarkup(buildKeyboard(MNU_ROOT));
            sendMessage(send);
        } catch (Exception e) {
            log.error(e);
        }
    }

    private String makeIcon(final Gender gender){
        if(gender.equals(Gender.FEMALE)){
            return "\uD83D\uDC69";
        }else{
            return "\uD83D\uDC71";
        }
    }

    private void actionList(final Update update){
        try {
            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            final StringBuilder names = new StringBuilder();
            final List<Person> confirmedList = personBussines.listConfirmed();
            final List<Person> waitingList   = personBussines.listWaiting();
            names.append("Confirmados: ").append(confirmedList.size()).append("\n");
            confirmedList.forEach(p -> {
                Optional.ofNullable(p.getGender()).ifPresent(g -> names.append(makeIcon(g)));
                names.append(p.getName().concat("\n"));
            });
            names.append("\n");
            names.append("Em espera: ").append(waitingList.size()).append("\n");
            waitingList.forEach(p -> {
                Optional.ofNullable(p.getGender()).ifPresent(g -> names.append(makeIcon(g)));
                names.append(p.getName().concat("\n"));
            });
            names.append("\n");
            names.append("Total: ").append(confirmedList.size() + waitingList.size());
            if(!update.getCallbackQuery().getMessage().getText().trim().equals(names.toString().trim())){
                final EditMessageText editText = new EditMessageText();
                editText.setText(names.toString());
                editText.setChatId(chatId.toString());
                editText.setMessageId(messageId);
                editText.setReplyMarkup(buildKeyboard(MNU_ROOT));
                editMessageText(editText);
            }
        }catch(Exception e){
            log.error(e);
        }
    }

    private void actionAccount(final Update update){
        try {
            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            final String text = " Banco Bradesco \n Agência 2305 \n Conta Corrente 35041-9 \n Rodrigo Rodrigues da Costa \n CPF 855.066.051-53 \n Email rodrigorgvti@gmail.com \n whatsapp 62.9800-5224";
            if(!update.getCallbackQuery().getMessage().getText().trim().equals(text.trim())) {
                final EditMessageText editText = new EditMessageText();
                editText.setText(text);
                editText.setChatId(chatId.toString());
                editText.setMessageId(messageId);
                editText.setReplyMarkup(buildKeyboard(MNU_ROOT));
                editMessageText(editText);
            }
        }catch(Exception e){
            log.error(e);
        }
     }

    private void actionBalance(final Update update) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            final NumberFormat     nbf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR" ));
            final Long chatId = update.getCallbackQuery().getMessage().getChatId();
            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            final StringBuilder report = new StringBuilder();
            final Optional<Person> person = personBussines.findByTelegram(chatId);
            person.ifPresent(p -> {
                Double credit =0.0;
                //Double debit = 0.0;
                final List<Payment> payments = financeBussines.listPaymentsDoneByPerson(p);
                if(payments.size() > 0){
                    report.append("\nDepósitos");
                    payments.forEach(pa -> {
                        try {
                            report.append("\n ").append(sdf.format(pa.getDate())).append("   ").append(nbf.format(pa.getAmmount()));
                        }catch (Exception e){
                            log.error(e);
                        }
                    });
                   credit = payments.stream().mapToDouble(Payment::getAmmount).sum();
                }
                //final List<Despesa> debits = bussinesFinanceiro.listDespesaByPessoa(p);
                //debits.forEach(db -> report.append("\n").append(db.getDescricao()).append(" ").append(db.getValor()));
                //report.append("Despesas");
                //final Double debit = Math.ceil(debits.stream().mapToDouble(Despesa::getValor).sum());
                report.append("\nSaldo ").append(nbf.format(credit));
            });
            if(person.isPresent()){
                if (!update.getCallbackQuery().getMessage().getText().trim().equals(report.toString().trim())) {
                    final EditMessageText editText = new EditMessageText();
                    editText.setText(report.toString());
                    editText.setChatId(chatId.toString());
                    editText.setMessageId(messageId);
                    editText.setReplyMarkup(buildKeyboard(MNU_ROOT));
                    editMessageText(editText);
                }
            }
        }catch(TelegramApiException e){
            log.error(e);
        }catch(Exception e){
            log.error(e);
        }
    }

    private InlineKeyboardMarkup buildKeyboard(final String action){
        final List<List<InlineKeyboardButton>> buttons = new ArrayList();
        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        ArrayList<InlineKeyboardButton> line  = new ArrayList();
        InlineKeyboardButton button = new InlineKeyboardButton();

        //line = new ArrayList();
        //buttons.add(line);
        //button = new InlineKeyboardButton();
        button.setText(" \uD83C\uDFAD Companheiros");
        button.setCallbackData(CMD_LIST);
        line.add(button);
        buttons.add(line);

        line = new ArrayList();
        button = new InlineKeyboardButton();
        button.setText("\uD83D\uDCB3 Depósito");
        button.setCallbackData(CMD_ACCOUNT);
        line.add(button);
        button = new InlineKeyboardButton();
        button.setText("\uD83D\uDCB0 Saldo");
        button.setCallbackData(CMD_BALANCE);
        line.add(button);
        buttons.add(line);

        keyboard.setKeyboard(buttons);
        return keyboard;


        //button.setText("Perfil");
        //button.setCallbackData(CMD_PROFILE);
        //line.add(button);



//        if(action.equals(MNU_DRINK)) {
//            button.setText("Cerveja");
//            button.setCallbackData(CMD_CERVEJA);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Refrigerante");
//            button.setCallbackData(CMD_REFRIG);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Energético");
//            button.setCallbackData(CMD_ENERG);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Suco");
//            button.setCallbackData(CMD_SUCO);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Ice");
//            button.setCallbackData(CMD_ICE);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Toddynho");
//            button.setCallbackData(CMD_TODDY);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Água de Côco");
//            button.setCallbackData(CMD_AGUAC);
//            line.add(button);
//            buttons.add(line);
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Voltar");
//            button.setCallbackData(CMD_VOLTAR);
//            line.add(button);
//            buttons.add(line);
//        }else{
// /            button = new InlineKeyboardButton();
////            button.setText("Bebidas");
////            button.setCallbackData(CMD_DRINK);
////            line.add(button);
//            buttons.add(line);
//
//
//
//            line = new ArrayList();
//            button = new InlineKeyboardButton();
//            button.setText("Acordo Conduta");
//            button.setCallbackData(CMD_CONDUCT);
//            line.add(button);
//            buttons.add(line);
        //}

    }




}


//    }

//    private void actionRefrig(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isRefrigerante()){
//                    editText.setText("Você não irá beber Refrigerante (Beba Cerveja)");
//                }else{
//                    editText.setText("Você irá beber Refrigerante");
//                }
//                p.setRefrigerante(!p.isRefrigerante());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionCerveja(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isCerveja()){
//                    editText.setText("Você não irá beber Cerveja (Seu fraco)");
//                }else{
//                    editText.setText("Você irá beber Cerveja (Parabéns)");
//                }
//                p.setCerveja(!p.isCerveja());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//    private void actionEnerg(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isEnergetico()){
//                    editText.setText("Você não irá beber Energético (Beba Cerveja)");
//                }else{
//                    editText.setText("Você irá beber Energético (Vai ficar ligado)");
//                }
//                p.setEnergetico(!p.isEnergetico());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionSuco(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isSuco()){
//                    editText.setText("Você não irá beber Suco (Parabéns Beba Cerveja)");
//                }else{
//                    editText.setText("Você irá beber Suco (Fresco)");
//                }
//                p.setSuco(!p.isSuco());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionIce(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isIce()){
//                    editText.setText("Você não irá beber Ice (Parabéns Beba Cerveja)");
//                }else{
//                    editText.setText("Você irá beber Ice (Misture com energético)");
//                }
//                p.setIce(!p.isIce());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionToddy(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isToddynho()){
//                    editText.setText("Você não irá beber Toddynho (Parabéns Beba Cerveja)");
//                }else{
//                    editText.setText("Você irá beber Toddynho (quantos anos tem?)");
//                }
//                p.setToddynho(!p.isToddynho());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionAguaC(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Indefinido");
//            person.ifPresent(p -> {
//                if(p.isAguaCoco()){
//                    editText.setText("Você não irá beber Água de Côco (Parabéns Beba Cerveja)");
//                }else{
//                    editText.setText("Você irá beber Água de Côco (Bicha)");
//                }
//                p.setAguaCoco(!p.isAguaCoco());
//                bussinesPessoa.updateBebidas(p);
//            });
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionBack(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Escolha uma das opções abaixo.");
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_ROOT));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionDrink(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Escolha as bebidas que irá consumir");
//            editText.setChatId(chatId.toString());
//            editText.setMessageId(messageId);
//            editText.setReplyMarkup(getKeyboard(MNU_DRINK));
//            editMessageText(editText);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionProfile(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            final StringBuilder profile = new StringBuilder();
//            final Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            person.ifPresent(p ->{
//                profile.append("Nome: ").append(p.getNome()).append("\n");
//                profile.append("Status: ").append(p.isPagou() ? "Pagamento Parcial" : "Sem Pagamentos").append("\n");
//                profile.append("Cerveja: ").append(p.isCerveja() ? "Sim" : "Não").append("\n");
//                profile.append("Refrigerante: ").append(p.isRefrigerante() ? "Sim" : "Não").append("\n");
//                profile.append("Energetico: ").append(p.isEnergetico() ? "Sim" : "Não").append("\n");
//                profile.append("Suco: ").append(p.isSuco() ? "Sim" : "Não").append("\n");
//                profile.append("Ice: ").append(p.isIce() ? "Sim" : "Não").append("\n");
//                profile.append("Toddynho: ").append(p.isToddynho() ? "Sim" : "Não").append("\n");
//                profile.append("Água de Coco : ").append(p.isAguaCoco() ? "Sim" : "Não").append("\n");
//                p.setQuarto(quartoBussines.lotacaoById(p.getQuarto().getId()).get());
//                profile.append("Acomodação: ").append(p.getQuarto().getTipo().getDescricao()).append("\n");
//                profile.append("Ocupantes:\n");
//                p.getQuarto().getOcupantes().forEach(o -> profile.append(o.getNome()).append("\n"));
//            });
//            if(person.isPresent()){
//                if(!update.getCallbackQuery().getMessage().getText().trim().equals(profile.toString().trim())) {
//                    final EditMessageText editText = new EditMessageText();
//                    editText.setText(profile.toString());
//                    editText.setChatId(chatId.toString());
//                    editText.setMessageId(messageId);
//                    editText.setReplyMarkup(getKeyboard(MNU_ROOT));
//                    editMessageText(editText);
//                }
//            }
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//    private void actionNoticeAll(final Update update){
//        final Long chatId = update.getMessage().getChatId();
//        final StringBuilder name = new StringBuilder(update.getMessage().getFrom().getFirstName());
//        final Optional<String> lastName = Optional.ofNullable(update.getMessage().getFrom().getLastName());
//        lastName.ifPresent(s -> name.append("").append(s));
//        try{
//            final List<Pessoa> list = bussinesPessoa.listAll();
//            list.forEach(p -> {
//                final SendMessage send = new SendMessage();
//                send.setChatId(p.getIdTelegram().toString());
//                send.setText(update.getMessage().getText().replace(CMD_NTC_ALL, ""));
//                send.setReplyMarkup(getKeyboard(MNU_ROOT));
//                try {
//                    sendMessage(send);
//                }catch(TelegramApiException e){
//                    log.error(e);
//                }
//            });
//        }catch (Exception e) {
//            log.error(e);
//        }
//    }
//    private void actionNoticePay(final Update update){
//        final Long chatId = update.getMessage().getChatId();
//        final StringBuilder name = new StringBuilder(update.getMessage().getFrom().getFirstName());
//        final Optional<String> lastName = Optional.ofNullable(update.getMessage().getFrom().getLastName());
//        lastName.ifPresent(s -> name.append("").append(s));
//        try{
//            final List<Pessoa> list = bussinesPessoa.listAll().stream().filter(p -> p.isPagou()).collect(Collectors.toList());
//            list.forEach(p -> {
//                final SendMessage send = new SendMessage();
//                send.setChatId(p.getIdTelegram().toString());
//                send.setText(update.getMessage().getText().replace(CMD_NTC_PAY, ""));
//                send.setReplyMarkup(getKeyboard(MNU_ROOT));
//                try {
//                    sendMessage(send);
//                }catch(TelegramApiException e){
//                    log.error(e);
//                }
//            });
//        }catch (Exception e) {
//            log.error(e);
//        }
//    }
//
//
//
//

//
//
//
//    private void actionConduct(final Update update){
//        final String chatId   = String.valueOf(update.getCallbackQuery().getMessage().getChatId().toString());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        final ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        button.setText("Concordo");
//        button.setCallbackData(CMD_AGREE);
//        btns.add(button);
//        button = new InlineKeyboardButton();
//        button.setText("Discordo");
//        button.setCallbackData(CMD_DISAGREE);
//        btns.add(button);
//        buttons.add(btns);
//        keyboard.setKeyboard(buttons);
//        try{
//            final EditMessageText editText = new EditMessageText();
//            editText.setText("Abra o arquivo e leia depois escolha uma opção abaixo");
//            editText.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
//            editText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
//            editMessageText(editText);
//            final SendDocument d = new SendDocument();
//            d.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
//            final SendMessage send = new SendMessage();
//            final File f = new File("/opt/latinowarebot/conduta.pdf");
//            d.setNewDocument(f);
//            sendDocument(d);
//            send.setChatId(chatId);
//            send.setText("Concluído");
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            log.error(e);
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionAgree(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            final String text = "Você concorda com os termos de conduta da caravana.";
//            final Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            person.ifPresent(p -> {
//                p.setConcorda(true);
//                bussinesPessoa.updateAccordance(p);
//            });
//            if(!update.getCallbackQuery().getMessage().getText().trim().equals(text.trim())) {
//                final EditMessageText editText = new EditMessageText();
//                editText.setText(text);
//                editText.setChatId(chatId.toString());
//                editText.setMessageId(messageId);
//                editText.setReplyMarkup(getKeyboard(MNU_ROOT));
//                editMessageText(editText);
//            }
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//
//    private void actionDesagree(final Update update){
//        try {
//            final Long chatId  = update.getCallbackQuery().getMessage().getChatId();
//            final Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
//            final String text = "Você não concorda com os termos de conduta da caravana.";
//            final Optional<Pessoa> person = bussinesPessoa.findByIdTelegram(chatId);
//            person.ifPresent(p -> {
//                p.setConcorda(false);
//                bussinesPessoa.updateAccordance(p);
//            });
//            if(!update.getCallbackQuery().getMessage().getText().trim().equals(text.trim())) {
//                final EditMessageText editText = new EditMessageText();
//                editText.setText(text);
//                editText.setChatId(chatId.toString());
//                editText.setMessageId(messageId);
//                editText.setReplyMarkup(getKeyboard(MNU_ROOT));
//                editMessageText(editText);
//            }
//        }catch(Exception e){
//            log.error(e);
//        }
//    }
//

//
//}
