package dev.niranjan.BookMyShow.DTO;

import dev.niranjan.BookMyShow.Model.Constant.AuditoriumFeature;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AuditoriumDTO {
    private String name;
    private int audiNumber;
    private int capacity;
    private int theatreId;
    private List<AuditoriumFeature> auditoriumFeatures;
}
