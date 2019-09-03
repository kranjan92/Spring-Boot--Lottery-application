/**
 * 
 */
package com.lottery.ticket.application.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.lottery.ticket.application.rest.View;



/**
 * @author karthikranjan
 *
 */
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Basic.class)
    private Long id;

    
    @Enumerated()
    @JsonView(View.Basic.class)
    private Status status = Status.NEW;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("outcome desc")
    private List<LotteryLine> lines = new ArrayList<>();

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(View.Basic.class)
    public Integer getLinesCount() {
        return lines.size();
    }

    public List<LotteryLine> getLines() {
        return lines;
    }

    public void setLines(List<LotteryLine> lines) {
        this.lines = lines;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {

        NEW,
        CHECKED

    }


}
