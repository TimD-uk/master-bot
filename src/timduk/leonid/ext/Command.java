package timduk.leonid.ext;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Command {
    private List<String> aliases = new ArrayList<>();
    private List<Long> allowedChannels = new ArrayList<>();
    private List<Long> blockedChannels = new ArrayList<>();
    private List<Long> allowedRoles = new ArrayList<>();
    private List<Long> blockedRoles = new ArrayList<>();
    private String cmdDescription;
    private Map<Long, Integer> limit = new HashMap<>();

    //Тут - алиасы, группы, которым запрещено пользоваться, группы, которым разрешено
    public Command() {
    }

    public final Command addAlias(String alias) {
        this.aliases.add(alias);
        return this;
    }

    public final Command addAllowedChannels(Long channelId) {
        this.allowedChannels.add(channelId);
        return this;
    }

    public final Command addBlockedChannels(Long channelId) {
        this.blockedChannels.add(channelId);
        return this;
    }

    public final Command addAllowedRoles(Long roleId) {
        this.allowedRoles.add(roleId);
        return this;
    }

    public final Command addBlockedRoles(Long roleId) {
        this.blockedRoles.add(roleId);
        return this;
    }

    public final Command addLimit(Long roleId, Integer times) {
        this.limit.put(roleId, times);
        return this;
    }

    public final List<String> getAliases() {
        return this.aliases;
    }

    public final List<Long> getAllowedChannels() {
        return this.allowedChannels;
    }

    public final List<Long> getBlockedChannels() {
        return this.blockedChannels;
    }

    public final List<Long> getAllowedRoles() {
        return this.allowedRoles;
    }

    public final List<Long> getBlockedRoles() {
        return this.blockedRoles;
    }

    public final Integer getLimit(Long roleId) {
        return limit.get(roleId);
    }

    public final String getCmdDescription() {
        return this.cmdDescription;
    }

    public final Command setCmdDescription(String cmdDescription) {
        this.cmdDescription = cmdDescription;
        return this;
    }

    public final Boolean hasArgs(String text) {
        String[] arr = text.split(" ");
        return arr.length >= 1;
    }

    public final Boolean isAllowedChannel(Long channelId) {
        if (this.getAllowedChannels().isEmpty())
            return true;
        return this.getAllowedChannels().contains(channelId);
    }

    public final Boolean hasPerms(Member member) {
        if (member.hasPermission(Permission.ADMINISTRATOR))
            return true;

        List<Role> roles = member.getRoles();
        for (Long allowedRole : this.getAllowedRoles()) {
            for (Role role : roles) {
                if (role.getIdLong() == allowedRole)
                    return true;
            }
        }
        return false;
    }

    public void execute(Message msg) {
    }
}