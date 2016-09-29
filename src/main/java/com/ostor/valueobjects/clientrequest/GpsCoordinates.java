/* DO NOT EDIT */
/* This file was generated from files.stone */

package com.ostor.valueobjects.clientrequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.ostor.util.StoneSerializers;
import com.ostor.util.StructSerializer;

import java.io.IOException;

/**
 * GPS coordinates for a photo or video.
 */
public class GpsCoordinates {
    // struct GpsCoordinates

    protected final double latitude;
    protected final double longitude;

    /**
     * GPS coordinates for a photo or video.
     *
     * @param latitude  Latitude of the GPS coordinates.
     * @param longitude  Longitude of the GPS coordinates.
     */
    public GpsCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Latitude of the GPS coordinates.
     *
     * @return value for this field.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Longitude of the GPS coordinates.
     *
     * @return value for this field.
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public int hashCode() {
        int hash = java.util.Arrays.hashCode(new Object [] {
            latitude,
            longitude
        });
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        // be careful with inheritance
        else if (obj.getClass().equals(this.getClass())) {
            GpsCoordinates other = (GpsCoordinates) obj;
            return (this.latitude == other.latitude)
                && (this.longitude == other.longitude)
                ;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Serializer.INSTANCE.serialize(this, false);
    }

    /**
     * Returns a String representation of this object formatted for easier
     * readability.
     *
     * <p> The returned String may contain newlines. </p>
     *
     * @return Formatted, multiline String representation of this object
     */
    public String toStringMultiline() {
        return Serializer.INSTANCE.serialize(this, true);
    }

    /**
     * For internal use only.
     */
    static final class Serializer extends StructSerializer<GpsCoordinates> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void serialize(GpsCoordinates value, JsonGenerator g, boolean collapse) throws IOException, JsonGenerationException {
            if (!collapse) {
                g.writeStartObject();
            }
            g.writeFieldName("latitude");
            StoneSerializers.float64().serialize(value.latitude, g);
            g.writeFieldName("longitude");
            StoneSerializers.float64().serialize(value.longitude, g);
            if (!collapse) {
                g.writeEndObject();
            }
        }

        @Override
        public GpsCoordinates deserialize(JsonParser p, boolean collapsed) throws IOException, JsonParseException {
            GpsCoordinates value;
            String tag = null;
            if (!collapsed) {
                expectStartObject(p);
                tag = readTag(p);
            }
            if (tag == null) {
                Double f_latitude = null;
                Double f_longitude = null;
                while (p.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String field = p.getCurrentName();
                    p.nextToken();
                    if ("latitude".equals(field)) {
                        f_latitude = StoneSerializers.float64().deserialize(p);
                    }
                    else if ("longitude".equals(field)) {
                        f_longitude = StoneSerializers.float64().deserialize(p);
                    }
                    else {
                        skipValue(p);
                    }
                }
                if (f_latitude == null) {
                    throw new JsonParseException("Required field \"latitude\" missing.", p.getCurrentLocation());
                }
                if (f_longitude == null) {
                    throw new JsonParseException("Required field \"longitude\" missing.", p.getCurrentLocation());
                }
                value = new GpsCoordinates(f_latitude, f_longitude);
            }
            else {
                throw new JsonParseException("No subtype found that matches tag: \"" + tag + "\"", p.getCurrentLocation());
            }
            if (!collapsed) {
                expectEndObject(p);
            }
            return value;
        }
    }
}
