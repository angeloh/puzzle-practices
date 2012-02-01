
package com.sumware.quiz;

import java.io.InputStream;
import java.util.List;

public interface VehicleParser {
    public List<Vehicle> parseStream(InputStream in);
}
