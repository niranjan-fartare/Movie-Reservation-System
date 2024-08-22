package dev.niranjan.BookMyShow.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel {
    private String name;
    private String State;
    @OneToMany
    private List<Theatre> theatreList;
}
