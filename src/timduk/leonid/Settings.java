package timduk.leonid;

import timduk.leonid.commands.mute;
import timduk.leonid.commands.nick;
import timduk.leonid.commands.spit;
import timduk.leonid.commands.tell;
import timduk.leonid.ext.Command;
import timduk.leonid.utils.log;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {
    // Database
    public final String db_tableNameMute = "mute";

    public Map<Long, Long> mutedUsers = new HashMap<>();
    public final String db_tableNameSettings = "settings";
    // Leonid bot
    public String LeonidToken;
    public Long technoCraftGeneralChannelId = 466530335782338560L;
    // Users' Ids
    public Long TimDukId = 210516579778166787L;
    public Long LeonidUserId = 602865066462806047L;
    //Channels' Ids
    public Long technoCraftLuckyManChannelId = 602866931791560727L;
    public Long technoCraftTestGeneralChannelId = 466530335782338560L;
    // Modules execute
    public Boolean enableCmdModule = true;
    public Long technoCraftAdminChannelId = 466321863199424525L;
    // Guilds' Ids
    public Long technoGuildId = 466321862209699871L;
    // Roles' Ids
    public Long technoHeadRoleId = 466529626802618378L;
    public Long technoTimDukRoleId = 466344989979312158L;
    public Long technoModerRoleId = 466529791009357835L;
    public Long technoLowModerRoleId = 466623863602806784L;
    public Long technoLuckyBoyRoleId = 604419998445862912L;
    public Long technoMuteRoleId = 467761280590282763L;

    Settings() throws SQLException
    {
        log.info("Settings begin to initialize");
        LeonidToken = Leonid.database.select("*", this.db_tableNameSettings, "settings_name='token'").get(0).get(1).toString();
        List<List> muted = Leonid.database.select("*", this.db_tableNameMute);
        for (List list : muted)
        {
            this.mutedUsers.put(Long.parseLong((String) (list.get(0))), Long.parseLong((String) (list.get(1))));
        }
        log.info("Settings initialized");
    }
    // Commands
    public String cmdPrefix = "!";
    public Map<String, Command> commands = new HashMap<>();

    public void initCmd() throws SQLException
    {
        this.commands.put(
                "tell",
                new tell()
                        .addAlias("tell")
                        .addAlias("say")
                        .addAlias("говори")
                        .addAlias("скажи")
                        .addAllowedChannels(this.technoCraftLuckyManChannelId)
                        .addAllowedChannels(this.technoCraftAdminChannelId)
                        .addAllowedRoles(this.technoLuckyBoyRoleId)
                        .addAllowedRoles(this.technoHeadRoleId)
                        .addAllowedRoles(this.technoTimDukRoleId)
                        .setCmdDescription("Команда для общение с пользователями через бота")
        );
        this.commands.put(
                "nick",
                new nick()
                        .addAlias("nick")
                        .addAlias("nickname")
                        .addAlias("ник")
                        .addAlias("никнейм")
                        .addAlias("поменяйник")
                        .addAllowedChannels(this.technoCraftLuckyManChannelId)
                        .addAllowedChannels(this.technoCraftAdminChannelId)
                        .addAllowedRoles(this.technoLuckyBoyRoleId)
                        .addLimit(this.technoLuckyBoyRoleId, 3)
                        .addLimit(this.technoHeadRoleId, 0)
                        .addLimit(this.technoTimDukRoleId, 0)
                        .setCmdDescription("Команда для изменения никнейма пользователю на 10 минут")
        );
        this.commands.put(
                "spit",
                new spit()
                        .addAlias("spit")
                        .addAlias("плюнуть")
                        .addAlias("харкнуть")
                        .addAllowedChannels(this.technoCraftLuckyManChannelId)
                        .addAllowedChannels(this.technoCraftAdminChannelId)
                        .addAllowedRoles(this.technoLuckyBoyRoleId)
                        .addLimit(this.technoLuckyBoyRoleId, 11)
                        .addLimit(this.technoHeadRoleId, 0)
                        .addLimit(this.technoTimDukRoleId, 0)
                        .setCmdDescription("Плевок в ебало")
                //TODO количество использований - (количество, роль); 0 - бесконечно
        );
        this.commands.put(
                "mute",
                new mute()
                        .addAlias("мут")
                        .addAlias("mute")
                        .addAllowedRoles(this.technoModerRoleId)
                        .addAllowedRoles(this.technoLowModerRoleId)
                        .setCmdDescription("Выдача мута игрокам, когда они заходят слишком далеко")
        );
    }
}
