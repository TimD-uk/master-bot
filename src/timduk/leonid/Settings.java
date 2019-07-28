package timduk.leonid;

import timduk.leonid.commands.mute;
import timduk.leonid.commands.nick;
import timduk.leonid.commands.spit;
import timduk.leonid.commands.tell;
import timduk.leonid.ext.Command;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private static Settings instance = new Settings();
    public Map<Long, Long> mutedUsers = new HashMap<>();
    // Leonid bot
    public String LeonidToken = "NjAyODY1MDY2NDYyODA2MDQ3.XTXD9g.8Mc56nxC_OkDVFYnihrtrALjdbY"; //TODO перевести параметры в отдельный файл
    // Users' Ids
    public Long TimDukId = 210516579778166787L;
    public Long LeonidUserId = 602865066462806047L;
    //Channels' Ids
    public Long technoCraftLuckyManChannelId = 602866931791560727L;
    public Long technoCraftGeneralChannelId = 604029873895505935L;
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
    // Modules execute
    public Boolean CommandModule = true;
    // Commands
    public String cmdPrefix = "!";
    public Map<String, Command> commands = new HashMap<>();

    public static Settings get() {
        return instance;
    }
    //TODO создать класс, куда будут записываться данные команд

    void init() {
        this.initCmd();
    }


    private void initCmd() {
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
                        .setCmdDescription("Выдача мута игрокам, когда она заходят слишком далеко")
        );
    }
}
