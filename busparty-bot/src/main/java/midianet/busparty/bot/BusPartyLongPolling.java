package midianet.busparty.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusPartyLongPolling extends TelegramLongPollingBot {
    private static final String TOKEN = "";
    private static final String START = "/start";


    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if(update.getMessage() != null){
            actionMessage(update);
        }else if(update.getCallbackQuery() != null) {
            actionCallBack(update);
        }
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    private void actionCallBack(final Update update){
//        if(update.getCallbackQuery().getData().equals(CONTINUE)){
//            actionContinue(update);
//        }
    }

    private void actionMessage(final Update update){
        if(START.equals(update.getMessage().getText())){
            //actionStart(update);
        }
    }

//    private void actionDia(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Voltar SFR");
//        //button.setUrl("Voltar SFR");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId);
//            send.setText("Marcações do dia Segunda 18 de Julho de 2016 \n08:05\n12:04\n13:08\n18:33");
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//    private void actionUltimo(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Voltar SFR");
//        //button.setUrl("Voltar SFR");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId);
//            send.setText("Último registro de frequência\nTerça-Feira 23 de Agosto de 2016 13:07");
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//    public void actionLembrete(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Voltar SFR");
//        //button.setUrl("Voltar SFR");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId);
//            send.setText("A partir de agora você será notificado das sua marcações de ponto");
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//    private void actionSFR(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Dia");
//       // button.setUrl("Dia");
//        btns.add(button);
//        button = new InlineKeyboardButton();
//        button.setText("Último");
//        //button.setUrl("Último");
//        btns.add(button);
//        button = new InlineKeyboardButton();
//        button.setText("Lembrete");
//       // button.setUrl("Lembrete");
//        btns.add(button);
//        button = new InlineKeyboardButton();
//        button.setText("Voltar Serviço");
//        //button.setUrl("Voltar Serviço");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId);
//            send.setText("Escolha a opção");
//            send.setReplyMarkup(keyboard);
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//    private void actionContracheque(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Voltar RH-Net");
//        //button.setUrl("Voltar RH-Net");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendDocument send = new SendDocument();
//            send.setChatId(update.getMessage().getChatId().toString());
//            send.setNewDocument(new File("/home/marcos-fc/Contracheque.pdf"));
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendDocument(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }


//    private void actionRHNet(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Contracheque");
//        //button.setUrl("Contracheque");
//        btns.add(button);
//        button = new InlineKeyboardButton();
//        button.setText("Voltar Serviço");
//        //button.setUrl("Voltar Serviço");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId);
//            send.setText("Escolha a opção");
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void actionStart(final Update update){
//        final String chatId   = String.valueOf(update.getMessage().getChatId());
//        final String chatName = update.getMessage().getFrom().getFirstName();
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
////        markup = types.InlineKeyboardMarkup()
////        itembtna = types.InlineKeyboardButton('a', switch_inline_query="")
////        itembtnv = types.InlineKeyboardButton('v', switch_inline_query="")
////        itembtnc = types.InlineKeyboardButton('c', switch_inline_query="")
////        markup.row(itembtna)
////        markup.row(itembtnv, itembtnc)
////        bot.send_message(message.chat.id, "Choose one letter:", reply_markup=markup)
//
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("Continuar");
//        button.setUrl("");
//        button.setCallbackData(CONTINUE);
//        button.setSwitchInlineQuery("");
//       // button.setUrl("Continuar");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(chatId);
//            send.setText("Seja bem vindo ".concat(chatName).concat("\n").concat("Seu identificador é: ").concat(chatId));
//            send.setReplyToMessageId(update.getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void actionContinue(final Update update){
//        final String chatId   = String.valueOf(update.getCallbackQuery().getInlineMessageId());
//
//        final List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//        ArrayList<InlineKeyboardButton> btns = new ArrayList<>();
//        InlineKeyboardButton button;
//        button = new InlineKeyboardButton();
//        button.setText("SFR");
//        //button.setUrl("SFR");
//        button.setUrl("");
//        button.setCallbackData(SERVICE_SFR);
//        button.setSwitchInlineQuery("");
//        btns.add(button);
//        button = new InlineKeyboardButton();
//        button.setText("RH-Net");
//        button.setUrl("");
//        button.setCallbackData(SERVICE_RHNET);
//        button.setSwitchInlineQuery("");
//       // button.setUrl("RH-Net");
//        btns.add(button);
//        buttons.add(btns);
//        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.setKeyboard(buttons);
//        try{
//            final SendMessage send = new SendMessage();
//            send.setChatId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
//            send.setText("Escolha o Serviço");
//            send.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
//            send.setReplyMarkup(keyboard);
//            sendMessage(send);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//    public void actionDay(final Update update){
//        try{
//            List<String> list = service.findDayByIdTelegram(update.getMessage().getChatId());
//            final StringBuilder text = new StringBuilder(DateAsUTCAdapter.marshalExtend(new Date())).append("\n");
//            list.forEach(s -> text.append(s).append("\n"));
//            sendMessage(update,text.toString());
//        }catch(Exception e){
//            sendMessage(update,e.getMessage());
//        }
//    }
//
//    public void actionNotify(final Update update){
//        try{
//            boolean state = usuarioService.updateNotifyUsuario(update.getMessage().getChatId().toString());
//            final String message =  update.getMessage().getFrom().getFirstName().concat("\n").concat("A partir de agora você ").concat(state == true ? "" : "não").concat(" receberá notificações quando registrar marcações de ponto.");
//            sendMessage(update, message);
//        }catch(Exception e){
//            sendMessage(update,e.getMessage());
//        }
//    }

//
//    private void actionLast(final Update update){
//        try {
//            final Frequencia f = service.findLastByIdTelegram(update.getMessage().getChatId());
//            final String dateExtend = DateAsUTCAdapter.marshalExtend(f.getData());
//            final String time = DateAsUTCAdapter.marshalTime(f.getData());
//            sendMessage(update,"Último registro de frequência: \n".concat(dateExtend).concat("\n").concat(time));
//        }catch(Exception e){
//            sendMessage(update,e.getMessage());
//        }
//    }

    private void sendMessage(final Update update, final String message){
        try {
            final SendMessage m = new SendMessage();
            m.setChatId(update.getMessage().getChatId().toString());
            m.setText(message);
            sendMessage(m);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(final String chatId, final String message){
        try {
            final SendMessage m = new SendMessage();
            m.setChatId(chatId);
            m.setText(message);
            sendMessage(m);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}