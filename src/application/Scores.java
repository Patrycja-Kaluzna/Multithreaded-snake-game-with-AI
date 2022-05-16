
package application;

public class Scores implements Comparable <Scores> {
    public String name="";
    public int score=0;

    public Scores(String Name, int Score){
        name=Name;
        score=Score;
    }

    @Override
    public int compareTo(Scores s){
        return s.score- this.score ;
    }
}
