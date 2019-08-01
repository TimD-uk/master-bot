package timduk.leonid;

import net.dv8tion.jda.core.entities.Message;
import timduk.leonid.ext.Command;
import timduk.leonid.utils.log;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandManager {
    private Map<String, String> allAlias = new HashMap<>();

    public CommandManager() {
        if (!Leonid.settings.enableCmdModule)
            return;
        // Регистрация команд - вручную
        this.compileAllAlias();
        log.info("Command manager initialized");
    }


    private void compileAllAlias() {
        for (Map.Entry<String, Command> command : Leonid.settings.commands.entrySet())
        {
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
            if (Pattern.compile(Leonid.settings.cmdPrefix + alias.getKey() + "( ?|$)").matcher(fullText).find())
            {
                return alias.getValue();
            }
        }
        return null;
    }

    public void checkChat(Message message) {
        if (!Leonid.settings.enableCmdModule)
            return;
        String cmdName = whatCommand(message.getContentDisplay());
        if (cmdName == null)
            return;

        Leonid.settings.commands.get(cmdName).execute(message);
    }
}