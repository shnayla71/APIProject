package POJO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Countries {

    private String country_id;
    private String country_name;
    private int region_id;
}
