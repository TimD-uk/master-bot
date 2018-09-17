package timduk;

import java.io.File;
import java.util.Properties;

public class properties
{
    private static properties instance = new properties();
    public static properties get()
    {
        return instance;
    }

    private static File propertiesFile = new File("serversProp.properties");
    private static Properties serversProp = new Properties();

    public Long TimDukId = 210516579778166787L;

    // TechnoCraft bot settings
    public String technoBotToken = "NDY2NjY5NzY2MTk1Njc1MTM3.DifbnQ.LjiD75YwPgVMSmrQv2Y2v0LAQxc";
    public Long technoBotUserId = 466669766195675137L;

    public Long technoGuildId = 466321862209699871L;
    public Long technoHeadRoleId = 466529626802618378L;
    public Long technoModerRoleId = 466529791009357835L;
    public Long[] technoOtherControlRolesId = {466623863602806784L};

    public Long technoSomeOneMinRoleId = 466526312584970250L;
    public Long technoMuteRoleId = 467761280590282763L;
    public Long technoLuckyManRoleId = 469483888092119046L;
    public Long technoStartMemberRoleId = 466616770955051018L;

    public Long technoMainChannelId = 466530335782338560L;
    public Long technoBotChannelId = 466530618608713728L;
    public Long technoNewsChannelId = 466324170708484096L;
    public Long technoAdminChannelId = 466321863199424525L;
    // On Test server
//    public Long technoBotChannelId = 484431228573974543L;
//    public Long technoNewsChannelId = 484427406686420993L;
//    public Long technoAdminChannelId = 484427427876044832L;

    // SpaceCraft bot settings
    public String spaceBotToken = "NDgzMjMxNDUwMTk2NDc1OTM0.DmQcWw.hjs-O-xHLDlHMAokWJWGpO2m6ME";
    public Long spaceBotUserId = 483231450196475934L;

    public Long spaceGuildId = 455714087544815619L;
    public Long spaceHeadRoleId = 455722881511456768L;
    public Long spaceModerRoleId = 455730080304332805L;
    public Long[] spaceOtherControlRolesId = {480038315437981706L};

    public Long spaceSomeOneMinRoleId = 455736976616980502L;

    public Long spaceMainChannelId = 455744306364547072L;
    public Long spaceAdminChannelId = 480029345197326359L;
    public Long spaceNewsChannelId = 455714199109107712L;

    public void load()
    {
        //https://www.java2novice.com/java-collections-and-util/properties/all-keys/
    }
}