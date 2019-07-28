package timduk;

public class properties
{
    private static properties instance = new properties();
    public static properties get()
    {
        return instance;
    }

    public Long TimDukId = 210516579778166787L;

    // TechnoCraft bot settings
    public String technoBotToken = "NDY2NjY5NzY2MTk1Njc1MTM3.DifbnQ.LjiD75YwPgVMSmrQv2Y2v0LAQxc";
    public Long technoBotUserId = 466669766195675137L;

    // TechnoHuy bot
    public String technoCraftToken = "NjAyODY1MDY2NDYyODA2MDQ3.XTXD9g.8Mc56nxC_OkDVFYnihrtrALjdbY";
    public Long technoCraftUserId = 602865066462806047L;

    public Long technoCraftTellRandomChannel = 602866931791560727L;

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
    public Long technoLogChannelId = 501081109442985985L;
}