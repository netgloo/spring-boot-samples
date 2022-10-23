package netgloo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.jetbrains.annotations.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity(name = "KVEntity")
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
@NoArgsConstructor
public class KVEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kv_entity_id", nullable = false, unique = true)
    @JsonProperty("kv_entity_id")
    private Long KVEntityId;

    @Column(name = "kv_entity_key", nullable = false)
    @JsonProperty("kv_entity_key")
    private String KVEntityKey;


    @Column(name = "kv_entity_value", length = 10000) // NULLABLE
    @JsonProperty("kv_entity_value")
    @Nullable
    private String KVEntityValue;

    public KVEntity(String key, @Nullable String value) {
        this.KVEntityKey = key;
        this.KVEntityValue = value;
    }

    @Override
    public String toString() {
        return "KVEntity{" +
                "id=" + this.KVEntityId +
                ", key='" + KVEntityKey + '\'' +
                ", value='" + KVEntityValue + '\'' +
                '}';
    }
}
