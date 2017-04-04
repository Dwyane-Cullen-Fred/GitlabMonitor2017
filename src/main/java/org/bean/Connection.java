package org.bean;

public class Connection {

    private int connectionCount;
    private String member1;
    private String member2;

    public int getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(int connectionCount) {
        this.connectionCount = connectionCount;
    }

    public String getMember1() {
        return member1;
    }

    public void setMember1(String member1) {
        this.member1 = member1;
    }

    public String getMember2() {
        return member2;
    }

    public void setMember2(String member2) {
        this.member2 = member2;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Connection) obj ).member1.equals(member1) && ((Connection) obj ).member2.equals(member2)) ||
                (((Connection) obj ).member1.equals(member2) && ((Connection) obj ).member2.equals(member1));
    }

    @Override
    public int hashCode() {
        return member1.hashCode() + member2.hashCode();
    }
}
