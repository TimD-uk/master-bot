package timduk.leonid;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static timduk.leonid.Leonid.commandManager;
import static timduk.leonid.Leonid.leonidJda;

public class ActionManager extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // В БД (или массив) добавление сообщения
        leonidJda = event.getJDA();
        if (event.getAuthor().isBot())
            return;

        //TODO here -> DB

        commandManager.checkChat(event.getMessage());
    }
}