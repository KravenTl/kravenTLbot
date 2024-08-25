package umg.programacionII.BotTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot{

    @Override
    public String getBotUsername() {
        return "KravenTLbot";
    }

    @Override
    public String getBotToken() {
        return "6887244452:AAGVAE-lyhAMRDiZaje4zOU_Ytf0A69dlFE";
    }

    //El método onUpdateReceived(Update update) de la clase Bot se usa para manejar todas las actualizaciones que el
    // bot recibe.
    // Dependiendo del tipo de actualización, se toman diferentes acciones.

    @Override
    public void onUpdateReceived(Update update) {
        String nombre=update.getMessage().getFrom().getFirstName();
        String apellido=update.getMessage().getFrom().getLastName();
        String nickname=update.getMessage().getFrom().getUserName();

        //Se verifica si la actualización contiene un mensaje y si ese mensaje tiene texto.
        //Luego se procesa el contenido del mensaje y se responde según el caso.
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("Hola "+nickname+" Tu nombre es: "+nombre+" y tu apellido es: "+apellido);
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            System.out.println("User id: " + chat_id + " Message: " + message_text);

            //manejo de mensajas
            if(message_text.toLowerCase().equals("hola")){
                sendText(chat_id,"Hola"+nombre+" Gusto saludarte malparido");
            }
        }
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }


}
