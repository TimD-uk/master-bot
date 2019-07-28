package timduk.leonid.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import timduk.leonid.Settings;
import timduk.leonid.ext.Chat;
import timduk.leonid.ext.Command;
import timduk.utils.log;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class spit extends Command {
    private Map<spitChance, Long[]> roleChances = new HashMap<>();
    private List<Member> memberListHigh = new ArrayList<>();
    private List<Member> memberListLow = new ArrayList<>();
    private List<Member> commonList = new ArrayList<>();

    public spit() {
        log.info("Command 'spit' registered");
    }

    @Override
    public void execute(Message msg) {
        if (!hasPerms(msg.getMember())) {
            return;
        }

        if (!isAllowedChannel(msg.getChannel().getIdLong())) {
            return;
        }

        roleChances.put(spitChance.HIGH, new Long[]{525412671470960641L, 466525684844462090L, 466526022951632896L, 466526072461066250L, 466526113741406239L, 466529626802618378L, 466529791009357835L, 466623863602806784L});
        roleChances.put(spitChance.LOW, new Long[]{466526156204802050L, 466526206825594881L, 466526230003580928L, 466526278120636417L});

        for (int i = 0; i < roleChances.get(spitChance.HIGH).length; i++) {
            memberListHigh.addAll(msg.getGuild().getMembersWithRoles(msg.getGuild().getRoleById(roleChances.get(spitChance.HIGH)[i])));
        }
        memberListHigh = removeDuplicates(memberListHigh);

        List<Member> localList = memberListHigh;

        for (int i = 0; i < spitChance.HIGH.get(); i++) {
            memberListHigh.addAll(localList);
        }

        for (int i = 0; i < roleChances.get(spitChance.LOW).length; i++) {
            memberListLow.addAll(msg.getGuild().getMembersWithRoles(msg.getGuild().getRoleById(roleChances.get(spitChance.LOW)[i])));
        }

        memberListLow = removeDuplicates(memberListLow);

        commonList.addAll(memberListHigh);
        commonList.addAll(memberListLow);

        Member randMem = commonList.get(rand(commonList.size()));

        msg.getGuild().getTextChannelById(Settings.get().technoCraftGeneralChannelId).sendMessage(msg.getAuthor().getAsMention() + " плюнул в сторону и попал в " + Chat.HIDE + randMem.getAsMention() + Chat.HIDE + " \\\uD83D\uDCA6 \\\uD83D\uDCA6").queue();
        msg.getChannel().sendMessage(
                new EmbedBuilder()
                        .addField("Leonid Arkadyevich", Chat.BOLD + "Вы плюнули в " + randMem.getAsMention() + Chat.BOLD, false)
                        .setColor(Color.MAGENTA)
                        .build()
        ).queue();
    }

    private ArrayList<Member> removeDuplicates(List<Member> list) {
        ArrayList<Member> newList = new ArrayList<>();
        for (Member element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    private Integer rand(Integer max) {
        return (int) (Math.random() * max);
    }

    private enum spitChance {
        HIGH(2),
        LOW(1);

        private final int chance;

        spitChance(int chance) {
            this.chance = chance;
        }

        public Integer get() {
            return this.chance;
        }
    }
}