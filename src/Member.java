public class Member {
    private char memberType;
    private int memberID;
    private String name;
    private double fees;
    public Member(char memberType, int memberID, String name, double fees){
        this.memberType = memberType;
        this.memberID = memberID;
        this.name = name;
        this.fees = fees;
    }
    public char getMemberType() {
        return memberType;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return  memberType +
                ", " + memberID +
                ", " + name +
                ", " + fees ;
    }
}