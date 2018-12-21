package domain;

import java.util.Date;

public class Message {

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public Message(String text, Date when, Person from, Person to) {
        this.text = text;
        this.when = when;
        this.from = from;
        this.to = to;
    }

    public boolean toAndFrom(Person to, Person from){
        return (this.to != null && this.to.equals(to)) && (this.from != null && this.from.equals(from));
    }

    public boolean toAndFromOrFromAndTo(Person a, Person b){
        return toAndFrom(a,b) || toAndFrom(b,a);
    }

    public Message(){};

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", when=" + when +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    private String text;
    private Date when;
    private Person from;
    private Person to;


}
