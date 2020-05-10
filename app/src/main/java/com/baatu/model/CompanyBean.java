package com.baatu.model;

/**
 *
 * @author Shailendra This is model class for company
 *
 */

public class CompanyBean {
    /**
     * name : Romaguera-Crona
     * catchPhrase : Multi-layered client-server neural-net
     * bs : harness real-time e-markets
     */

    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public CompanyBean() {
    }
}
