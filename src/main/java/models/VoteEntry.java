package main.java.models;

import java.util.Objects;

public class VoteEntry implements Comparable<VoteEntry> {
    private String candidate;
    private String municipality;
    private int voteCount;

    public VoteEntry(String municipality, int voteCount) {
        candidate = "Dilan Yeşilgöz";
        this.municipality = municipality;
        this.voteCount = voteCount;
    }

    public String toString(){
        return voteCount + ":" + municipality;
    }

    @Override
    public int compareTo(VoteEntry o) {
        int voteComparison = Integer.compare(this.voteCount, o.voteCount);
        if (voteComparison == 0) {
            return this.municipality.compareTo(o.municipality);
        }
        return voteComparison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteEntry voteEntry)) return false;
        return Objects.equals(candidate, voteEntry.candidate) && Objects.equals(municipality, voteEntry.municipality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidate, municipality, voteCount);
    }
}
