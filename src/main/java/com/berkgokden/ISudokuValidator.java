package com.berkgokden;

import java.io.Reader;
import java.util.List;

/**
 * Created by berkgokden on 11/10/14.
 */
public interface ISudokuValidator {
    public List<String> validate(Reader reader);
}
