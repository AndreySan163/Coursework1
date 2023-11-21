public class SingleClubMember extends Member{
    private int club;

    public SingleClubMember(char memberType, int memberID, String name, double fees, int club) {
        super(memberType, memberID, name, fees);
        this.club = club;
    }

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return super.toString() + club + '\n';
    }
}