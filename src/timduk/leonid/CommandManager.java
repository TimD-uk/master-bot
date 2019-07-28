package timduk.leonid;

import net.dv8tion.jda.core.entities.Message;
import timduk.leonid.ext.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandManager {
    private Map<String, String> allAlias = new HashMap<>();

    public CommandManager() {
        if (!Settings.get().CommandModule)
            return;
        // Регистрация команд - вручную
        this.compileAllAlias();
    }


    private void compileAllAlias() {
        for (Map.Entry<String, Command> command : Settings.get().commands.entrySet()) {
            for (String string : command.getValue().getAliases()) {
                allAlias.put(string, command.getKey());
            }
        }
    }

    private Map<String, String> getAllAlias() {
        return allAlias;
    }

    private String whatCommand(String fullText) {
        for (Map.Entry<String, String> alias : this.getAllAlias().entrySet()) {
            if (Pattern.compile(Settings.get().cmdPrefix + alias.getKey() + "( ?|$)").matcher(fullText).find()) {
                return alias.getValue();
            }
        }
        return null;
    }

    public void checkChat(Message message) {
        if (!Settings.get().CommandModule)
            return;
        String cmdName = whatCommand(message.getContentDisplay());
        if (cmdName == null)
            return;

        Settings.get().commands.get(cmdName).execute(message);
    }
}