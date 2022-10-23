package netgloo.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
public class APIIdNotFoundException extends APIException {
    private final Long id;

    public APIIdNotFoundException(final Long id) {
        super("Interview with id " + id + " not found");
        this.id = id;
    }
}
