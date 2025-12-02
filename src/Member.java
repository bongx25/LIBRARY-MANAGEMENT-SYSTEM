public class Member {
    private int membershipId;
    private String firstName;
    // ... other fields needed for full system

    public Member(int membershipId, String firstName) {
        this.membershipId = membershipId;
        this.firstName = firstName;
    }

    public int getMembershipId() { return membershipId; }
    public String getFirstName() { return firstName; }
}