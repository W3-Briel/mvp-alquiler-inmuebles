package ar.edu.unpaz.app.model.strategy;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class EstadoInmuebleConverter implements AttributeConverter<EstadoInmueble, String> {

    @Override
    public String convertToDatabaseColumn(EstadoInmueble attribute) {
        if (attribute == null) return null;
        return attribute.getNombreEstado();
    }

    @Override
    public EstadoInmueble convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        switch (dbData) {
            case "DISPONIBLE":
                return new EstadoDisponible();
            case "ALQUILADO":
                return new EstadoAlquilado();
            case "EN_MANTENIMIENTO":
                return new EstadoEnMantenimiento();
            default:
                throw new IllegalArgumentException("EstadoInmueble desconocido en BD: " + dbData);
        }
    }
}