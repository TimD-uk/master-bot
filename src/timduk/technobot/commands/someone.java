package timduk.technobot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import timduk.properties;
import timduk.technobot.modules.MemberData;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Random;

public class someone
{
    public someone(Message msg)
    {
        String[] smiles = new String[] {
                "(͡° ͜ʖ ͡°)",
                "(͡°╭͜ʖ╮͡°)",
                "¯\\_(ツ)_/¯",
                "ʕ•ᴥ•ʔ",
                "(ง ͠° ͟ل͜ ͡°)ง",
                "༼ つ ◕◕ ༽つ",
                "(づ｡◕‿‿◕｡)づ",
                "(͡°╭͜ʖ╮͡°)",
                "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: *ヽ(◕ヮ◕ヽ)",
                "(ಠಠ)",
                "(ಥ﹏ಥ)",
                "(づ￣ ³￣)づ",
                "| (• ◡•)| (❍ᴥ❍ʋ)",
                "(ノಠ益ಠ)ノ彡┻━┻",
                "﴾͡๏̯͡๏﴿ O’RLY?",
                "٩(͡๏̯͡๏)۶",
                "ಠಠ",
                "(☞ﾟヮﾟ)☞ ☜(ﾟヮﾟ☜)",
                "(╯°□°)╯︵",
                "(͡ᵔ ͜ʖ ͡ᵔ)",
                "(☞ﾟヮﾟ)☞",
                "ヾ(⌐■■)ノ♪",
                "ヽ༼ຈل͜ຈ༽ﾉ",
                "༼ つ ಥಥ ༽つ",
                "(ง'̀-'́)ง",
                "(╯°□°）╯︵ ┻━┻",
                "ᕦ(òóˇ)ᕤ",
                "(ﾉ◕ヮ◕)ﾉ:･ﾟ✧",
                "(☞ﾟ∀ﾟ)☞",
                "┬┴┬┴┤ ͜ʖ ͡°) ├┬┴┬┴",
                "ᕙ(⇀‸↼‶)ᕗ",
                "(~˘▾˘)~ (◕‿◕)",
                "(｡◕‿‿◕｡)",
                "(｡◕‿◕｡)",
                "~(˘▾˘~)",
                "(°ロ°)☝",
                "⌐╦╦═─",
                "(☞ຈل͜ຈ)☞",
                "(ง°ل͜°)ง",
                "┌(ಠಠ)┘",
                "◉◉",
                "(╯°□°）╯︵(.o.)",
                "┬──┬ ノ(゜-゜ノ)",
                "☜(˚▽˚)☞",
                "(─‿‿─)",
                "ლ(´ڡლ)",
                "(ಥ_ಥ)",
                "ᄽὁȍ ̪ őὀᄿ",
                "\\ (•◡•) /",
                "(° ͡ ͜ ͡ʖ ͡ °)",
                "☜(⌒▽⌒)☞",
                "+1 ☜(⌒▽⌒)☞ +1",
                "｡◕‿‿◕｡",
                "╚(ಠ_ಠ)=┐",
                "(ಠ‿ಠ)",
                "(ʘᗩʘ')",
                "(✿´‿)",
                "ಥಥ",
                "(ღ˘⌣˘ღ)",
                "(；一一)",
                "¯(°o)/¯",
                "(¬‿¬)",
                "͠° ͟ل͜ ͡°",
                "(>ლ)",
                "(｡◕‿◕｡)",
                "┬─┬ノ(º  ºノ)",
                "凸(--)凸",
                "̿ ̿ ̿'̿'̵͇̿з=(••)=ε/̵͇̿/'̿'̿ ̿",
                "̿̿ ̿̿ ̿̿ ̿'̿'̵͇̿з= (▀ ͜͞ʖ▀) =ε/̵͇̿/'̿'̿ ̿ ̿̿ ̿̿ ̿̿",
                "̿'̿'̵͇̿з=(͠° ͟ʖ ͡°)=ε/̵͇̿/'̿̿ ̿ ̿ ̿ ̿ ̿",
                "••)",
                "(••)>⌐■-■",
                "(⌐■■)",
                "o ()xxxx[{:>",
                "O===|__/",
                "¦̵̱ ̵̱ ̵̱ ̵̱ ̵̱(̢ ̡͇̅└͇̅┘͇̅ (▤8כ−◦",
                "༼ つ ಥಥ ༽つ",
                "| (• ◡•)|",
                "(❍ᴥ❍ʋ)",
                "t (-.-)t",
                "(☞ﾟヮﾟ)☞",
                "<:[]=¤༼ຈل͜ຈ༽ﾉ",
                "(͡° ͜ʖ ͡°) ▄︻̷̿┻̿═━一 ʕ•ᴥ•ʔ",
                "ლ(ಠ益ಠლ)",
                "(⌐■■)>¸,ø¤º°`°º¤ø,¸¸",
                "(͝סּ ͜ʖ͡סּ)",
                "(xx)@~(--Q)",
                "(ﾉ◕ヮ◕)ﾉ:･ﾟ✧(ﾉ◕ヮ◕)ﾉ*: ･ﾟ✧",
                "(͡° ͜ʖ ͡°) ▄︻̷̿┻̿═━一 (ʘᗩʘ')",
                "ζ༼Ɵ͆ل͜Ɵ͆༽ᶘ",
                "ლ(ಠ‿ಠლ)"};


        Member randomMember;

        while (true)
        {
            randomMember = MemberData.getRandomMember(msg.getGuild());
            if (randomMember.getRoles().contains(msg.getGuild().getRoleById(properties.get().technoSomeOneMinRoleId)))
            {
                break;
            }
        }

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(msg.getAuthor().getName(),"https://minecraftonly.ru", msg.getMember().getUser().getAvatarUrl());
        embed.setColor(new Color(165,40,197));
        embed.setTimestamp(OffsetDateTime.now());
        String rndMemStr = randomMember.getUser().getName();
        if (randomMember.getNickname() != null) {
            rndMemStr = randomMember.getNickname();
        }
        embed.addField(smiles[new Random().nextInt(smiles.length)], "**" + rndMemStr + "** " + msg.getContentDisplay().substring(9), false);

        msg.getChannel().sendMessage(embed.build()).queue();
    }
}